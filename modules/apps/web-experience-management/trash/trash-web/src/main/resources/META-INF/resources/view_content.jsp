<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = trashDisplayContext.getViewContentRedirectURL();

long classPK = trashDisplayContext.getClassPK();

PortletURL portletURL = renderResponse.createRenderURL();

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "recycle-bin"), portletURL.toString());

PortletURL containerModelURL = renderResponse.createRenderURL();

TrashHandler trashHandler = trashDisplayContext.getTrashHandler();

String trashHandlerContainerModelClassName = trashHandler.getContainerModelClassName(classPK);

containerModelURL.setParameter("mvcPath", "/view_content.jsp");
containerModelURL.setParameter("classNameId", String.valueOf(PortalUtil.getClassNameId(trashHandlerContainerModelClassName)));

TrashUtil.addBaseModelBreadcrumbEntries(request, liferayPortletResponse, trashDisplayContext.getClassName(), classPK, containerModelURL);

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

TrashRenderer trashRenderer = trashDisplayContext.getTrashRenderer();

renderResponse.setTitle(trashRenderer.getTitle(locale));
%>

<liferay-util:include page="/navigation.jsp" servletContext="<%= application %>" />

<liferay-util:include page="/toolbar.jsp" servletContext="<%= application %>" />

<liferay-util:include page="/restore_path.jsp" servletContext="<%= application %>" />

<div class="closed container-fluid-1280 sidenav-container sidenav-right" id="<portlet:namespace />infoPanelId">
	<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/info_panel" var="sidebarPanelURL" />

	<liferay-frontend:sidebar-panel
		resourceURL="<%= sidebarPanelURL %>"
	>
		<liferay-util:include page="/info_panel.jsp" servletContext="<%= application %>" />
	</liferay-frontend:sidebar-panel>

	<div class="sidenav-content">
		<div id="<portlet:namespace />trashContainer">
			<liferay-ui:breadcrumb
				showCurrentGroup="<%= false %>"
				showGuestGroup="<%= false %>"
				showLayout="<%= false %>"
				showParentGroups="<%= false %>"
			/>

			<liferay-util:include page="/info_panel_content.jsp" servletContext="<%= application %>">
				<liferay-util:param name="redirect" value="<%= redirect %>" />
			</liferay-util:include>

			<%
			PortletURL iteratorURL = renderResponse.createRenderURL();

			iteratorURL.setParameter("mvcPath", "/view_content.jsp");
			iteratorURL.setParameter("classNameId", String.valueOf(trashDisplayContext.getClassNameId()));
			iteratorURL.setParameter("classPK", String.valueOf(classPK));

			String emptyResultsMessage = LanguageUtil.format(request, "this-x-does-not-contain-an-entry", ResourceActionsUtil.getModelResource(locale, trashDisplayContext.getClassName()), false);
			%>

			<liferay-ui:search-container
				deltaConfigurable="<%= false %>"
				emptyResultsMessage="<%= emptyResultsMessage %>"
				id="trash"
				iteratorURL="<%= iteratorURL %>"
				total="<%= trashHandler.getTrashModelsCount(classPK) %>"
			>
				<liferay-ui:search-container-results
					results="<%= trashHandler.getTrashModelTrashRenderers(classPK, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
				/>

				<liferay-ui:search-container-row
					className="com.liferay.portal.kernel.trash.TrashRenderer"
					modelVar="curTrashRenderer"
				>

					<%
					TrashHandler curTrashHandler = TrashHandlerRegistryUtil.getTrashHandler(curTrashRenderer.getClassName());
					%>

					<liferay-ui:search-container-column-text
						cssClass="text-strong"
						name="name"
					>
						<c:choose>
							<c:when test="<%= curTrashHandler.isContainerModel() %>">

								<%
								PortletURL rowURL = renderResponse.createRenderURL();

								rowURL.setParameter("mvcPath", "/view_content.jsp");
								rowURL.setParameter("classNameId", String.valueOf(PortalUtil.getClassNameId(curTrashRenderer.getClassName())));
								rowURL.setParameter("classPK", String.valueOf(curTrashRenderer.getClassPK()));
								%>

								<aui:a href="<%= rowURL.toString() %>">
									<%= HtmlUtil.escape(curTrashRenderer.getTitle(locale)) %>
								</aui:a>
							</c:when>
							<c:otherwise>

								<%
								PortletURL rowURL = renderResponse.createRenderURL();

								rowURL.setParameter("mvcPath", "/preview.jsp");
								rowURL.setParameter("classNameId", String.valueOf(PortalUtil.getClassNameId(curTrashRenderer.getClassName())));
								rowURL.setParameter("classPK", String.valueOf(curTrashRenderer.getClassPK()));

								rowURL.setWindowState(LiferayWindowState.POP_UP);

								Map<String, Object> data = new HashMap<String, Object>();

								data.put("title", HtmlUtil.escape(curTrashRenderer.getTitle(locale)));
								data.put("url", rowURL.toString());
								%>

								<aui:a cssClass="preview" data="<%= data %>" href="javascript:;">
									<%= HtmlUtil.escape(curTrashRenderer.getTitle(locale)) %>
								</aui:a>
							</c:otherwise>
						</c:choose>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						name="type"
						value="<%= ResourceActionsUtil.getModelResource(locale, curTrashRenderer.getClassName()) %>"
					/>

					<liferay-ui:search-container-column-jsp
						cssClass="list-group-item-field"
						path="/view_content_action.jsp"
					/>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator markupView="lexicon" resultRowSplitter="<%= new TrashResultRowSplitter() %>" />
			</liferay-ui:search-container>
	</div>
</div>

<aui:script use="liferay-url-preview">
	A.one('#<portlet:namespace />trashContainer').delegate(
		'click',
		function(event) {
			var currentTarget = event.currentTarget;

			var urlPreview = new Liferay.UrlPreview(
				{
					title: currentTarget.attr('data-title'),
					url: currentTarget.attr('data-url')
				}
			);

			urlPreview.open();
		},
		'.preview'
	);
</aui:script>
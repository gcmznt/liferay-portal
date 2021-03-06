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

package com.liferay.social.networking.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.liferay.social.networking.model.WallEntry;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for WallEntry. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see WallEntryLocalServiceUtil
 * @see com.liferay.social.networking.service.base.WallEntryLocalServiceBaseImpl
 * @see com.liferay.social.networking.service.impl.WallEntryLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface WallEntryLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WallEntryLocalServiceUtil} to access the wall entry local service. Add custom service methods to {@link com.liferay.social.networking.service.impl.WallEntryLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public WallEntry addWallEntry(long groupId, long userId, String comments,
		ThemeDisplay themeDisplay) throws PortalException;

	/**
	* Adds the wall entry to the database. Also notifies the appropriate model listeners.
	*
	* @param wallEntry the wall entry
	* @return the wall entry that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public WallEntry addWallEntry(WallEntry wallEntry);

	/**
	* Creates a new wall entry with the primary key. Does not add the wall entry to the database.
	*
	* @param wallEntryId the primary key for the new wall entry
	* @return the new wall entry
	*/
	@Transactional(enabled = false)
	public WallEntry createWallEntry(long wallEntryId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	public void deleteWallEntries(long groupId) throws PortalException;

	/**
	* Deletes the wall entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param wallEntryId the primary key of the wall entry
	* @return the wall entry that was removed
	* @throws PortalException if a wall entry with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public WallEntry deleteWallEntry(long wallEntryId)
		throws PortalException;

	/**
	* Deletes the wall entry from the database. Also notifies the appropriate model listeners.
	*
	* @param wallEntry the wall entry
	* @return the wall entry that was removed
	* @throws PortalException
	*/
	@Indexable(type = IndexableType.DELETE)
	public WallEntry deleteWallEntry(WallEntry wallEntry)
		throws PortalException;

	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.social.networking.model.impl.WallEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.social.networking.model.impl.WallEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WallEntry fetchWallEntry(long wallEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns a range of all the wall entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.social.networking.model.impl.WallEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of wall entries
	* @param end the upper bound of the range of wall entries (not inclusive)
	* @return the range of wall entries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WallEntry> getWallEntries(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WallEntry> getWallEntries(long groupId, int start, int end);

	/**
	* Returns the number of wall entries.
	*
	* @return the number of wall entries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getWallEntriesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getWallEntriesCount(long groupId);

	/**
	* Returns the wall entry with the primary key.
	*
	* @param wallEntryId the primary key of the wall entry
	* @return the wall entry
	* @throws PortalException if a wall entry with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WallEntry getWallEntry(long wallEntryId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WallEntry> getWallToWallEntries(long groupId1, long groupId2,
		long userId1, long userId2, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getWallToWallEntriesCount(long groupId1, long groupId2,
		long userId1, long userId2);

	public WallEntry updateWallEntry(long wallEntryId, String comments)
		throws PortalException;

	/**
	* Updates the wall entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param wallEntry the wall entry
	* @return the wall entry that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public WallEntry updateWallEntry(WallEntry wallEntry);
}
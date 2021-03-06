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

package com.liferay.document.library.uad.anonymizer;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.uad.constants.DLUADConstants;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import com.liferay.user.associated.data.anonymizer.DynamicQueryUADAnonymizer;

import org.osgi.service.component.annotations.Reference;

import java.util.Arrays;
import java.util.List;

/**
 * Provides the base implementation for the document library file entry UAD anonymizer.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom service methods should be put in
 * {@link DLFileEntryUADAnonymizer}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseDLFileEntryUADAnonymizer
	extends DynamicQueryUADAnonymizer<DLFileEntry> {
	@Override
	public void autoAnonymize(DLFileEntry dlFileEntry, long userId,
		User anonymousUser) throws PortalException {
		if (dlFileEntry.getUserId() == userId) {
			dlFileEntry.setUserId(anonymousUser.getUserId());
			dlFileEntry.setUserName(anonymousUser.getFullName());
		}

		dlFileEntryLocalService.updateDLFileEntry(dlFileEntry);
	}

	@Override
	public void delete(DLFileEntry dlFileEntry) throws PortalException {
		dlFileEntryLocalService.deleteFileEntry(dlFileEntry);
	}

	@Override
	public List<String> getNonanonymizableFieldNames() {
		return Arrays.asList("fileName", "extension", "title", "description");
	}

	@Override
	public Class<DLFileEntry> getTypeClass() {
		return DLFileEntry.class;
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return dlFileEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return DLUADConstants.USER_ID_FIELD_NAMES_DL_FILE_ENTRY;
	}

	@Reference
	protected DLFileEntryLocalService dlFileEntryLocalService;
}
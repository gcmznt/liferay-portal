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

package com.liferay.fragment.processor;

import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Locale;

/**
 * Represents fragment entry settings to be used in the processing of
 * fragment entry HTML.
 *
 * @author Pavel Savinov
 */
public interface FragmentEntrySettings {

	/**
	 * Returns the setting value for specific setting ID and default locale.
	 *
	 * @param id Setting ID
	 * @return the setting value
	 */
	public default String getValue(String id) {
		return getValue(id, LocaleUtil.getSiteDefault());
	};

	/**
	 * Returns the setting value for specific setting ID and locale.
	 *
	 * @param id Setting ID
	 * @param locale Locale
	 * @return the setting value
	 */
	public String getValue(String id, Locale locale);

}
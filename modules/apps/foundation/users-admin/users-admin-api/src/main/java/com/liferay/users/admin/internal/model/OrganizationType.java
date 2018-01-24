/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 * <p>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.users.admin.internal.model;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.users.admin.internal.configuration.OrganizationTypeConfiguration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import java.util.Map;

/**
 * @author Marco Leo
 */
@Component(
	configurationPid = "com.liferay.users.admin.internal.configuration.OrganizationTypeConfiguration",
	immediate = true, service = OrganizationType.class
)
public class OrganizationType {

	public String[] getChildrenTypes() {

		return _organizationsTypesConfiguration.childrenTypes();
	}

	public String getName() {
		return _organizationsTypesConfiguration.name();
	}

	public boolean isCountryEnabled() {
		return _organizationsTypesConfiguration.countryEnabled();
	}

	public boolean isCountryRequired() {
		return _organizationsTypesConfiguration.countryRequired();
	}

	public boolean isRootable() {
		return _organizationsTypesConfiguration.rootable();
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_organizationsTypesConfiguration =
			ConfigurableUtil.createConfigurable(
				OrganizationTypeConfiguration.class, properties);
	}

	private volatile OrganizationTypeConfiguration _organizationsTypesConfiguration;
}

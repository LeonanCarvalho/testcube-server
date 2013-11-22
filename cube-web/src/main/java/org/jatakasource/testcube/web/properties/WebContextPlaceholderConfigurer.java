/**
 * TestCube is an enterprise Test management tool.
 * Copyright (C) 2011 JatakaSource Ltd.
 *
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * TestCube is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with TestCube.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.jatakasource.testcube.web.properties;

import org.apache.commons.lang.StringUtils;
import org.jatakasource.testcube.startup.Startup;
import org.jatakasource.web.properties.ContextAttributesPlaceholderConfigurer;

public class WebContextPlaceholderConfigurer extends ContextAttributesPlaceholderConfigurer {
	private final static String SYSTEM_PROPERTIES_BEAN_NAME = "systemProperties";
	private final static String DEVELOPMENT_WAR_NAME = "cube-web";

	public WebContextPlaceholderConfigurer() {
		super();
	}

	@Override
	public String getDevelopmentWarName() {
		return DEVELOPMENT_WAR_NAME;
	}

	@Override
	public String getServerEnvdirFolder() {
		return Startup.SERVER_ENVDIR_FOLDER;
	}

	@Override
	public String getServerRundirFolder() {
		return Startup.SERVER_RUNDIR_FOLDER;
	}

	@Override
	public String getSystemPropertiesBeanName() {
		return SYSTEM_PROPERTIES_BEAN_NAME;
	}

	public String get(String placeholder) {
		return resolvePlaceholder(placeholder, getProperties());
	}

	public String get(String code, String defaultValue) {
		String value = get(code);
		if (StringUtils.isNotEmpty(value)) {
			return value;
		}

		return defaultValue;
	}

	public Integer getInteger(String code, Integer defaultValue) {
		String value = get(code);
		if (StringUtils.isNumeric(value) && StringUtils.isNotEmpty(value)) {
			return Integer.valueOf(value);
		}

		return defaultValue;
	}

}

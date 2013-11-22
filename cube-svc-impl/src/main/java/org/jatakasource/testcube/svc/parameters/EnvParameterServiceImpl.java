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
package org.jatakasource.testcube.svc.parameters;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.LocaleUtils;
import org.apache.commons.lang.StringUtils;
import org.jatakasource.common.properties.SystemPlaceholderConfigurer;
import org.jatakasource.common.svc.properties.MultiInstanceSystemProperties;
import org.jatakasource.testcube.startup.Startup;
import org.jatakasource.testcube.svc.parameters.EnvParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EnvParameterServiceImpl implements MultiInstanceSystemProperties, EnvParameterService {
	private final static String DEFAULT_DIRECTION = "ltr";
	private final static Locale DEFAULT_LOCALE = Locale.ENGLISH;

	@Autowired
	@Qualifier(value = "systemProperties")
	private SystemPlaceholderConfigurer placeholderConfigurer;

	private String instanceName;

	@PostConstruct
	protected void loadInstanceName() {
		instanceName = System.getProperty(Startup.INSTANCE_NAME_KEY, Startup.DEFAULT_INSTANCE);
	}

	@Override
	public String getInstanceName() {
		return instanceName;
	}

	@Override
	public String getProperty(String code) {
		return placeholderConfigurer.get(code);
	}

	@Override
	public Integer getInteger(String code, Integer defaultValue) {
		return placeholderConfigurer.getInteger(code, defaultValue);
	}

	@Override
	public String getProperty(String code, String defaultValue) {
		return placeholderConfigurer.get(code, defaultValue);
	}

	@Override
	public Locale getLocale() {
		Locale locale = null;

		String localeStr = getProperty(EnvParametersKeys.class.getName() + "." + EnvParametersKeys.DEFAULT_LOCALE.name());

		if (StringUtils.isNotEmpty(localeStr)) {
			locale = LocaleUtils.toLocale(localeStr);
		}
		return locale == null ? DEFAULT_LOCALE : locale;
	}

	@Override
	public String getDirection() {
		String directionStr = getProperty(EnvParametersKeys.class.getName() + "." + EnvParametersKeys.DEFAULT_DIRECTION.name());

		return StringUtils.isNotEmpty(directionStr) ? directionStr: DEFAULT_DIRECTION;
	}
}

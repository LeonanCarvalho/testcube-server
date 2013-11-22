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
package org.jatakasource.testcube.web.messages;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

@Service
public class ApplicationMessages {
	@Autowired
	@Qualifier(value = "applicationProperties")
	protected ResourceBundleMessageSource messageSource;

	private Locale locale;

	// Return message from Spring MessageSource.
	public String getMessage(String code) {
		return messageSource.getMessage(code, new Object[] {}, locale);
	}

	// Return message from Spring MessageSource.
	public String getMessage(String code, Locale locale) {
		return messageSource.getMessage(code, new Object[] {}, locale);
	}

	public String getMessage(String code, Integer... params) {
		return messageSource.getMessage(code, params, locale);
	}

	public String getMessage(String code, String... params) {
		return messageSource.getMessage(code, params, locale);
	}

	public String getMessage(String code, String defaultMessage, Locale locale) {
		return messageSource.getMessage(code, new Object[] {}, defaultMessage, locale);
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public Locale getLocale() {
		return locale == null ? Locale.ENGLISH : locale;
	}
}
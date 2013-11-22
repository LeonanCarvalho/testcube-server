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
package org.jatakasource.testcube.jsf.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.commons.lang.StringUtils;
import org.jatakasource.testcube.jsf.JSFBean;
import org.jatakasource.testcube.web.properties.WebSystemProperties;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

@ManagedBean
@RequestScoped
@URLMapping(id = "after", pattern = "/login", viewId = "/jsf/public/login.xhtml")
public class LoginBean extends JSFBean {
	private static final long serialVersionUID = 6583902905238163066L;
	private static final String ERROR_PARAM = "error_message";

	public String getTitle() {
		return getApplicationMessages().getMessage(LoginProperties.class.getName() + "." + LoginProperties.TITLE.name(), getLocale());
	}

	public String getProductName() {
		return getApplicationMessages().getMessage(LoginProperties.class.getName() + "." + LoginProperties.PRODUCT_NAME.name(), getLocale());
	}

	public String getHeaderTitle() {
		return getApplicationMessages().getMessage(LoginProperties.class.getName() + "." + LoginProperties.HEADER_TITLE.name(), getLocale());
	}

	public String getUserNameCapital() {
		return getApplicationMessages().getMessage(LoginProperties.class.getName() + "." + LoginProperties.USERNAME_CAPITAL.name(), getLocale());
	}

	public String getUserNameLabel() {
		return getApplicationMessages().getMessage(LoginProperties.class.getName() + "." + LoginProperties.USERNAME_LABEL.name(), getLocale());
	}

	public String getPasswordCapital() {
		return getApplicationMessages().getMessage(LoginProperties.class.getName() + "." + LoginProperties.PASSWORD_CAPITAL.name(), getLocale());
	}

	public String getPasswordLabel() {
		return getApplicationMessages().getMessage(LoginProperties.class.getName() + "." + LoginProperties.PASSWORD_LABEL.name(), getLocale());
	}

	public String getRememberCapital() {
		return getApplicationMessages().getMessage(LoginProperties.class.getName() + "." + LoginProperties.REMEMBER_CAPITAL.name(), getLocale());
	}

	public String getRememberLabel() {
		return getApplicationMessages().getMessage(LoginProperties.class.getName() + "." + LoginProperties.REMEMBER_LABEL.name(), getLocale());
	}

	public String getLoginButton() {
		return getApplicationMessages().getMessage(LoginProperties.class.getName() + "." + LoginProperties.LOGIN_BUTTON.name(), getLocale());
	}

	public String getForgotPassword() {
		return getApplicationMessages().getMessage(LoginProperties.class.getName() + "." + LoginProperties.FORGOT_PASSWORD.name(), getLocale());
	}

	public String getReportAProblem() {
		return getApplicationMessages().getMessage(LoginProperties.class.getName() + "." + LoginProperties.REPORT_A_PROBLEM.name(), getLocale());
	}

	public String getTestManagement() {
		return getApplicationMessages().getMessage(LoginProperties.class.getName() + "." + LoginProperties.TEST_MANAGEMENT.name(), getLocale());
	}

	public String getTestManagementAnd() {
		return getApplicationMessages().getMessage(LoginProperties.class.getName() + "." + LoginProperties.TEST_MANAGEMENT_AND.name(), getLocale());
	}

	public String getAutomatedTests() {
		return getApplicationMessages().getMessage(LoginProperties.class.getName() + "." + LoginProperties.AUTOMATED_TESTS.name(), getLocale());
	}

	public String getAutomatedTestsFor() {
		return getApplicationMessages().getMessage(LoginProperties.class.getName() + "." + LoginProperties.AUTOMATED_TESTS_FOR.name(), getLocale());
	}

	public String getSoftwareDevelopment() {
		return getApplicationMessages().getMessage(LoginProperties.class.getName() + "." + LoginProperties.SOFTWARE_DEVELOPMENT.name(), getLocale());
	}

	public String getSoftwareDevelopmentPowered() {
		return getApplicationMessages().getMessage(LoginProperties.class.getName() + "." + LoginProperties.SOFTWARE_DEVELOPMENT_POWERED.name(), getLocale());
	}

	public String getVersion() {
		return getWebSystemProperties().getProperty(WebSystemProperties.class.getName() + "." + WebSystemProperties.VERSION.name(), "1.1.0");
	}

	public String getBuildNumber() {
		return getWebSystemProperties().getProperty(WebSystemProperties.class.getName() + "." + WebSystemProperties.BUILD_NUMBER.name(), "0");
	}

	public String getRevisionNumber() {
		return getWebSystemProperties().getProperty(WebSystemProperties.class.getName() + "." + WebSystemProperties.REVISION_NUMBER.name(), "1");
	}

	public Boolean getErrorExists() {
		String error = getRequest().getParameter(ERROR_PARAM);

		return StringUtils.isNotEmpty(error);
	}

	public String getError() {
		if (getErrorExists()) {
			String error = getRequest().getParameter(ERROR_PARAM);

			return getApplicationMessages()
					.getMessage(LoginErrorProperties.class.getName() + "." + LoginErrorProperties.secureValueOf(error).name(), "Unknown Error.", getLocale());
		}

		return StringUtils.EMPTY;
	}

}

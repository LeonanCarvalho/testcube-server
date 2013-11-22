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
package org.jatakasource.testcube.jsf;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jatakasource.common.svc.properties.SystemProperties;
import org.jatakasource.testcube.svc.parameters.DBParameterService;
import org.jatakasource.testcube.svc.parameters.EnvParameterService;
import org.jatakasource.testcube.web.messages.ApplicationMessages;

public abstract class JSFBean implements Serializable {
	private static final long serialVersionUID = -1432164194377763420L;
	private static final Logger logger = Logger.getLogger(JSFBean.class);

	private static final String DEFAULT_CHARACTER_ENCODING = "UTF-8";

	@ManagedProperty(value = "#{applicationMessages}")
	private ApplicationMessages applicationMessages;

	@ManagedProperty(value = "#{DBParameterServiceImpl}")
	private DBParameterService dbParametersService;
	@ManagedProperty(value = "#{envParameterServiceImpl}")
	private EnvParameterService envParameterService;

	@ManagedProperty(value = "#{webSystemPropertiesService}")
	private SystemProperties webSystemProperties;

	public DBParameterService getDbParametersService() {
		return dbParametersService;
	}

	public void setDbParametersService(DBParameterService dbParametersService) {
		this.dbParametersService = dbParametersService;
	}

	public EnvParameterService getEnvParameterService() {
		return envParameterService;
	}

	public void setEnvParameterService(EnvParameterService envParameterService) {
		this.envParameterService = envParameterService;
	}

	public ApplicationMessages getApplicationMessages() {
		return applicationMessages;
	}

	public void setApplicationMessages(ApplicationMessages applicationMessages) {
		this.applicationMessages = applicationMessages;
	}

	public SystemProperties getWebSystemProperties() {
		return webSystemProperties;
	}

	public void setWebSystemProperties(SystemProperties webSystemProperties) {
		this.webSystemProperties = webSystemProperties;
	}

	public String getDirection() {
		return envParameterService.getDirection();
	}

	public Locale getLocale() {
		return envParameterService.getLocale();
	}

	public String getLocaleAsString() {
		return envParameterService.getLocale().toString();
	}

	protected HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	protected HttpServletResponse getResponse() {
		return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	}

	protected String getContext() {
		return getRequest().getContextPath();
	}

	public String getServerPath() {
		return getRequest().getScheme() + "://" + getRequest().getServerName() + ":" + getRequest().getServerPort() + getRequest().getContextPath() + "/";
	}

	public String getCharacterEncoding() {
		getResponse().setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
		try {
			getRequest().setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
		} catch (UnsupportedEncodingException e) {
			logger.error("Error while setting request CharacterEncoding !!! ");
		}

		return DEFAULT_CHARACTER_ENCODING;
	}

}

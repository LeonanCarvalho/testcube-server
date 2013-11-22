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
package org.jatakasource.testcube.web.viewer;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.jatakasource.common.svc.exception.ServiceException;
import org.jatakasource.common.svc.exception.ServiceRunTimeException;
import org.jatakasource.testcube.web.controller.GeneralProperties;
import org.jatakasource.testcube.web.messages.ApplicationMessages;
import org.jatakasource.web.messages.ReturnMessage;
import org.jatakasource.web.viewer.ExceptionViewer;
import org.jatakasource.web.viewer.Viewer;
import org.springframework.beans.factory.annotation.Autowired;

@Viewer
public class CubeExceptionViewer extends ExceptionViewer {
	private final static Logger logger = Logger.getLogger(ExceptionViewer.class);

	@Autowired
	private ApplicationMessages applicationMessages;

	public ApplicationMessages getMessages() {
		return applicationMessages;
	}

	public void setMessages(ApplicationMessages applicationMessages) {
		this.applicationMessages = applicationMessages;
	}

	protected void handleException(Map<String, Object> model, Exception ex, HttpServletResponse response) {
		super.handleException(model, ex, response);

		logger.error("HTTP Request ERROR !!!", ex);

		response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
	}

	@Override
	protected void handleException(ServiceRunTimeException exception, Map<String, Object> model) {
		String title = getMessages().getMessage(GeneralProperties.class.getName() + "." + GeneralProperties.getGeneralTitle(exception.getType()).name());
		String message =  applicationMessages.getMessage(exception.getClass().getName());

		model.put(DATA_TO_SERIALIZED_KEY, new ReturnMessage(exception.getType(), title, message));
	}
	
	@Override
	protected void handleException(ServiceException exception, Map<String, Object> model) {
		String title = getMessages().getMessage(GeneralProperties.class.getName() + "." + GeneralProperties.getGeneralTitle(exception.getType()).name());
		String message =  applicationMessages.getMessage(exception.getClass().getName());

		model.put(DATA_TO_SERIALIZED_KEY, new ReturnMessage(exception.getType(), title, message));
	}
}

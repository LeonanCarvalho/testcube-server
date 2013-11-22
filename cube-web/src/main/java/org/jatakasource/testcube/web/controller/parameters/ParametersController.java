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
package org.jatakasource.testcube.web.controller.parameters;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.jatakasource.common.model.parameters.EnvParameter;
import org.jatakasource.common.model.parameters.IDBParameter;
import org.jatakasource.common.model.parameters.ISystemParameter;
import org.jatakasource.common.svc.CRUDService;
import org.jatakasource.testcube.model.parameters.DBParameter;
import org.jatakasource.testcube.svc.parameters.DBParameterService;
import org.jatakasource.testcube.svc.parameters.EnvParameterService;
import org.jatakasource.testcube.svc.parameters.EnvParametersKeys;
import org.jatakasource.testcube.web.controller.ApplicationMessagesController;
import org.jatakasource.testcube.web.messages.ApplicationMessages;
import org.jatakasource.testcube.web.xml.parameters.DBParameterRendered;
import org.jatakasource.testcube.web.xml.parameters.DBParametersRenderedList;
import org.jatakasource.testcube.web.xml.parameters.SystemParameterRendered;
import org.jatakasource.testcube.web.xml.parameters.SystemParametersRenderedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ParametersController extends ApplicationMessagesController<IDBParameter, String> {
	private static final Logger logger = Logger.getLogger(ParametersController.class);

	@Autowired
	private DBParameterService dbParameterService;
	@Autowired
	private EnvParameterService envParameterService;

	@Autowired
	private ApplicationMessages applicationMessages;

	@PostConstruct
	public void initialize() {
		applicationMessages.setLocale(envParameterService.getLocale());
	}

	@RequestMapping(value = PROTECTED_SERVICE + "/parameters/get")
	protected ModelAndView getAllSys(@RequestParam(value = "SYS_PARAMETERS", required = true) String parametersStr) {
		logger.trace("Request for SYS parameters + " + parametersStr);

		// Get DB parameters list
		List<String> parametersKeys = toList(parametersStr);
		List<ISystemParameter> parameters = new ArrayList<ISystemParameter>();
		Object paramter = null;

		for (String parameterKey : parametersKeys) {
			// Try and get the DB parameter
			paramter = dbParameterService.get(parameterKey);

			// Get Env Parameter
			if (paramter == null) {
				paramter = envParameterService.getProperty(EnvParametersKeys.class.getName() + "." + parameterKey);
				if (paramter != null)
					paramter = new EnvParameter(parameterKey, paramter.toString());
			}

			if (paramter != null)
				parameters.add((ISystemParameter) paramter);
			else
				logger.warn(parameterKey + " is missing both in DB and ENV.");
		}

		return getXMLViewer(getAsRenderer(parameters, SystemParameterRendered.class, ISystemParameter.class, SystemParametersRenderedList.class));
	}

	@RequestMapping(value = PROTECTED_SERVICE + "/dbparameters/get")
	protected ModelAndView getAll(@RequestParam(value = "DB_PARAMETERS", required = true) String parametersStr) {
		logger.trace("Request for SYS parameters + " + parametersStr);

		// Get DB parameters list
		List<String> parametersKeys = toList(parametersStr);
		List<DBParameter> parameters = new ArrayList<DBParameter>();
		DBParameter paramter = null;

		for (String parameterKey : parametersKeys) {
			paramter = (DBParameter) dbParameterService.get(parameterKey);

			if (paramter != null)
				parameters.add((DBParameter) paramter);
		}

		return getXMLViewer(getAsRenderer(parameters, DBParameterRendered.class, DBParameter.class, DBParametersRenderedList.class));
	}

	@Override
	protected CRUDService<IDBParameter, String> getCrudService() {
		return dbParameterService;
	}

	@Override
	protected String getModelName() {
		return null;
	}

}

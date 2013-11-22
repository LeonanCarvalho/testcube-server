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
package org.jatakasource.testcube.web.controller.product;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jatakasource.common.svc.CRUDService;
import org.jatakasource.testcube.model.product.Environment;
import org.jatakasource.testcube.model.product.IEnvironment;
import org.jatakasource.testcube.svc.product.EnvironmentService;
import org.jatakasource.testcube.web.controller.ApplicationMessagesController;
import org.jatakasource.testcube.web.xml.product.EnvironmentGridParameters;
import org.jatakasource.testcube.web.xml.product.EnvironmentRendered;
import org.jatakasource.testcube.web.xml.product.EnvironmentRenderedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EnvironmentController extends ApplicationMessagesController<IEnvironment, Long> {
	private static final Logger logger = Logger.getLogger(EnvironmentController.class);

	@Autowired
	private EnvironmentService environmentService;

	@Override
	protected CRUDService<IEnvironment, Long> getCrudService() {
		return environmentService;
	}

	@Override
	protected String getModelName() {
		return getMessages().getMessage(EnvironmentProperties.class.getName() + "." + EnvironmentProperties.MODEL_NAME.name());
	}

	@RequestMapping(value = PROTECTED_SERVICE + "/environment/search")
	protected ModelAndView search(@RequestParam(value = GRID_PARAMETERS, required = true) String xmlGridParameters) {
		logger.trace("Request for search environments with parameters " + xmlGridParameters + "!!!");

		EnvironmentGridParameters gridParameters = null;
		String keyword = null;
		Long productId = null;

		if (StringUtils.isNotEmpty(xmlGridParameters)) {
			gridParameters = getGridParameters(xmlGridParameters, EnvironmentGridParameters.class);
			keyword = gridParameters.getKeyword();
			productId = gridParameters.getProductId();
		}

		PagingAndSorting pagingAndSorting = getPagingAndSorting(gridParameters);

		// Return all users according to keyword search
		List<IEnvironment> environments = environmentService.getAll(pagingAndSorting.getPaging(), pagingAndSorting.getSorting(), keyword, productId);

		// Convert all users to UsersRendered
		EnvironmentRenderedList environmentRenderedList = getAsRenderer(environments, EnvironmentRendered.class, IEnvironment.class, EnvironmentRenderedList.class, gridParameters);

		return getXMLViewer(environmentRenderedList);
	}

	@Override
	@RequestMapping(value = PROTECTED_SERVICE + "/environment/delete")
	protected ModelAndView delete(@RequestParam(value = CRUD_PARAMETERS, required = true) Long id) {
		return super.delete(id);
	}

	@RequestMapping(value = PROTECTED_SERVICE + "/environment/update")
	protected ModelAndView update(@RequestParam(value = CRUD_PARAMETERS, required = true) String xmlCrudParameters) {
		return super.update(xmlCrudParameters, EnvironmentRendered.class, Environment.class);
	}

	@RequestMapping(value = PROTECTED_SERVICE + "/environment/create")
	protected ModelAndView create(@RequestParam(value = CRUD_PARAMETERS, required = true) String xmlCrudParameters) {
		return super.create(xmlCrudParameters, EnvironmentRendered.class, Environment.class);
	}

}

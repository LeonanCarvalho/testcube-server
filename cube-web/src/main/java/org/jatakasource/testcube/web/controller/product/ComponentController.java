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
import org.jatakasource.testcube.model.product.Component;
import org.jatakasource.testcube.model.product.IComponent;
import org.jatakasource.testcube.svc.product.ComponentService;
import org.jatakasource.testcube.web.controller.ApplicationMessagesController;
import org.jatakasource.testcube.web.xml.product.ComponentRendered;
import org.jatakasource.testcube.web.xml.product.ComponentRenderedList;
import org.jatakasource.web.xml.rendered.KeywordParameterRendered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ComponentController extends ApplicationMessagesController<IComponent, Long> {
	private static final Logger logger = Logger.getLogger(ComponentController.class);

	@Autowired
	private ComponentService componentService;

	@Override
	protected CRUDService<IComponent, Long> getCrudService() {
		return componentService;
	}

	@Override
	protected String getModelName() {
		return getMessages().getMessage(ComponentProperties.class.getName() + "." + ComponentProperties.MODEL_NAME.name());
	}
	
	@RequestMapping(value = PROTECTED_SERVICE + "/component/search")
	protected ModelAndView search(@RequestParam(value = GRID_PARAMETERS, required = true) String xmlGridParameters) {
		logger.trace("Request for search components with parameters " + xmlGridParameters + "!!!");

		KeywordParameterRendered gridParameters = null;
		String keyword = null;

		if (StringUtils.isNotEmpty(xmlGridParameters)) {
			gridParameters = getGridParameters(xmlGridParameters, KeywordParameterRendered.class);
			keyword = gridParameters.getKeyword();
		}

		PagingAndSorting pagingAndSorting = getPagingAndSorting(gridParameters);

		// Return all users according to keyword search
		List<IComponent> components = componentService.getAll(pagingAndSorting.getPaging(), pagingAndSorting.getSorting(), keyword);

		// Convert all users to UsersRendered
		ComponentRenderedList componentRenderedList = getAsRenderer(components, ComponentRendered.class, IComponent.class, ComponentRenderedList.class, gridParameters);

		return getXMLViewer(componentRenderedList);
	}
	
	@Override
	@RequestMapping(value = PROTECTED_SERVICE + "/component/delete")
	protected ModelAndView delete(@RequestParam(value = CRUD_PARAMETERS, required = true) Long id) {
		return super.delete(id);
	}

	@RequestMapping(value = PROTECTED_SERVICE + "/component/update")
	protected ModelAndView update(@RequestParam(value = CRUD_PARAMETERS, required = true) String xmlCrudParameters) {
		return super.update(xmlCrudParameters, ComponentRendered.class, Component.class);	
	}

	@RequestMapping(value = PROTECTED_SERVICE + "/component/create")
	protected ModelAndView create(@RequestParam(value = CRUD_PARAMETERS, required = true) String xmlCrudParameters) {
		return super.create(xmlCrudParameters,  ComponentRendered.class, Component.class);
	}

}

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
import org.jatakasource.testcube.model.product.Category;
import org.jatakasource.testcube.model.product.ICategory;
import org.jatakasource.testcube.model.testplan.ITestPlan;
import org.jatakasource.testcube.svc.product.CategoryService;
import org.jatakasource.testcube.svc.testplan.TestPlanService;
import org.jatakasource.testcube.web.controller.ApplicationMessagesController;
import org.jatakasource.testcube.web.xml.product.CategoryGridParameters;
import org.jatakasource.testcube.web.xml.product.CategoryRendered;
import org.jatakasource.testcube.web.xml.product.CategoryRenderedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController extends ApplicationMessagesController<ICategory, Long> {
	private static final Logger logger = Logger.getLogger(CategoryController.class);

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private TestPlanService testPlanService;

	@Override
	protected CRUDService<ICategory, Long> getCrudService() {
		return categoryService;
	}

	@Override
	protected String getModelName() {
		return getMessages().getMessage(CategoryProperties.class.getName() + "." + CategoryProperties.MODEL_NAME.name());
	}

	@RequestMapping(value = PROTECTED_SERVICE + "/category/search")
	protected ModelAndView search(@RequestParam(value = GRID_PARAMETERS, required = true) String xmlGridParameters) {
		logger.trace("Request for search categories with parameters " + xmlGridParameters + "!!!");

		CategoryGridParameters gridParameters = null;
		String keyword = null;
		Long productId = null;
		
		if (StringUtils.isNotEmpty(xmlGridParameters)) {
			gridParameters = getGridParameters(xmlGridParameters, CategoryGridParameters.class);
			keyword = gridParameters.getKeyword();

			// Extract productId if exists
			if (gridParameters.getProductId() != null) {
				productId = gridParameters.getProductId();
			}

			// If product id is missing try to extract from TestPlan id.
			if (productId == null && gridParameters.getTestPlanId() != null) {
				ITestPlan plan = testPlanService.get(gridParameters.getTestPlanId());
				if (plan != null) {
					productId = plan.getProduct().getId();
				}
			}
		}

		PagingAndSorting pagingAndSorting = getPagingAndSorting(gridParameters);

		// Return all users according to keyword search
		List<ICategory> categories = categoryService.getAll(pagingAndSorting.getPaging(), pagingAndSorting.getSorting(), keyword, productId);

		// Convert all users to UsersRendered
		CategoryRenderedList categoryRenderedList = getAsRenderer(categories, CategoryRendered.class, ICategory.class, CategoryRenderedList.class, gridParameters);

		return getXMLViewer(categoryRenderedList);
	}

	@Override
	@RequestMapping(value = PROTECTED_SERVICE + "/category/delete")
	protected ModelAndView delete(@RequestParam(value = CRUD_PARAMETERS, required = true) Long id) {
		return super.delete(id);
	}

	@RequestMapping(value = PROTECTED_SERVICE + "/category/update")
	protected ModelAndView update(@RequestParam(value = CRUD_PARAMETERS, required = true) String xmlCrudParameters) {
		return super.update(xmlCrudParameters, CategoryRendered.class, Category.class);
	}

	@RequestMapping(value = PROTECTED_SERVICE + "/category/create")
	protected ModelAndView create(@RequestParam(value = CRUD_PARAMETERS, required = true) String xmlCrudParameters) {
		return super.create(xmlCrudParameters, CategoryRendered.class, Category.class);
	}

}

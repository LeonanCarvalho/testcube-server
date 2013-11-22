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
package org.jatakasource.testcube.web.controller.testplan;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jatakasource.common.model.security.UserPojo;
import org.jatakasource.common.svc.CRUDService;
import org.jatakasource.testcube.model.testplan.ITestPlan;
import org.jatakasource.testcube.model.testplan.TestPlan;
import org.jatakasource.testcube.svc.security.UserService;
import org.jatakasource.testcube.svc.testplan.TestPlanService;
import org.jatakasource.testcube.web.controller.ApplicationMessagesController;
import org.jatakasource.testcube.web.xml.testplan.TestPlanGridParameters;
import org.jatakasource.testcube.web.xml.testplan.TestPlanRendered;
import org.jatakasource.testcube.web.xml.testplan.TestPlanRenderedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestPlanController extends ApplicationMessagesController<ITestPlan, Long> {
	private static final Logger logger = Logger.getLogger(TestPlanController.class);

	@Autowired
	private TestPlanService testPlanService;
	@Autowired
	private UserService userService;

	@Override
	protected CRUDService<ITestPlan, Long> getCrudService() {
		return testPlanService;
	}

	@Override
	protected String getModelName() {
		return getMessages().getMessage(TestPlanProperties.class.getName() + "." + TestPlanProperties.MODEL_NAME.name());
	}

	@RequestMapping(value = PROTECTED_SERVICE + "/testplan/search")
	protected ModelAndView search(@RequestParam(value = GRID_PARAMETERS, required = true) String xmlGridParameters) {
		logger.trace("Request for search test-plans with parameters " + xmlGridParameters + "!!!");

		TestPlanGridParameters gridParameters = null;
		String keyword = null;
		Long productId = null;

		if (StringUtils.isNotEmpty(xmlGridParameters)) {
			gridParameters = getGridParameters(xmlGridParameters, TestPlanGridParameters.class);
			keyword = gridParameters.getKeyword();
			productId = gridParameters.getProductId();
		}

		PagingAndSorting pagingAndSorting = getPagingAndSorting(gridParameters);

		// Return all plans according to keyword search
		List<ITestPlan> plans = testPlanService.getAll(pagingAndSorting.getPaging(), pagingAndSorting.getSorting(), keyword, productId);

		// Convert all plans to TestPlanRendered
		TestPlanRenderedList testplanRenderedList = getAsRenderer(plans, TestPlanRendered.class, ITestPlan.class, TestPlanRenderedList.class, gridParameters);

		return getXMLViewer(testplanRenderedList);
	}

	@Override
	@RequestMapping(value = PROTECTED_SERVICE + "/testplan/delete")
	protected ModelAndView delete(@RequestParam(value = CRUD_PARAMETERS, required = true) Long id) {
		return super.delete(id);
	}

	@RequestMapping(value = PROTECTED_SERVICE + "/testplan/update")
	protected ModelAndView update(@RequestParam(value = CRUD_PARAMETERS, required = true) String xmlCrudParameters) {
		return super.update(xmlCrudParameters, TestPlanRendered.class, TestPlan.class);
	}

	@RequestMapping(value = PROTECTED_SERVICE + "/testplan/create")
	protected ModelAndView create(@RequestParam(value = CRUD_PARAMETERS, required = true) String xmlCrudParameters) {
		return super.create(xmlCrudParameters, TestPlanRendered.class, TestPlan.class);
	}

	@Override
	protected ITestPlan beforeCreate(ITestPlan model) {
		return setDate(setUser(model));
	}

	private ITestPlan setUser(ITestPlan testPlan) {
		testPlan.setCreatedBy((UserPojo) userService.getCurrentUser());

		return testPlan;
	}

	private ITestPlan setDate(ITestPlan testPlan) {
		testPlan.setCreatedDate(new Date());

		return testPlan;
	}

}

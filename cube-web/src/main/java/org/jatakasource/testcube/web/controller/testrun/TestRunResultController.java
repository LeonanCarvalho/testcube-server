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
package org.jatakasource.testcube.web.controller.testrun;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jatakasource.common.model.security.UserPojo;
import org.jatakasource.common.svc.CRUDService;
import org.jatakasource.testcube.model.testrun.ITestRunResult;
import org.jatakasource.testcube.model.testrun.RunStatus;
import org.jatakasource.testcube.model.testrun.RunStatusEnum;
import org.jatakasource.testcube.model.testrun.RunStatusPerDate;
import org.jatakasource.testcube.model.testrun.TestRunResult;
import org.jatakasource.testcube.svc.security.UserService;
import org.jatakasource.testcube.svc.testrun.RunStatusService;
import org.jatakasource.testcube.svc.testrun.TestRunResultService;
import org.jatakasource.testcube.web.controller.ApplicationMessagesController;
import org.jatakasource.testcube.web.controller.GeneralProperties;
import org.jatakasource.testcube.web.xml.testrun.RunStatusPerDateRendered;
import org.jatakasource.testcube.web.xml.testrun.RunStatusPerDateRenderedList;
import org.jatakasource.testcube.web.xml.testrun.TestRunResultGridParameters;
import org.jatakasource.testcube.web.xml.testrun.TestRunResultRendered;
import org.jatakasource.testcube.web.xml.testrun.TestRunResultRenderedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestRunResultController extends ApplicationMessagesController<ITestRunResult, Long> {
	private static final Logger logger = Logger.getLogger(TestRunResultController.class);
	private static final String RUN_STATUS = "RUN_STATUS";

	@Autowired
	private TestRunResultService testRunResultService;
	@Autowired
	private RunStatusService runStatusService;
	@Autowired
	private UserService userService;

	@Override
	protected CRUDService<ITestRunResult, Long> getCrudService() {
		return testRunResultService;
	}

	@Override
	protected String getModelName() {
		return getMessages().getMessage(TestRunResultProperties.class.getName() + "." + TestRunResultProperties.MODEL_NAME.name());
	}

	@RequestMapping(value = PROTECTED_SERVICE + "/runresult/all")
	protected ModelAndView all(@RequestParam(value = GRID_PARAMETERS, required = true) String xmlGridParameters) {
		TestRunResultGridParameters gridParameters = null;

		if (StringUtils.isNotEmpty(xmlGridParameters)) {
			gridParameters = getGridParameters(xmlGridParameters, TestRunResultGridParameters.class);
		}

		// Return all runs According to paging and sorting
		PagingAndSorting pagingAndSorting = getPagingAndSorting(gridParameters);
		List<ITestRunResult> runResults = testRunResultService.getAll(pagingAndSorting.getPaging(), pagingAndSorting.getSorting());

		// Convert all RestRunResults to TestRunResultRendered
		TestRunResultRenderedList testRunResultRenderedList = getAsRenderer(runResults, TestRunResultRendered.class, ITestRunResult.class, TestRunResultRenderedList.class,
				gridParameters);

		return getXMLViewer(testRunResultRenderedList);
	}

	@RequestMapping(value = PROTECTED_SERVICE + "/runresult/search")
	protected ModelAndView search(@RequestParam(value = GRID_PARAMETERS, required = true) String xmlGridParameters) {
		logger.trace("Request for search test-runs with parameters " + xmlGridParameters + "!!!");

		TestRunResultGridParameters gridParameters = null;
		String keyword = null;
		Long testRunId = null;
		Long testPlanId = null;

		if (StringUtils.isNotEmpty(xmlGridParameters)) {
			gridParameters = getGridParameters(xmlGridParameters, TestRunResultGridParameters.class);
			keyword = gridParameters.getKeyword();
			testRunId = gridParameters.getTestRunId();
			testPlanId = gridParameters.getTestPlanId();
		}

		PagingAndSorting pagingAndSorting = getPagingAndSorting(gridParameters);
		List<ITestRunResult> runResults = null;

		// Return all runs according to keyword search and existing testrun
		if (testRunId != null) {
			runResults = testRunResultService.getAll(pagingAndSorting.getPaging(), pagingAndSorting.getSorting(), keyword, testRunId, null);
		} else {
			// Prepare new RestRunResults list according to testPlanId
			runResults = testRunResultService.getAll(pagingAndSorting.getPaging(), pagingAndSorting.getSorting(), testPlanId);
		}

		// Convert all RestRunResults to TestRunResultRendered
		TestRunResultRenderedList testRunResultRenderedList = getAsRenderer(runResults, TestRunResultRendered.class, ITestRunResult.class, TestRunResultRenderedList.class,
				gridParameters);

		return getXMLViewer(testRunResultRenderedList);
	}

	@Override
	@RequestMapping(value = PROTECTED_SERVICE + "/runresult/delete")
	protected ModelAndView delete(@RequestParam(value = CRUD_PARAMETERS, required = true) Long id) {
		return super.delete(id);
	}

	@RequestMapping(value = PROTECTED_SERVICE + "/runresult/add")
	protected ModelAndView create(@RequestParam(value = CRUD_PARAMETERS, required = true) String xmlCrudParameters) {
		return null;
	}

	@RequestMapping(value = PROTECTED_SERVICE + "/runresult/status")
	protected ModelAndView status(@RequestParam(value = CRUD_PARAMETERS, required = true) Long id, @RequestParam(value = RUN_STATUS, required = true) String status) {
		try {
			TestRunResult testRunResult = (TestRunResult) testRunResultService.get(id);
			testRunResult.setStatus((RunStatus) runStatusService.get(RunStatusEnum.valueOf(status)));
			beforeUpdate(testRunResult);
			testRunResultService.update(testRunResult);

			return getXMLViewer(getInfo(getMessages().getMessage(GeneralProperties.class.getName() + "." + GeneralProperties.INFO_TITLE.name()),
					getMessages().getMessage(TestRunResultProperties.class.getName() + "." + TestRunResultProperties.STATUS_SUCCEED.name(), getModelName())));
		} catch (Exception e) {
			return handleException(e, TestRunResultProperties.class.getName() + "." + TestRunResultProperties.STATUS_FAILED.name());
		}

	}

	@RequestMapping(value = PROTECTED_SERVICE + "/runresult/status/bydate")
	protected ModelAndView statusByDate() {
		List<RunStatusPerDate> statistics = testRunResultService.getStatusByDay();

		// Convert all RestRunResults to TestRunResultRendered
		RunStatusPerDateRenderedList runStatusPerDateRenderedList = getAsRenderer(statistics, RunStatusPerDateRendered.class, RunStatusPerDate.class,
				RunStatusPerDateRenderedList.class);

		return getXMLViewer(runStatusPerDateRenderedList);
	}

	@Override
	protected ITestRunResult beforeUpdate(ITestRunResult model) {
		model.setUpdatedDate(new Date());
		model.setUpdatedBy((UserPojo) userService.getCurrentUser());
		return model;
	}
}

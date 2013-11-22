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
package org.jatakasource.testcube.svc.testrun;

import java.util.List;

import org.jatakasource.common.model.paging.Paging;
import org.jatakasource.common.model.paging.Sorting;
import org.jatakasource.common.svc.CRUDService;
import org.jatakasource.testcube.model.testrun.ITestRunResult;
import org.jatakasource.testcube.model.testrun.RunStatusPerDate;

public interface TestRunResultService extends CRUDService<ITestRunResult, Long> {
	List<ITestRunResult> getAll(Paging paging, Sorting sorting, Long testPlanId);

	List<ITestRunResult> getAll(Paging paging, Sorting sorting, Long testRunId, Long runStatusId);
	
	List<ITestRunResult> getAll(Paging paging, Sorting sorting, String keyword, Long testRunId, Long runStatusId);
	
	List<RunStatusPerDate> getStatusByDay();
}

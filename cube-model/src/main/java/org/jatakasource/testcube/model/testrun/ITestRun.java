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
package org.jatakasource.testcube.model.testrun;

import java.util.Date;
import java.util.List;

import org.jatakasource.common.model.security.UserPojo;
import org.jatakasource.testcube.model.INameDescription;
import org.jatakasource.testcube.model.product.BuildPojo;
import org.jatakasource.testcube.model.product.EnvironmentPojo;
import org.jatakasource.testcube.model.product.VersionPojo;
import org.jatakasource.testcube.model.testplan.TestPlanPojo;

public interface ITestRun extends INameDescription<Long> {

	TestPlanPojo getTestPlan();

	void setTestPlan(TestPlanPojo testPlan);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	UserPojo getAssignee();

	void setAssignee(UserPojo assignee);

	UserPojo getCreatedBy();

	void setCreatedBy(UserPojo createdBy);

	VersionPojo getProductVersion();

	void setProductVersion(VersionPojo productVersion);

	BuildPojo getProductBuild();

	void setProductBuild(BuildPojo productBuild);

	EnvironmentPojo getProductEnvironment();

	void setProductEnvironment(EnvironmentPojo productEnvironment);

	RunStatusPojo getStatus();

	void setStatus(RunStatusPojo status);

	<T extends TestRunResultPojo> List<T> getResults();

	<T extends TestRunResultPojo> void setResults(List<T> results);

}

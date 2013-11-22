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

import java.util.List;

import org.jatakasource.testcube.model.AssignablePojo;
import org.jatakasource.testcube.model.product.BuildPojo;
import org.jatakasource.testcube.model.product.EnvironmentPojo;
import org.jatakasource.testcube.model.product.VersionPojo;
import org.jatakasource.testcube.model.testplan.TestPlanPojo;

public class TestRunPojo extends AssignablePojo<Long> implements ITestRun {
	private static final long serialVersionUID = -748040279742237455L;

	private Long id;
	private TestPlanPojo testPlan;

	private VersionPojo productVersion;
	private BuildPojo productBuild;
	private EnvironmentPojo productEnvironment;

	private RunStatusPojo status;
	private List<TestRunResultPojo> results;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public TestPlanPojo getTestPlan() {
		return testPlan;
	}

	@Override
	public void setTestPlan(TestPlanPojo testPlan) {
		this.testPlan = testPlan;
	}

	@Override
	public VersionPojo getProductVersion() {
		return productVersion;
	}

	@Override
	public void setProductVersion(VersionPojo productVersion) {
		this.productVersion = productVersion;
	}

	@Override
	public BuildPojo getProductBuild() {
		return productBuild;
	}

	@Override
	public void setProductBuild(BuildPojo productBuild) {
		this.productBuild = productBuild;
	}

	@Override
	public EnvironmentPojo getProductEnvironment() {
		return productEnvironment;
	}

	@Override
	public void setProductEnvironment(EnvironmentPojo productEnvironment) {
		this.productEnvironment = productEnvironment;
	}

	@Override
	public RunStatusPojo getStatus() {
		return status;
	}

	@Override
	public void setStatus(RunStatusPojo status) {
		this.status = status;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends TestRunResultPojo> List<T> getResults() {
		return (List<T>) results;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends TestRunResultPojo> void setResults(List<T> results) {
		this.results = (List<TestRunResultPojo>) results;
	}
}

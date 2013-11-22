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

public class TestRunStatisticsPojo implements ITestRunStatistics {

	private TestRunPojo testRun;
	private RunStatusPojo status;
	private Long count;

	public TestRunStatisticsPojo() {

	}

	public TestRunStatisticsPojo(Long count, TestRunPojo testRun, RunStatusPojo status) {
		super();
		this.testRun = testRun;
		this.status = status;
		this.count = count;
	}

	public TestRunPojo getTestRun() {
		return testRun;
	}

	public void setTestRun(TestRunPojo testRun) {
		this.testRun = testRun;
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
	public Long getCount() {
		return count;
	}

	@Override
	public void setCount(Long count) {
		this.count = count;
	}

}

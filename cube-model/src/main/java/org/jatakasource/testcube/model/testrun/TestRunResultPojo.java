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

import org.jatakasource.common.model.BasePojo;
import org.jatakasource.common.model.security.UserPojo;
import org.jatakasource.testcube.model.testcase.TestCasePojo;

public class TestRunResultPojo extends BasePojo<Long> implements ITestRunResult {
	private static final long serialVersionUID = 6367241146849249562L;

	private Long id;
	private TestRunPojo testRun;
	private TestCasePojo testcase;
	private RunStatusPojo status;
	private Date createdDate;
	private Date updatedDate;
	private UserPojo updatedBy;

	public TestRunResultPojo() {

	}

	public TestRunResultPojo(TestRunPojo testRun, TestCasePojo testcase, RunStatusPojo status) {
		super();
		this.testRun = testRun;
		this.testcase = testcase;
		this.status = status;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public TestRunPojo getTestRun() {
		return testRun;
	}

	@Override
	public void setTestRun(TestRunPojo testRun) {
		this.testRun = testRun;
	}

	@Override
	public TestCasePojo getTestcase() {
		return testcase;
	}

	@Override
	public void setTestcase(TestCasePojo testcase) {
		this.testcase = testcase;
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
	public Date getCreatedDate() {
		return createdDate;
	}

	@Override
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public Date getUpdatedDate() {
		return updatedDate;
	}

	@Override
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public UserPojo getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(UserPojo updatedBy) {
		this.updatedBy = updatedBy;
	}
}

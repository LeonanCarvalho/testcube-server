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

import org.jatakasource.common.model.IDomainObject;
import org.jatakasource.common.model.security.UserPojo;
import org.jatakasource.testcube.model.testcase.TestCasePojo;

public interface ITestRunResult extends IDomainObject<Long> {

	TestRunPojo getTestRun();

	void setTestRun(TestRunPojo testRun);

	TestCasePojo getTestcase();

	void setTestcase(TestCasePojo testcase);

	RunStatusPojo getStatus();

	void setStatus(RunStatusPojo status);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();

	void setUpdatedDate(Date createdDate);
	
	UserPojo getUpdatedBy();

	void setUpdatedBy(UserPojo createdBy);
}

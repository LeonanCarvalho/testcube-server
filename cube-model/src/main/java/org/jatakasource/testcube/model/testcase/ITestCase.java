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
package org.jatakasource.testcube.model.testcase;

import java.util.Date;
import java.util.List;

import org.jatakasource.common.model.security.UserPojo;
import org.jatakasource.testcube.model.INameDescription;
import org.jatakasource.testcube.model.product.CategoryPojo;
import org.jatakasource.testcube.model.testplan.TestPlanPojo;

public interface ITestCase extends INameDescription<Long> {

	TestPlanPojo getTestPlan();

	void setTestPlan(TestPlanPojo testPlan);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	UserPojo getCreatedBy();

	void setCreatedBy(UserPojo createdBy);

	UserPojo getAssignee();

	void setAssignee(UserPojo assignee);

	CasePriorityPojo getPriority();

	void setPriority(CasePriorityPojo priority);

	CategoryPojo getCategory();

	void setCategory(CategoryPojo category);

	String getEstimatedTime();

	void setEstimatedTime(String estimatedTime);

	String getBugs();

	void setBugs(String bugs);

	CaseStatusPojo getStatus();

	void setStatus(CaseStatusPojo status);

	String getLabels();

	void setLabels(String labels);

	String getRequirements();

	void setRequirements(String requirements);

	String getSetup();

	void setSetup(String setup);

	String getAction();

	void setAction(String action);

	String getExpected();

	void setExpected(String expected);

	<T extends CaseAttachmentPojo> List<T> getAttachments();

	<T extends CaseAttachmentPojo> void setAttachments(List<T> attachments);
}

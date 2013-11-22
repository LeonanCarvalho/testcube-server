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
package org.jatakasource.testcube.web.xml.testcase;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.jatakasource.common.model.security.UserPojo;
import org.jatakasource.testcube.model.product.Category;
import org.jatakasource.testcube.model.testcase.CasePriority;
import org.jatakasource.testcube.model.testcase.CaseStatus;
import org.jatakasource.testcube.model.testcase.ITestCase;
import org.jatakasource.testcube.model.testcase.TestCasePojo;
import org.jatakasource.testcube.web.xml.NameDescriptionRendered;
import org.jatakasource.testcube.web.xml.users.UserRendered;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "TESTCASE")
public class TestCaseBasicRendered extends NameDescriptionRendered<ITestCase> {

	public TestCaseBasicRendered() {

	}

	public TestCaseBasicRendered(TestCasePojo testcase) {
		super(testcase);
	}

	@Element(name = "CREATED_DATE", required = false)
	public Date getCreatedDate() {
		return getDelegated().getCreatedDate();
	}

	@Element(name = "CREATED_DATE", required = false)
	public void setCreatedDate(Date createdDate) {
		getDelegated().setCreatedDate(createdDate);
	}

	@Element(name = "CREATED_BY", required = false)
	public String getCreatedBy() {
		return getDelegated().getCreatedBy() != null ? getDelegated().getCreatedBy().getUsername() : StringUtils.EMPTY;
	}

	@Element(name = "CREATED_BY", required = false)
	public void setCreatedBy(String createdBy) {
		// EMPTY XML method
	}

	@Element(name = "ASSIGNEE", required = false)
	public UserRendered getAssignee() {
		if (getDelegated().getAssignee() != null)
			return new UserRendered(getDelegated().getAssignee());
		return null;
	}

	@Element(name = "ASSIGNEE", required = false)
	public void setAssignee(UserRendered assignee) {
		getDelegated().setAssignee((UserPojo) assignee.getDelegated());
	}

	@Attribute(required = false)
	public Long getPriorityId() {
		if (getDelegated().getPriority() != null)
			return getDelegated().getPriority().getId();

		return null;
	}

	@Attribute(required = false)
	public void setPriorityId(Long priorityId) {
		if (isItemSelected(priorityId))
			getDelegated().setPriority(new CasePriority(priorityId));
		else
			getDelegated().setPriority(null);
	}
	
	@Element(name = "PRIORITY_NAME", required = false, data = true)
	public String getPriorityName() {
		if (getDelegated().getPriority() != null)
			return getDelegated().getPriority().getName();

		return null;
	}

	@Element(name = "PRIORITY_NAME", required = false, data = true)
	public void setPriorityName(String testPlanName) {
		// EMPTY XML method
	}

	@Attribute(required = false)
	public Long getCategoryId() {
		if (getDelegated().getCategory() != null)
			return getDelegated().getCategory().getId();

		return null;
	}

	@Attribute(required = false)
	public void setCategoryId(Long categoryId) {
		if (isItemSelected(categoryId))
			getDelegated().setCategory(new Category(categoryId));
		else
			getDelegated().setCategory(null);
	}

	@Element(name = "CATEGORY_NAME", required = false, data = true)
	public String getCategoryName() {
		if (getDelegated().getCategory() != null)
			return getDelegated().getCategory().getName();

		return null;
	}

	@Element(name = "CATEGORY_NAME", required = false, data = true)
	public void setCategoryName(String testPlanName) {
		// EMPTY XML method
	}
	
	@Element(name = "ESTIMATED_TIME", required = false)
	public String getEstimatedTime() {
		return getDelegated().getEstimatedTime();
	}

	@Element(name = "ESTIMATED_TIME", required = false)
	public void setEstimatedTime(String estimatedTime) {
		getDelegated().setEstimatedTime(estimatedTime);
	}

	@Element(name = "BUGS", required = false, data = true)
	public String getBugs() {
		return getDelegated().getBugs();
	}

	@Element(name = "BUGS", required = false, data = true)
	public void setBugs(String bugs) {
		getDelegated().setBugs(bugs);
	}

	@Attribute(required = false)
	public Long getStatusId() {
		if (getDelegated().getStatus() != null)
			return getDelegated().getStatus().getId();

		return null;
	}

	@Attribute(required = false)
	public void setStatusId(Long statusId) {
		if (isItemSelected(statusId))
			getDelegated().setStatus(new CaseStatus(statusId));
		else
			getDelegated().setStatus(null);
	}
}

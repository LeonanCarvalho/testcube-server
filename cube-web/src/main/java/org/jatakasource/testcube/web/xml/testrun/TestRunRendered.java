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
package org.jatakasource.testcube.web.xml.testrun;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.jatakasource.common.model.security.UserPojo;
import org.jatakasource.testcube.model.product.BuildPojo;
import org.jatakasource.testcube.model.product.EnvironmentPojo;
import org.jatakasource.testcube.model.product.VersionPojo;
import org.jatakasource.testcube.model.testplan.TestPlan;
import org.jatakasource.testcube.model.testrun.ITestRun;
import org.jatakasource.testcube.model.testrun.RunStatusPojo;
import org.jatakasource.testcube.web.xml.product.BuildRendered;
import org.jatakasource.testcube.web.xml.product.EnvironmentRendered;
import org.jatakasource.testcube.web.xml.product.VersionRendered;
import org.jatakasource.testcube.web.xml.users.UserRendered;
import org.jatakasource.web.xml.rendered.XMLRendererImpl;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "TESTRUN")
public class TestRunRendered extends XMLRendererImpl<ITestRun> {

	@Attribute(required = false)
	public Long getId() {
		return getDelegated().getId();
	}

	@Attribute(required = false)
	public void setId(Long id) {
		getDelegated().setId(id);
	}
	
	@Attribute(required = true)
	public Long getTestPlanId() {
		if (getDelegated().getTestPlan() != null)
			return getDelegated().getTestPlan().getId();

		return null;
	}

	@Attribute(required = true)
	public void setTestPlanId(Long testPlanId) {
		getDelegated().setTestPlan(new TestPlan(testPlanId));
	}

	@Attribute(required = true)
	public Long getProductId() {
		if (getDelegated().getTestPlan() != null && getDelegated().getTestPlan().getProduct().getId() != null)
			return getDelegated().getTestPlan().getProduct().getId();

		return null;
	}

	@Attribute(required = true)
	public void setProductId(Long productId) {
		// Empty XML setter
	}

	@Element(name = "TEST_PLAN_NAME", required = false, data = true)
	public String getTestPlanName() {
		if (getDelegated().getTestPlan() != null)
			return getDelegated().getTestPlan().getName();

		return null;
	}

	@Element(name = "TEST_PLAN_NAME", required = false, data = true)
	public void setTestPlanName(String testPlanName) {
		// EMPTY XML method
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

	@Element(name = "VERSION", required = true)
	public VersionRendered getProductVersion() {
		if (getDelegated().getProductVersion() != null)
			return new VersionRendered(getDelegated().getProductVersion());
		return null;
	}

	@Element(name = "VERSION", required = true)
	public void setProductVersion(VersionRendered productVersion) {
		getDelegated().setProductVersion((VersionPojo) productVersion.getDelegated());
	}

	@Element(name = "BUILD", required = false)
	public BuildRendered getProductBuild() {
		if (getDelegated().getProductBuild() != null)
			return new BuildRendered(getDelegated().getProductBuild());
		return null;
	}

	@Element(name = "BUILD", required = false)
	public void setProductBuild(BuildRendered productBuild) {
		getDelegated().setProductBuild((BuildPojo) productBuild.getDelegated());
	}

	@Element(name = "ENVIRONMENT", required = false)
	public EnvironmentRendered getProductEnvironment() {
		if (getDelegated().getProductEnvironment() != null)
			return new EnvironmentRendered(getDelegated().getProductEnvironment());
		return null;
	}

	@Element(name = "ENVIRONMENT", required = false)
	public void setProductEnvironment(EnvironmentRendered productEnvironment) {
		getDelegated().setProductEnvironment((EnvironmentPojo) productEnvironment.getDelegated());
	}

	@Element(name = "SUMMARY", required = true, data = true)
	public String getSummary() {
		return getDelegated().getName();
	}

	@Element(name = "SUMMARY", required = true, data = true)
	public void setSummary(String summary) {
		getDelegated().setName(summary);
	}

	@Element(name = "NOTES", required = false, data = false)
	public String getNotes() {
		return getDelegated().getDescription();
	}

	@Element(name = "NOTES", required = false, data = false)
	public void setNotes(String notes) {
		getDelegated().setDescription(notes);
	}

	// Status is optional to allow new runs without status
	@Element(name = "STATUS", required = false)
	public RunStatusRendered getStatus() {
		if (getDelegated().getStatus() != null)
			return new RunStatusRendered(getDelegated().getStatus());
		return null;
	}

	@Element(name = "STATUS", required = false)
	public void setStatus(RunStatusRendered status) {
		getDelegated().setStatus((RunStatusPojo) status.getDelegated());
	}

}

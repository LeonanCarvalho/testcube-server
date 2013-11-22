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

import org.jatakasource.testcube.model.testcase.TestCasePojo;
import org.jatakasource.testcube.model.testplan.TestPlan;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "TESTCASE")
public class TestCaseRendered extends TestCaseBasicRendered {

	public TestCaseRendered() {

	}

	public TestCaseRendered(TestCasePojo testcase) {
		super(testcase);
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

	@Element(name = "LABELS", required = false, data = true)
	public String getLabels() {
		return getDelegated().getLabels();
	}

	@Element(name = "LABELS", required = false, data = true)
	public void setLabels(String labels) {
		getDelegated().setLabels(labels);
	}

	@Element(name = "REQUIREMENTS", required = false, data = true)
	public String getRequirements() {
		return getDelegated().getRequirements();
	}

	@Element(name = "REQUIREMENTS", required = false, data = true)
	public void setRequirements(String requirements) {
		getDelegated().setRequirements(requirements);
	}

	@Element(name = "SETUP", required = false, data = true)
	public String getSetup() {
		return getDelegated().getSetup();
	}

	@Element(name = "SETUP", required = false, data = true)
	public void setSetup(String setup) {
		getDelegated().setSetup(setup);
	}

	@Element(name = "ACTION", required = false, data = true)
	public String getAction() {
		return getDelegated().getAction();
	}

	@Element(name = "ACTION", required = false, data = true)
	public void setAction(String action) {
		getDelegated().setAction(action);
	}

	@Element(name = "EXPECTED", required = false, data = true)
	public String getExpected() {
		return getDelegated().getExpected();
	}

	@Element(name = "EXPECTED", required = false, data = true)
	public void setExpected(String expected) {
		getDelegated().setExpected(expected);
	}
}

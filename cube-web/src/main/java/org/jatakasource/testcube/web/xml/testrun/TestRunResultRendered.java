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

import org.jatakasource.testcube.model.testcase.TestCasePojo;
import org.jatakasource.testcube.model.testrun.ITestRunResult;
import org.jatakasource.testcube.model.testrun.RunStatusPojo;
import org.jatakasource.testcube.web.xml.testcase.TestCaseRendered;
import org.jatakasource.testcube.web.xml.users.UserRendered;
import org.jatakasource.web.xml.rendered.XMLRendererImpl;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "TESTRUN_RESULT")
public class TestRunResultRendered extends XMLRendererImpl<ITestRunResult> {

	@Attribute(required = false)
	public Long getId() {
		return getDelegated().getId();
	}

	@Attribute(required = false)
	public void setId(Long id) {
		getDelegated().setId(id);
	}

	@Element(name = "STATUS", required = true)
	public RunStatusRendered getStatus() {
		if (getDelegated().getStatus() != null)
			return new RunStatusRendered(getDelegated().getStatus());
		return null;
	}

	@Element(name = "STATUS", required = true)
	public void setStatus(RunStatusRendered status) {
		getDelegated().setStatus((RunStatusPojo) status.getDelegated());
	}

	@Element(name = "TESTCASE", required = true)
	public TestCaseRendered getTestCase() {
		if (getDelegated().getTestcase() != null)
			return new TestCaseRendered(getDelegated().getTestcase());
		return null;
	}

	@Element(name = "TESTCASE", required = true)
	public void setTestCase(TestCaseRendered testcase) {
		getDelegated().setTestcase((TestCasePojo) testcase.getDelegated());
	}
	
	@Element(name = "UPDATED_BY", required = false)
	public UserRendered getUpdatedBy() {
		if (getDelegated().getUpdatedBy() != null)
			return new UserRendered(getDelegated().getUpdatedBy());
		return null;
	}

	@Element(name = "UPDATED_BY", required = false)
	public void setUpdatedBy(UserRendered assignee) {
		// Empty XML Rendered
	}

}

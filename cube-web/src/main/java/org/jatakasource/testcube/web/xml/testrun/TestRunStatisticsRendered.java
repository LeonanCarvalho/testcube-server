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

import org.jatakasource.testcube.model.testrun.ITestRunStatistics;
import org.jatakasource.testcube.model.testrun.TestRunStatisticsPojo;
import org.jatakasource.web.xml.rendered.XMLRendererImpl;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "RUN_STATISTICS")
public class TestRunStatisticsRendered extends XMLRendererImpl<ITestRunStatistics> {
	public TestRunStatisticsRendered() {
		super(new TestRunStatisticsPojo());
	}

	public TestRunStatisticsRendered(ITestRunStatistics delegated) {
		super(delegated);
	}

	@Attribute
	public Long getCount() {
		return getDelegated().getCount();
	}

	@Attribute
	public void setCount(Long count) {
		getDelegated().setCount(count);
	}

	@Element(name = "STATUS")
	public RunStatusRendered getStatus() {
		return new RunStatusRendered(getDelegated().getStatus());
	}

	@Element(name = "STATUS")
	public void setStatus(RunStatusRendered status) {
		// Empty XML setter
	}
}

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

import org.jatakasource.testcube.model.testcase.ICaseStatus;
import org.jatakasource.testcube.web.xml.NameDescriptionRendered;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "CASE_STATUS")
public class CaseStatusRendered extends NameDescriptionRendered<ICaseStatus> {

	@Attribute(required = true)
	public int getOrder() {
		return getDelegated().getOrderWeight();
	}

	@Attribute(required = true)
	public void setOrder(int order) {
		getDelegated().setOrderWeight(order);
	}
}

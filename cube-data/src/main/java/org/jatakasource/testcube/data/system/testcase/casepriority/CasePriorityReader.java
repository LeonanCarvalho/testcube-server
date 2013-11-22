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
package org.jatakasource.testcube.data.system.testcase.casepriority;

import org.jatakasource.common.data.DataReader;
import org.jatakasource.testcube.model.testcase.CasePriority;

public class CasePriorityReader extends DataReader<CasePriority> {
	private CasePriority casePriority;

	public CasePriorityReader() {
		casePriority = new CasePriority();
	}

	public CasePriorityReader(CasePriority casePriority) {
		this.casePriority = casePriority;
	}

	public CasePriority getDelegated() {
		return casePriority;
	}

	public String getName() {
		return casePriority.getName();
	}

	public void setName(String name) {
		casePriority.setName(name);
	}

	public String getDescription() {
		return casePriority.getDescription();
	}

	public void setDescription(String description) {
		casePriority.setDescription(description);
	}

	public int getOrder() {
		return casePriority.getOrderWeight();
	}

	public void setOrder(int order) {
		casePriority.setOrderWeight(order);
	}
}

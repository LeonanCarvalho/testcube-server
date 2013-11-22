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
package org.jatakasource.testcube.data.system.testcase.casestatus;

import org.jatakasource.common.data.DataReader;
import org.jatakasource.testcube.model.testcase.CaseStatus;

public class CaseStatusReader extends DataReader<CaseStatus> {
	private CaseStatus caseStatus;

	public CaseStatusReader() {
		caseStatus = new CaseStatus();
	}

	public CaseStatusReader(CaseStatus caseStatus) {
		this.caseStatus = caseStatus;
	}

	public CaseStatus getDelegated() {
		return caseStatus;
	}

	public String getName() {
		return caseStatus.getName();
	}

	public void setName(String name) {
		caseStatus.setName(name);
	}

	public String getDescription() {
		return caseStatus.getDescription();
	}

	public void setDescription(String description) {
		caseStatus.setDescription(description);
	}

	public int getOrder() {
		return caseStatus.getOrderWeight();
	}

	public void setOrder(int order) {
		caseStatus.setOrderWeight(order);
	}
}

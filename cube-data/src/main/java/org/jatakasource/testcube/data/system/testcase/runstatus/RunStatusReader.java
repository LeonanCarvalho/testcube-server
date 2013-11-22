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
package org.jatakasource.testcube.data.system.testcase.runstatus;

import org.jatakasource.common.data.DataReader;
import org.jatakasource.testcube.model.testrun.RunStatus;
import org.jatakasource.testcube.model.testrun.RunStatusEnum;

public class RunStatusReader extends DataReader<RunStatus> {
	private RunStatus runStatus;

	public RunStatusReader() {
		runStatus = new RunStatus();
	}

	public RunStatusReader(RunStatus runStatus) {
		this.runStatus = runStatus;
	}

	public RunStatus getDelegated() {
		return runStatus;
	}

	public String getName() {
		return runStatus.getName();
	}

	public void setName(String name) {
		runStatus.setName(name);
	}

	public String getDescription() {
		return runStatus.getDescription();
	}

	public void setDescription(String description) {
		runStatus.setDescription(description);
	}

	public int getOrder() {
		return runStatus.getOrderWeight();
	}

	public void setOrder(int order) {
		runStatus.setOrderWeight(order);
	}

	public String getStatus() {
		return runStatus.getStatus().name();
	}

	public void setStatus(String status) {
		runStatus.setStatus(RunStatusEnum.valueOf(status));
	}

}

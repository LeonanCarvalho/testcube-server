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
package org.jatakasource.testcube.model.testrun;

import org.jatakasource.testcube.model.NameDescriptionPojo;

public class RunStatusPojo extends NameDescriptionPojo<Long> implements IRunStatus {
	private static final long serialVersionUID = 2156330359833863667L;

	private Long id;
	private int orderWeight;
	private RunStatusEnum status;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int getOrderWeight() {
		return orderWeight;
	}

	@Override
	public void setOrderWeight(int orderWeight) {
		this.orderWeight = orderWeight;
	}

	@Override
	public RunStatusEnum getStatus() {
		return status;
	}

	@Override
	public void setStatus(RunStatusEnum status) {
		this.status = status;
	}
}

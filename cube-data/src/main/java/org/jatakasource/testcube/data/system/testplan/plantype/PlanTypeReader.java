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
package org.jatakasource.testcube.data.system.testplan.plantype;

import org.jatakasource.common.data.DataReader;
import org.jatakasource.testcube.model.testplan.PlanType;

public class PlanTypeReader extends DataReader<PlanType> {
	private PlanType planType;

	public PlanTypeReader() {
		planType = new PlanType();
	}

	public PlanTypeReader(PlanType planType) {
		this.planType = planType;
	}

	public PlanType getDelegated() {
		return planType;
	}

	public String getName() {
		return planType.getName();
	}

	public void setName(String name) {
		planType.setName(name);
	}

	public String getDescription() {
		return planType.getDescription();
	}

	public void setDescription(String description) {
		planType.setDescription(description);
	}
}

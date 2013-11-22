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
package org.jatakasource.testcube.data.system.parameters;

import org.jatakasource.common.data.DataReader;
import org.jatakasource.testcube.model.parameters.DBParameter;

public class DBParameterReader extends DataReader<DBParameter> {
	private DBParameter parameter;

	public DBParameterReader() {
		parameter = new DBParameter();
	}

	public DBParameterReader(DBParameter parameter) {
		this.parameter = parameter;
	}

	public DBParameter getDelegated() {
		return parameter;
	}

	public String getId() {
		return parameter.getId();
	}

	public void setId(String id) {
		parameter.setId(id);
	}

	public String getValue() {
		return parameter.getValue();
	}

	public void setValue(String value) {
		parameter.setValue(value);
	}

	public String getValidatorClass() {
		return parameter.getValidatorClass();
	}

	public void setValidatorClass(String validatorClass) {
		parameter.setValidatorClass(validatorClass);
	}

	public String getDescription() {
		return parameter.getDescription();
	}

	public void setDescription(String description) {
		parameter.setDescription(description);
	}

	public boolean isUpdateble() {
		return parameter.isUpdateble();
	}

	public void setUpdateble(boolean updateble) {
		parameter.setUpdateble(updateble);
	}

}

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
package org.jatakasource.testcube.model.parameters;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;
import org.jatakasource.common.model.IDomainObject;
import org.jatakasource.common.model.parameters.DBParameterPojo;

@Entity
@Table(name = "CUB_PARAMETERS")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DBParameter extends DBParameterPojo implements IDomainObject<String> {
	private static final long serialVersionUID = -6092780208254700974L;
	
	public DBParameter() {

	}

	public DBParameter(String id, String value, String description) {
		super(id, value, description);
	}

	@Id
	@Override
	public String getId() {
		return super.getId();
	}

	@Override
	public void setId(String id) {
		super.setId(id);
	}

	@Override
	@Column(nullable = false, length = FIELD_DESC_LENGTH)
	public String getValue() {
		return super.getValue();
	}

	@Override
	public void setValue(String value) {
		super.setValue(value);
	}

	@Override
	@Column(nullable = false, length = FIELD_DESC_LENGTH)
	public String getValidatorClass() {
		return super.getValidatorClass();
	}

	@Override
	public void setValidatorClass(String validatorClass) {
		super.setValidatorClass(validatorClass);
	}

	@Override
	@Column(nullable = false, length = FIELD_DESC_LENGTH)
	@Index(name = "IDX_CUB_PARAMETERS_DESC")
	public String getDescription() {
		return super.getDescription();
	}

	@Override
	public void setDescription(String description) {
		super.setDescription(description);
	}

	@Override
	@Column(nullable = false)
	public Boolean isUpdateble() {
		return super.isUpdateble();
	}

	@Override
	public void setUpdateble(Boolean updateble) {
		super.setUpdateble(updateble);
	}
}

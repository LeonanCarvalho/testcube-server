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
package org.jatakasource.testcube.model.testcase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.jatakasource.testcube.model.testcase.CaseStatusPojo;

@Entity
@Table(name = "CUB_CASE_STATUS")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CaseStatus extends CaseStatusPojo {
	private static final long serialVersionUID = 2832439407107740197L;
	private static final String FIELD_ORDER_WEIGHT = "orderWeight";
	private static final String[][] UNIQUE_CONSTRAINTS = new String[][] { new String[] { CaseStatus.FIELD_NAME }, new String[] { CaseStatus.FIELD_DESC },
			new String[] { CaseStatus.FIELD_ORDER_WEIGHT } };

	public CaseStatus() {

	}

	public CaseStatus(Long id) {
		setId(id);
	}

	@Id
	@SequenceGenerator(name = "CUB_CASE_STATUS_SEQ", sequenceName = "CUB_CASE_STATUS_SEQ", initialValue = 0)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CUB_CASE_STATUS_SEQ")
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	@Override
	@Column(unique = true, length = FIELD_NAME_LENGTH, nullable = false)
	public String getName() {
		return super.getName();
	}

	@Override
	public void setName(String name) {
		super.setName(name);
	}

	@Override
	@Column(unique = true, length = FIELD_DESC_LENGTH, nullable = false)
	public String getDescription() {
		return super.getDescription();
	}

	@Override
	public void setDescription(String description) {
		super.setDescription(description);
	}

	@Override
	@Column(nullable = false, unique = true)
	public int getOrderWeight() {
		return super.getOrderWeight();
	}

	public void setOrderWeight(int orderWeight) {
		super.setOrderWeight(orderWeight);
	}

	@Override
	public String[][] uniqueConstraints() {
		return UNIQUE_CONSTRAINTS;
	}
}

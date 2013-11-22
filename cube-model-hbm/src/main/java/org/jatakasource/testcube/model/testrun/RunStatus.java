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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "CUB_RUN_STATUS")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RunStatus extends RunStatusPojo {
	private static final long serialVersionUID = 2832439407107740197L;
	public static final String FIELD_STATUS = "status";
	private static final String FIELD_ORDER_WEIGHT = "orderWeight";
	
	private static final String[][] UNIQUE_CONSTRAINTS = new String[][] { new String[] { RunStatus.FIELD_NAME }, new String[] { RunStatus.FIELD_DESC },
		new String[] { RunStatus.FIELD_ORDER_WEIGHT } };
	
	public RunStatus() {

	}

	public RunStatus(Long id) {
		this.setId(id);
	}

	@Id
	@SequenceGenerator(name = "CUB_RUN_STATUS_SEQ", sequenceName = "CUB_RUN_STATUS_SEQ", initialValue = 0)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CUB_RUN_STATUS_SEQ")
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
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, unique = true)
	public RunStatusEnum getStatus() {
		return super.getStatus();
	}

	@Override
	public void setStatus(RunStatusEnum status) {
		super.setStatus(status);
	}
	
	@Override
	public String[][] uniqueConstraints() {
		return UNIQUE_CONSTRAINTS;
	}
}

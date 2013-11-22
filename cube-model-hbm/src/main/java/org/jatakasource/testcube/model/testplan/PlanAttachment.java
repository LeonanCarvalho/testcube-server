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
package org.jatakasource.testcube.model.testplan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.jatakasource.testcube.model.testplan.PlanAttachmentPojo;

@Entity
@Table(name = "CUB_TESTPLAN_ATTACHMENTS")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PlanAttachment extends PlanAttachmentPojo {
	private static final long serialVersionUID = -8832802375109472431L;

	@Id
	@SequenceGenerator(name = "CUB_TESTPLAN_ATTACHMENTS_SEQ", sequenceName = "CUB_TESTPLAN_ATTACHMENTS_SEQ", initialValue = 0)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CUB_TESTPLAN_ATTACHMENTS_SEQ")
	@Override
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	@Override
	@Column(length = FIELD_NAME_LENGTH, nullable = false)
	public String getName() {
		return super.getName();
	}

	@Override
	public void setName(String name) {
		super.setName(name);
	}

	@Override
	@Column(length = FIELD_DESC_LENGTH)
	public String getDescription() {
		return super.getDescription();
	}

	@Override
	public void setDescription(String description) {
		super.setDescription(description);
	}

	@Override
	@Column(length = 4, nullable = false)
	public String getContentType() {
		return super.getContentType();
	}

	@Override
	public void setContentType(String contentType) {
		super.setContentType(contentType);
	}

	@Lob
	@Column(nullable = false)
	@Type(type = "org.hibernate.type.MaterializedBlobType")
	@Override
	public byte[] getContent() {
		return super.getContent();
	}

	@Override
	public void setContent(byte[] content) {
		super.setContent(content);
	}
}

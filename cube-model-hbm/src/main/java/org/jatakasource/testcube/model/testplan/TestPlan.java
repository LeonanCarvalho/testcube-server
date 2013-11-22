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

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;
import org.jatakasource.testcube.model.product.Product;
import org.jatakasource.testcube.model.product.Version;
import org.jatakasource.testcube.model.security.User;

@Entity
@Table(name = "CUB_TESTPLANS", uniqueConstraints = { @UniqueConstraint(name = "UC_NAME_PRODUCT", columnNames = { TestPlan.FIELD_NAME, TestPlan.COLUMN_PRODUCT }) })
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TestPlan extends TestPlanPojo {
	private static final long serialVersionUID = 2210656336860097791L;

	private static final String[][] UNIQUE_CONSTRAINTS = new String[][] { new String[] { TestPlan.FIELD_NAME, TestPlan.FIELD_PRODUCT } };
	
	public TestPlan() {

	}

	public TestPlan(Long id) {
		super.setId(id);
	}

	@Id
	@SequenceGenerator(name = "CUB_TESTPLANS_SEQ", sequenceName = "CUB_TESTPLANS_SEQ", initialValue = 0)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CUB_TESTPLANS_SEQ")
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
	@Index(name = "IDX_NAME")
	public String getName() {
		return super.getName();
	}

	@Override
	public void setName(String name) {
		super.setName(name);
	}

	@Override
	@Column(length = FIELD_DESC_LENGTH, nullable = false)
	@Index(name = "IDX_DESCRIPTION")
	public String getDescription() {
		return super.getDescription();
	}

	@Override
	public void setDescription(String description) {
		super.setDescription(description);
	}

	@Lob
	@Column
	@Type(type="org.hibernate.type.StringClobType")
	@Override
	public String getDocument() {
		return super.getDocument();
	}

	@Override
	public void setDocument(String document) {
		super.setDocument(document);
	}

	@Override
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@Fetch(FetchMode.SELECT)
	@ForeignKey(name = "FK_TESTPLANS_PRODUCTS")
	@Index(name = "IDX_TESTPLANS_PRODUCTS")
	public Product getProduct() {
		return (Product) super.getProduct();
	}

	public void setProduct(Product product) {
		super.setProduct(product);
	}

	@Override
	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedDate() {
		return super.getCreatedDate();
	}

	@Override
	public void setCreatedDate(Date createdDate) {
		super.setCreatedDate(createdDate);
	}

	@Override
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(updatable = false)
	@Fetch(FetchMode.SELECT)
	@ForeignKey(name = "FK_TESTPLANS_USERS")
	@Index(name = "IDX_TESTPLANS_USERS")
	public User getCreatedBy() {
		return (User) super.getCreatedBy();
	}

	public void setCreatedBy(User createdBy) {
		super.setCreatedBy(createdBy);
	}

	@Override
	@ManyToOne(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@ForeignKey(name = "FK_TESTPLANS_PLANTYPES")
	@Index(name = "FK_TESTPLANS_PLANTYPES")
	public PlanType getPlanType() {
		return (PlanType) super.getPlanType();
	}

	public void setPlanType(PlanType planType) {
		super.setPlanType(planType);
	}

	@Override
	@ManyToOne(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@ForeignKey(name = "FK_TESTPLANS_VERSIONS")
	@Index(name = "FK_TESTPLANS_VERSIONS")
	public Version getProductVersion() {
		return (Version) super.getProductVersion();
	}

	public void setProductVersion(Version productVersion) {
		super.setProductVersion(productVersion);
	}

	@Override
	@SuppressWarnings("unchecked")
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "CUB_TESTPLANS_ATTACHMENTS")
	@Fetch(FetchMode.SELECT)
	public List<PlanAttachment> getAttachments() {
		return super.getAttachments();
	}

	@Override
	public <T extends PlanAttachmentPojo> void setAttachments(List<T> attachments) {
		super.setAttachments(attachments);
	}
	
	@Override
	public String[][] uniqueConstraints() {
		return UNIQUE_CONSTRAINTS;
	}
}

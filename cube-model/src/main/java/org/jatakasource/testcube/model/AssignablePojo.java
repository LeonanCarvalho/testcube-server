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
package org.jatakasource.testcube.model;

import java.io.Serializable;
import java.util.Date;

import org.jatakasource.common.model.security.UserPojo;

public abstract class AssignablePojo<ID extends Serializable> extends NameDescriptionPojo<ID> {
	private static final long serialVersionUID = 5180545347204520633L;

	private Date createdDate;
	private UserPojo createdBy;
	private UserPojo assignee;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public UserPojo getAssignee() {
		return assignee;
	}

	public void setAssignee(UserPojo assignee) {
		this.assignee = assignee;
	}

	public UserPojo getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserPojo createdBy) {
		this.createdBy = createdBy;
	}

}

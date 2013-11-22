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
package org.jatakasource.testcube.model.product;

import java.util.List;

import org.jatakasource.common.model.security.UserPojo;
import org.jatakasource.testcube.model.ProductRelatedPojo;

public class ComponentPojo extends ProductRelatedPojo implements IComponent {
	private static final long serialVersionUID = 2092448509917138678L;

	private UserPojo defaultAssignee;
	private List<UserPojo> defaultCCList;

	@Override
	public UserPojo getDefaultAssignee() {
		return defaultAssignee;
	}

	@Override
	public void setDefaultAssignee(UserPojo defaultAssignee) {
		this.defaultAssignee = defaultAssignee;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends UserPojo> List<T> getDefaultCCList() {
		return (List<T>) defaultCCList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends UserPojo> void setDefaultCCList(List<T> defaultCCList) {
		this.defaultCCList = (List<UserPojo>) defaultCCList;
	}
}

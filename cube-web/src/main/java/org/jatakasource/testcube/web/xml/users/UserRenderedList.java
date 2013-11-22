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
package org.jatakasource.testcube.web.xml.users;

import java.util.ArrayList;
import java.util.List;

import org.jatakasource.web.xml.rendered.XMLListRenderer;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "USERS")
public class UserRenderedList extends XMLListRenderer<UserRendered> {
	@ElementList(inline = true, entry = "USER")
	private List<UserRendered> innerList;

	public UserRenderedList() {
		innerList = new ArrayList<UserRendered>();
	}

	@Override
	public List<UserRendered> getInnerList() {
		return innerList;
	}

	@Override
	public void setInnerList(List<UserRendered> innerList) {
		this.innerList = innerList;
	}
}

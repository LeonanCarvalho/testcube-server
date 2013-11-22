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
package org.jatakasource.testcube.web.xml.product;

import java.util.ArrayList;
import java.util.List;

import org.jatakasource.web.xml.rendered.XMLListRenderer;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "CATEGORIES")
public class CategoryRenderedList extends XMLListRenderer<CategoryRendered> {
	@ElementList(inline = true, entry = "CATEGORY")
	private List<CategoryRendered> innerList;

	public CategoryRenderedList() {
		innerList = new ArrayList<CategoryRendered>();
	}

	@Override
	public List<CategoryRendered> getInnerList() {
		return innerList;
	}

	@Override
	public void setInnerList(List<CategoryRendered> innerList) {
		this.innerList = innerList;
	}
}

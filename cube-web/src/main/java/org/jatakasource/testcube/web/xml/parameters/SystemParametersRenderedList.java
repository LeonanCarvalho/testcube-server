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
package org.jatakasource.testcube.web.xml.parameters;

import java.util.ArrayList;
import java.util.List;

import org.jatakasource.web.xml.rendered.XMLListRenderer;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "SYS_PARAMETERS")
public class SystemParametersRenderedList extends XMLListRenderer<SystemParameterRendered> {
	@ElementList(inline = true, entry = "SYS_PARAMETER")
	private List<SystemParameterRendered> innerList;

	public SystemParametersRenderedList() {
		innerList = new ArrayList<SystemParameterRendered>();
	}

	@Override
	public List<SystemParameterRendered> getInnerList() {
		return innerList;
	}

	@Override
	public void setInnerList(List<SystemParameterRendered> innerList) {
		this.innerList = innerList;
	}
}

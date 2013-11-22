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

import org.jatakasource.common.model.parameters.ISystemParameter;
import org.jatakasource.web.xml.rendered.XMLRendererImpl;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

public class AbstractParameterRendered<T extends ISystemParameter> extends XMLRendererImpl<T> {
	@Attribute
	public String getId() {
		return getDelegated().getId();
	}

	@Attribute
	public void setId(String id) {
		getDelegated().setId(id);
	}
	
	@Element(name = "VALUE", data = true, required = true)
	public String getValue() {
		return getDelegated().getValue();
	}

	@Element(name = "VALUE", data = true, required = true)
	public void setValue(String value) {
		getDelegated().setValue(value);
	}
}
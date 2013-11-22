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

import org.jatakasource.testcube.model.parameters.DBParameter;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "DB_PARAMETER")
public class DBParameterRendered extends AbstractParameterRendered<DBParameter> {
	
	@Element(name = XML_DESCRIPTION, data = true, required = true)
	public String getDescription() {
		return getDelegated().getDescription();
	}

	@Element(name = XML_DESCRIPTION, data = true, required = true)
	public void setDescription(String description) {
		getDelegated().setDescription(description);
	}

	@Attribute
	public Boolean isUpdateble() {
		return getDelegated().isUpdateble();
	}

	@Attribute
	public void setUpdateble(Boolean updateble) {
		getDelegated().setUpdateble(updateble);
	}
}

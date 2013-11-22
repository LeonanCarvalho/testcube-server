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
package org.jatakasource.testcube.web.xml;

import org.jatakasource.testcube.model.INameDescription;
import org.simpleframework.xml.Element;

public class NameDescriptionRendered<T extends INameDescription<Long>> extends LongIdRendered<T> {
	
	public NameDescriptionRendered() {

	}

	public NameDescriptionRendered(T delegated) {
		super(delegated);
	}

	
	@Element(name = "NAME", data = true)
	public String getName() {
		return getDelegated().getName();
	}

	@Element(name = "NAME", data = true)
	public void setName(String name) {
		getDelegated().setName(name);
	}

	@Element(name = "DESCRIPTION", data = true)
	public String getDescription() {
		return getDelegated().getDescription();
	}

	@Element(name = "DESCRIPTION", data = true)
	public void setDescription(String description) {
		getDelegated().setDescription(description);
	}
}

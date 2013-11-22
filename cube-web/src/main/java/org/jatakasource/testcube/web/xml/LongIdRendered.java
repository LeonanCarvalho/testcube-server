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
import org.jatakasource.web.xml.rendered.XMLRendererImpl;
import org.simpleframework.xml.Attribute;

public class LongIdRendered<T extends INameDescription<Long>> extends XMLRendererImpl<T> {

	public LongIdRendered() {

	}

	public LongIdRendered(T delegated) {
		super(delegated);
	}

	@Attribute(required = false)
	public Long getId() {
		return getDelegated().getId();
	}

	@Attribute(required = false)
	public void setId(Long id) {
		getDelegated().setId(id);
	}
}

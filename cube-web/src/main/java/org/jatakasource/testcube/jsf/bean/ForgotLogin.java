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
package org.jatakasource.testcube.jsf.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.jatakasource.testcube.jsf.JSFBean;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

@ManagedBean
@RequestScoped
@URLMapping(id = "forgotlogin", pattern = "/forgotlogin", viewId = "/jsf/public/forgotLogin.xhtml")
public class ForgotLogin extends JSFBean {
	private static final long serialVersionUID = 6583902905238163066L;

	public String getTitle() {
		return getApplicationMessages().getMessage(ForgotLoginProperties.class.getName() + "." + ForgotLoginProperties.TITLE.name(), getLocale());
	}

}

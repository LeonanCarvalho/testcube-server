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

public enum LoginErrorProperties {
	BAD_CREDENTIALS, 
	NO_DB_CONNECTION, 
	USER_IS_DISABLE, 
	USER_IS_LOCKED, 
	USER_IS_EXPIRED;
	
	public static LoginErrorProperties secureValueOf(String value) {
		if (NO_DB_CONNECTION.name().equals(value)) {
			return NO_DB_CONNECTION;
		} else if (USER_IS_DISABLE.name().equals(value)) {
			return USER_IS_DISABLE;
		} else if (USER_IS_LOCKED.name().equals(value)) {
			return USER_IS_LOCKED;
		} else if (USER_IS_EXPIRED.name().equals(value)) {
			return USER_IS_EXPIRED;
		} else {
			return BAD_CREDENTIALS;
		}
	}
}

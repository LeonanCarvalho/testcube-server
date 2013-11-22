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
package org.jatakasource.testcube.web.controller;

import org.jatakasource.common.svc.ReturnMessageEnum;

public enum GeneralProperties {
	INFO_TITLE,
	WARNING_TITLE,
	ERROR_TITLE,
	CRITICAL_TITLE, 
	
	UPDATE_SUCCEED,
	UPDATE_FAILED, 
	CREATE_SUCCEED,
	CREATE_FAILED,
	COPY_SUCCEED,
	COPY_FAILED,
	DELETE_SUCCEED,
	DELETE_FAILED;
	
	public static GeneralProperties getGeneralTitle(ReturnMessageEnum messageEnum){
		switch (messageEnum) {
		case INFO:
			return INFO_TITLE;
		case WARNING:
			return WARNING_TITLE;
		case ERROR:
			return ERROR_TITLE;
		case CRITICAL:
			return CRITICAL_TITLE;
		default:
			return INFO_TITLE;
		}
	}
}

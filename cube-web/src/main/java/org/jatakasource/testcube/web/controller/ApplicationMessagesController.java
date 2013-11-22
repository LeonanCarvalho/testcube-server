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

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.jatakasource.common.dao.UniqueConstraintsException;
import org.jatakasource.common.model.IDomainObject;
import org.jatakasource.common.svc.CRUDService;
import org.jatakasource.common.svc.ReturnMessageEnum;
import org.jatakasource.common.svc.exception.ServiceException;
import org.jatakasource.common.svc.exception.ServiceRunTimeException;
import org.jatakasource.testcube.web.messages.ApplicationMessages;
import org.jatakasource.web.controller.GridControllerBean;
import org.jatakasource.web.messages.ReturnMessage;
import org.jatakasource.web.xml.rendered.XMLRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

public abstract class ApplicationMessagesController<T extends IDomainObject<ID>, ID extends Serializable> extends GridControllerBean<T, ID> {
	private static final Logger logger = Logger.getLogger(ApplicationMessagesController.class);
	@Autowired
	private ApplicationMessages applicationMessages;

	public ApplicationMessages getMessages() {
		return applicationMessages;
	}

	public void setMessages(ApplicationMessages applicationMessages) {
		this.applicationMessages = applicationMessages;
	}

	protected abstract CRUDService<T, ID> getCrudService();

	protected abstract String getModelName();

	protected ModelAndView delete(ID id) {
		try {
			getCrudService().remove(id);

			return getXMLViewer(getInfo(getMessages().getMessage(GeneralProperties.class.getName() + "." + GeneralProperties.INFO_TITLE.name()),
					getMessages().getMessage(GeneralProperties.class.getName() + "." + GeneralProperties.DELETE_SUCCEED.name(), getModelName())));
		} catch (Exception e) {
			return handleException(e, GeneralProperties.class.getName() + "." + GeneralProperties.DELETE_FAILED.name());
		}
	}

	@Override
	protected <R extends XMLRenderer<T>, D extends T> ModelAndView update(String xmlCrudParameters, Class<R> renderedType, Class<D> delegatedType) {
		logger.trace("Request for update " + getModelName() + " with parameters " + xmlCrudParameters + "!!!");

		try {
			R rendered = read(xmlCrudParameters, renderedType, delegatedType);

			getCrudService().update(beforeUpdate(rendered.getDelegated()));

			return getXMLViewer(getInfo(getMessages().getMessage(GeneralProperties.class.getName() + "." + GeneralProperties.INFO_TITLE.name()),
					getMessages().getMessage(GeneralProperties.class.getName() + "." + GeneralProperties.UPDATE_SUCCEED.name(), getModelName())));
		} catch (Exception e) {
			return handleException(e, GeneralProperties.class.getName() + "." + GeneralProperties.UPDATE_FAILED.name());
		}
	}

	/**
	 * Override this method in order to perform model modifications.
	 */
	protected T beforeUpdate(T model) {
		return model;
	}

	protected <R extends XMLRenderer<T>, D extends T> ModelAndView create(String xmlCrudParameters, Class<R> renderedType, Class<D> delegatedType) {
		logger.trace("Request for create " + getModelName() + " with parameters " + xmlCrudParameters + "!!!");

		try {
			R rendered = read(xmlCrudParameters, renderedType, delegatedType);

			T returnObject = getCrudService().add(beforeCreate((T) rendered.getDelegated()));

			return getXMLViewer(getInfo(getMessages().getMessage(GeneralProperties.class.getName() + "." + GeneralProperties.INFO_TITLE.name()),
					getMessages().getMessage(GeneralProperties.class.getName() + "." + GeneralProperties.CREATE_SUCCEED.name(), getModelName()), returnObject.getId().toString()));
		} catch (Exception e) {
			return handleException(e, GeneralProperties.class.getName() + "." + GeneralProperties.CREATE_FAILED.name());
		}
	}
	
	protected ModelAndView copy(T domainObject) {
		try {
			getCrudService().copy(beforeCopy(domainObject));

			return getXMLViewer(getInfo(getMessages().getMessage(GeneralProperties.class.getName() + "." + GeneralProperties.INFO_TITLE.name()),
					getMessages().getMessage(GeneralProperties.class.getName() + "." + GeneralProperties.COPY_SUCCEED.name(), getModelName())));
		} catch (Exception e) {
			return handleException(e, GeneralProperties.class.getName() + "." + GeneralProperties.COPY_FAILED.name());
		}
	}
	
	protected T beforeCopy(T model) {
		return model;
	}

	/**
	 * Override this method in order to perform model modifications.
	 */
	protected T beforeCreate(T model) {
		return model;
	}

	public ModelAndView handleException(Exception exception, String generalMessageKey) {
		ReturnMessage message = null;

		boolean logRequired = true;

		if (ServiceRunTimeException.class.isAssignableFrom(exception.getClass())) {
			message = handleException((ServiceRunTimeException) exception);
		} else if (ServiceException.class.isAssignableFrom(exception.getClass())) {
			message = handleException((ServiceException) exception);
		} else if (UniqueConstraintsException.class.isAssignableFrom(exception.getClass())) {
			message = handleException((UniqueConstraintsException) exception);
			// This is just a validation message do not log.
			logRequired = false;
		} else {
			message = getError(getMessages().getMessage(GeneralProperties.class.getName() + "." + GeneralProperties.ERROR_TITLE.name()),
					getMessages().getMessage(generalMessageKey, getModelName()));
		}

		if (logRequired) {
			logger.error(exception.getMessage());

			if (logger.isDebugEnabled()) {
				logger.debug("ERROR - " + exception.getMessage(), exception);
			}
		}

		return getXMLViewer(message);
	}

	protected ReturnMessage handleException(ServiceRunTimeException exception) {
		String title = getMessages().getMessage(GeneralProperties.class.getName() + "." + GeneralProperties.getGeneralTitle(exception.getType()).name());
		String message = applicationMessages.getMessage(exception.getClass().getName());

		return new ReturnMessage(exception.getType(), title, message);
	}

	protected ReturnMessage handleException(ServiceException exception) {
		String title = getMessages().getMessage(GeneralProperties.class.getName() + "." + GeneralProperties.getGeneralTitle(exception.getType()).name());
		String message = applicationMessages.getMessage(exception.getClass().getName());

		return new ReturnMessage(exception.getType(), title, message);
	}

	protected ReturnMessage handleException(UniqueConstraintsException exception) {
		String title = getMessages().getMessage(GeneralProperties.class.getName() + "." + GeneralProperties.getGeneralTitle(ReturnMessageEnum.ERROR));
		String message = applicationMessages.getMessage(exception.getKey());

		return new ReturnMessage(ReturnMessageEnum.ERROR, title, message);
	}
}

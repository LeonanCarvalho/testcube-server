package org.jatakasource.testcube.dao.product;

import java.util.ArrayList;
import java.util.List;

import org.jatakasource.common.model.security.IUser;
import org.jatakasource.testcube.dao.SpringDaoTest;
import org.jatakasource.testcube.dao.product.ComponentDao;
import org.jatakasource.testcube.dao.product.ProductDao;
import org.jatakasource.testcube.dao.security.UserDao;
import org.jatakasource.testcube.dao.security.UserDaoTest;
import org.jatakasource.testcube.model.product.Component;
import org.jatakasource.testcube.model.product.IComponent;
import org.jatakasource.testcube.model.product.IProduct;
import org.jatakasource.testcube.model.product.Product;
import org.jatakasource.testcube.model.security.User;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class ComponentDaoTest extends SpringDaoTest<Long, ComponentDao, IComponent> {

	@Autowired
	private ComponentDao componentDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ProductDao productDao;
	
	@Override
	public ComponentDao getDao() {
		return componentDao;
	}

	private User user;
	private Product product;
	
	@Before
	public void before(){
		user = (User) prepareUser();
		product = (Product) prepareProduct();
	}
		
	@Override
	public IComponent[] getValidEntities() {
		Component component = new Component();
		
		component.setName("TEST COMPONENT");
		component.setDescription("TEST COMPONENT");
		component.setDefaultAssignee(user);
		
		List<User> ccList = new ArrayList<User>();
		ccList.add(user);
		
		component.setDefaultCCList(ccList);
		component.setProduct(product);
		
		return new IComponent[] { component };
	}

	@Override
	public IComponent[] getInvalidEntities() {
		Component component = (Component) getValidEntity();
		component.setName(null);
		component.setDescription(null);
		component.setDefaultAssignee(null);
		
		return new IComponent[] { component };
	}

	@Override
	public void change(IComponent entity) {
		entity.setName("TEST COMPONENT UPDATE");
	}
	
	private IUser prepareUser(){		
		return (User) userDao.save(UserDaoTest.getValidUser());
	}
	
	private IProduct prepareProduct(){		
		return (Product) productDao.save(ProductDaoTest.getValidProduct());
	}
}
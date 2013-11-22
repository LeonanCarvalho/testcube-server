package org.jatakasource.testcube.dao.security;

import org.jatakasource.common.model.security.IUser;
import org.jatakasource.testcube.dao.SpringDaoTest;
import org.jatakasource.testcube.dao.security.UserDao;
import org.jatakasource.testcube.model.security.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoTest extends SpringDaoTest<Long, UserDao, IUser> {

	@Autowired
	private UserDao userDao;

	@Override
	public void change(IUser user) {
		user.setAdministrator(false);
	}

	@Override
	public UserDao getDao() {
		return userDao;
	}

	@Override
	public IUser[] getInvalidEntities() {
		IUser invalidUser = getValidEntity();
		invalidUser.setUsername(null);
		invalidUser.setFirstName(null);
		invalidUser.setLastName(null);
		invalidUser.setPassword(null);
		invalidUser.setPasswordSlat(null);
		return new IUser[] { invalidUser };
	}

	@Override
	public IUser[] getValidEntities() {
		return new IUser[] { getValidUser() };
	}
	
	public static IUser getValidUser() {
		User user1 = new User();
		user1.setUsername("Test-User");
		user1.setFirstName("Test-User");
		user1.setLastName("Test-User");
		user1.setPassword("123456789");
		user1.setPasswordSlat("123456789");
		
		return user1;
	}
}
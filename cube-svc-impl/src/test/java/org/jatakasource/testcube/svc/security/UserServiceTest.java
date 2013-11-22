package org.jatakasource.testcube.svc.security;

import org.jatakasource.testcube.model.security.User;
import org.jatakasource.testcube.svc.SpringSvcTest;
import org.jatakasource.testcube.svc.security.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

public class UserServiceTest extends SpringSvcTest {

	@Autowired
	private UserService userService;

	@Test
	@Rollback
	public void testUserByUserName() {
		User user1 = new User();
		user1.setUsername("Test-User");
		user1.setFirstName("Test-User");
		user1.setLastName("Test-User");
		user1.setPassword("123456789");
		user1.setConfirmpassword("123456789");
		user1.setPasswordSlat("123456789");

		userService.add(user1);

		Assert.assertEquals("Test-User", userService.getUserByUserName(user1.getUsername()).getUsername());
	}
}

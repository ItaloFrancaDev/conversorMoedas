package com.thiagosena.currencyconverter.service;

import com.thiagosena.currencyconverter.dto.UserDTO;
import com.thiagosena.currencyconverter.model.User;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class UserServiceTest {

	@Inject
	UserService userService;

	private UserDTO userDTO;

	@BeforeEach
	void setUp() {
		userDTO = new UserDTO("Test1");
	}

	@Test
	@Transactional
	void whenPersistUser_ThenReturnOne() {
		User.deleteAll();
		User user = userService.persist(userDTO);
		assertNotNull(user.id);
		assertEquals("Test1", user.name);
	}

	@Test
	@Transactional
	void whenListAllUsers_ThenReturnAll() {
		User.deleteAll();
		userService.persist(userDTO);
		List<User> users = userService.listAll();
		assertEquals(1, users.size());
	}
}

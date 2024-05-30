package com.italofranca.currencyconverter.service;

import com.italofranca.currencyconverter.dto.UserDTO;
import com.italofranca.currencyconverter.model.Transaction;
import com.italofranca.currencyconverter.model.User;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
		Transaction.deleteAll();
		User.deleteAll();
		User user = userService.persist(userDTO);
		assertNotNull(user.id);
		assertEquals("Test1", user.name);
	}

	@Test
	void whenUserPersistsUserNameFieldWithMin3Characters_ThenReturnFail() {
		UserDTO userDTO = new UserDTO("Te");
		BadRequestException ex = assertThrows(BadRequestException.class, () -> userService.persist(userDTO));
		assertNotNull(ex);
	}

	@Test
	void whenUserPersistsUserNameFieldWithMax256Characters_ThenReturnFail() {
		UserDTO userDTO = new UserDTO("phUEnoMLPJiCuh1qqqkOl7hBbvScEO1ARjBaMaFPGGj81flvCSO0Ne7BDk3O8N7eJllJearUYM1S7dBvUIvcGjPjVc4vNSiUG69zZkTMpwHaHqeJ6xdiyPbanet2R2IKKypIHTIHZxUxGx6aqcNd6Ou08sdtsWCSOSRxCTA8ztRC6QXwWrHbduK09Jy2tuVa4gusZZLFEIOpe7KtO67DJzir5RJ44TTUPpYoEQkuMlF2z2WF0LItkKTt0SMAkgwQB");
		BadRequestException ex = assertThrows(BadRequestException.class, () -> userService.persist(userDTO));
		assertNotNull(ex);
	}

	@Test
	void whenUserPersistsIsNull_ThenReturnFail() {
		BadRequestException ex = assertThrows(BadRequestException.class, () -> userService.persist(null));
		assertNotNull(ex);
	}
}

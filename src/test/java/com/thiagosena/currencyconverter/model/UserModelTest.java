package com.thiagosena.currencyconverter.model;

import io.quarkus.test.junit.QuarkusTest;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class UserModelTest {

	private User originalUser;

	@BeforeEach
	void setUp() {
		originalUser = new User("Gohan Filho de Goku");
	}

	@Test
	@Transactional
	void whenFindUserById_ThenReturnOne() {
		originalUser.persistAndFlush();

		User persistedUser = User.getById(originalUser.id);

		assertNotNull(persistedUser);
		assertEquals(originalUser.id, persistedUser.id);
		assertEquals(originalUser.name, persistedUser.name);
	}

	@Test
	@Transactional
	void whenUserPersistsNameFieldWithNull_ThenReturnFail() {
		originalUser.name = null;
		PersistenceException ex = assertThrows(PersistenceException.class, () -> originalUser.persistAndFlush());
		assertNotNull(ex);
		assertTrue(ex.getCause() instanceof ConstraintViolationException);
	}

	@Test
	@Transactional
	void whenUserPersistsNameFieldWithBlank_ThenReturnFail() {
		originalUser.name = "";
		PersistenceException ex = assertThrows(PersistenceException.class, () -> originalUser.persistAndFlush());
		assertNotNull(ex);
		assertTrue(ex.getCause() instanceof ConstraintViolationException);
	}

	@Test
	@Transactional
	void whenListAllUsers_ThenReturnAll() {
		Transaction.deleteAll();
		User.deleteAll();
		originalUser.persist();
		List<User> users = User.getAll();
		assertEquals(1, users.size());
	}

	@Test
	@Transactional
	void whenUserPersistsNameFieldWithMax256Characters_ThenReturnFail() {
		User user = new User("phUEnoMLPJiCuh1qqqkOl7hBbvSc4568789EO1ARjBaMaFPGGj81flvCSO0Ne7BDk3O8N7eJllJearUYM1S7dBvUIvcGjPjVc4vNSiUG69zZkTMpwHaHqeJ6xdiyPbanet2R2IKKypIHTIHZxUxGx6aqcNd6Ou08sdtsWCSOSRxCTA8ztRC6QXwWrHbduK09Jy2tuVa4gusZZLFEIOpe7KtO67DJzir5RJ44TTUPpYoEQkuMlF2z2WF0LItkKTt0SMAkgwQB");
		PersistenceException ex = assertThrows(PersistenceException.class, user::persistAndFlush);
		assertNotNull(ex);
		assertTrue(ex.getCause() instanceof DataException);
	}
}

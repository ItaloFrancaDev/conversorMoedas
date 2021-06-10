package com.thiagosena.currencyconverter.repository;

import com.thiagosena.currencyconverter.model.User;
import io.quarkus.test.junit.QuarkusTest;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

@QuarkusTest
public class UserRepositoryTest {

	private User originalUser;

	@BeforeEach
	void setUp() {
		originalUser = new User();
		originalUser.name = "Gohan Filho de Goku";

	}

	@Test
	@Transactional
	public void whenFindUserById_ThenReturnOne() {
		originalUser.persistAndFlush();

		User persistedUser = User.findById(originalUser.id);

		Assertions.assertNotNull(persistedUser);
		Assertions.assertEquals(originalUser.id, persistedUser.id);
		Assertions.assertEquals(originalUser.name, persistedUser.name);
	}

	@Test
	@Transactional
	public void whenUserPersistsNameFieldWithNull_ThenReturnFail() {
		originalUser.name = null;
		PersistenceException ex = Assertions.assertThrows(PersistenceException.class, () -> originalUser.persistAndFlush());
		Assertions.assertNotNull(ex);
		Assertions.assertTrue(ex.getCause() instanceof ConstraintViolationException);
	}

	@Test
	@Transactional
	public void whenUserPersistsNameFildWithBlank_ThenReturnFail() {
		originalUser.name = "";
		PersistenceException ex = Assertions.assertThrows(PersistenceException.class, () -> originalUser.persistAndFlush());
		Assertions.assertNotNull(ex);
		Assertions.assertTrue(ex.getCause() instanceof ConstraintViolationException);
	}
}

package com.thiagosena.currencyconverter.repository;

import com.thiagosena.currencyconverter.model.Transaction;
import com.thiagosena.currencyconverter.model.User;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.test.junit.QuarkusTest;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class TransactionRepositoryTest {

	private Transaction originalTransaction;

	@BeforeEach
	@Transactional
	void setUpUser() {
		User user = new User();
		user.name = "Acheropita Nagazaki";
		user.persistAndFlush();

		originalTransaction = new Transaction();
		originalTransaction.userId = user.id;
		originalTransaction.conversionRate = new BigDecimal("6.133848");
		originalTransaction.sourceCurrency = "EUR";
		originalTransaction.targetCurrency = "BRL";
		originalTransaction.sourceValue = new BigDecimal("1");
		originalTransaction.dateTime = LocalDateTime.now(ZoneOffset.UTC);
	}

	@Test
	@Transactional
	public void whenNotExistTransaction_ThenReturnEmpty() {
		Transaction.deleteAll();
		PanacheQuery<Transaction> allTransactions = Transaction.findAll();

		assertNotNull(allTransactions.list());
		assertTrue(allTransactions.list().isEmpty());
		assertEquals(0, allTransactions.stream().count());
	}

	@Test
	@Transactional
	public void whenTransactionCreatedAndFindAll_ThenReturnOne() {
		Transaction.deleteAll();
		originalTransaction.persistAndFlush();

		PanacheQuery<Transaction> allTransactions = Transaction.findAll();

		assertNotNull(allTransactions.list());
		assertEquals(1, allTransactions.stream().count());
	}

	@Test
	@Transactional
	public void whenTransactionCreatedAndFindAllByUserId_ThenReturnOne() {
		Transaction.deleteAll();
		originalTransaction.persistAndFlush();

		List<Transaction> allTransactions = Transaction.findAllByUserId(originalTransaction.userId);

		assertNotNull(allTransactions);
		assertEquals(1, allTransactions.size());
		assertEquals(originalTransaction.id, allTransactions.get(0).id);
		assertEquals(originalTransaction.userId, allTransactions.get(0).userId);
		assertEquals(originalTransaction.conversionRate, allTransactions.get(0).conversionRate);
		assertEquals(originalTransaction.sourceCurrency, allTransactions.get(0).sourceCurrency);
		assertEquals(originalTransaction.sourceValue, allTransactions.get(0).sourceValue);
		assertEquals(originalTransaction.dateTime, allTransactions.get(0).dateTime);
	}

	@Test
	@Transactional
	public void whenTransactionPersistsUserIdFieldWithNull_ThenReturnFail() {
		originalTransaction.userId = null;
		PersistenceException ex = assertThrows(PersistenceException.class, () -> originalTransaction.persistAndFlush());
		assertNotNull(ex);
		assertTrue(ex.getCause() instanceof ConstraintViolationException);
	}

	@Test
	@Transactional
	public void whenTransactionPersistsConversionRateFieldWithNull_ThenReturnFail() {
		originalTransaction.conversionRate = null;
		PersistenceException ex = assertThrows(PersistenceException.class, () -> originalTransaction.persistAndFlush());
		assertNotNull(ex);
		assertTrue(ex.getCause() instanceof ConstraintViolationException);
	}

	@Test
	@Transactional
	public void whenTransactionPersistsSourceCurrencyFieldWithNull_ThenReturnFail() {
		originalTransaction.sourceCurrency = null;
		PersistenceException ex = assertThrows(PersistenceException.class, () -> originalTransaction.persistAndFlush());
		assertNotNull(ex);
		assertTrue(ex.getCause() instanceof ConstraintViolationException);
	}

	@Test
	@Transactional
	public void whenTransactionPersistsSourceCurrencyFieldWithWrongLenght_ThenReturnFail() {
		originalTransaction.sourceCurrency = "XPTO";
		PersistenceException ex = assertThrows(PersistenceException.class, () -> originalTransaction.persistAndFlush());
		assertNotNull(ex);
		assertTrue(ex.getCause() instanceof DataException);
	}

	@Test
	@Transactional
	public void whenTransactionPersistsTargetCurrencyFieldWithNull_ThenReturnFail() {
		originalTransaction.targetCurrency = null;
		PersistenceException ex = assertThrows(PersistenceException.class, () -> originalTransaction.persistAndFlush());
		assertNotNull(ex);
		assertTrue(ex.getCause() instanceof ConstraintViolationException);
	}

	@Test
	@Transactional
	public void whenTransactionPersistsTargetCurrencyFieldWithWrongLenght_ThenReturnFail() {
		originalTransaction.targetCurrency = "XPTO";
		PersistenceException ex = assertThrows(PersistenceException.class, () -> originalTransaction.persistAndFlush());
		assertNotNull(ex);
		assertTrue(ex.getCause() instanceof DataException);
	}

	@Test
	@Transactional
	public void whenTransactionPersistsSourceValueFieldWithNull_ThenReturnFail() {
		originalTransaction.sourceValue = null;
		PersistenceException ex = assertThrows(PersistenceException.class, () -> originalTransaction.persistAndFlush());
		assertNotNull(ex);
		assertTrue(ex.getCause() instanceof ConstraintViolationException);
	}

	@Test
	@Transactional
	public void whenTransactionPersistsDateTimeFieldWithNull_ThenReturnFail() {
		originalTransaction.dateTime = null;
		PersistenceException ex = assertThrows(PersistenceException.class, () -> originalTransaction.persistAndFlush());
		assertNotNull(ex);
		assertTrue(ex.getCause() instanceof ConstraintViolationException);
	}

}

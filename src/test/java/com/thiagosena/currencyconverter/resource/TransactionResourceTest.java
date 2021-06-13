package com.thiagosena.currencyconverter.resource;

import com.thiagosena.currencyconverter.model.Transaction;
import com.thiagosena.currencyconverter.model.User;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class TransactionResourceTest {

	private final LocalDateTime dateTime = LocalDateTime.parse("2021-06-13T02:20:10");

	private User user;

	@BeforeEach
	@Transactional
	void setUp() {
		Transaction.deleteAll();
		User.deleteAll();

		user = new User("Test1");
		user.persistAndFlush();

		Transaction transaction = new Transaction();
		transaction.userId = user.id;
		transaction.conversionRate = new BigDecimal("5.4");
		transaction.sourceCurrency = "EUR";
		transaction.dateTime = dateTime;
		transaction.sourceValue = new BigDecimal("5");
		transaction.targetCurrency = "BRL";
		transaction.persistAndFlush();
	}

	@Test
	void whenListAllTransactionByUserId_ThenReturnAllByUserId() {
		Transaction[] transactions = given()
				.when().get("/api/v1/transaction?user_id="+user.id)
				.then()
				.statusCode(200)
				.extract().as(Transaction[].class);

		assertNotNull(transactions);
		assertEquals(1, transactions.length);
		assertEquals(BigDecimal.valueOf(5.4), transactions[0].conversionRate);
		assertEquals("EUR", transactions[0].sourceCurrency);
		assertEquals(dateTime, transactions[0].dateTime);
		assertEquals("BRL", transactions[0].targetCurrency);
		assertEquals(new BigDecimal("5"), transactions[0].sourceValue);

	}

}
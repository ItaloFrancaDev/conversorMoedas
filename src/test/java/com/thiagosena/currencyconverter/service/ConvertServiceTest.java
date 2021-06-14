package com.thiagosena.currencyconverter.service;

import com.thiagosena.currencyconverter.dto.ExchangeRatesApiDTO;
import com.thiagosena.currencyconverter.dto.TransactionDTO;
import com.thiagosena.currencyconverter.model.Transaction;
import com.thiagosena.currencyconverter.model.User;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class ConvertServiceTest {

	@InjectMock
	@RestClient
	ExchangeRatesApiService exchangeRatesApiService;

	@ConfigProperty(name = "app.exchangeratesapi.access_key")
	String exchangeRatesApiKey;

	@Inject
	ConvertService userService;

	private User user;

	@BeforeEach
	@Transactional
	void setUp() {
		Transaction.deleteAll();
		User.deleteAll();
		user = new User("Test1");
		user.persistAndFlush();
	}

	@Test
	void whenSourceValueIsAdd_ThenReturnTheConvertedValue() {
		var exchangeRatesApiDTO = new ExchangeRatesApiDTO(LocalDate.now(), "EUR", Map.of("BRL", new BigDecimal("6.19")));
		Mockito.when(exchangeRatesApiService.getExchangeRates("EUR", "BRL", exchangeRatesApiKey)).thenReturn(exchangeRatesApiDTO);

		TransactionDTO transactionDTO = userService.convert(user.id, "EUR", "BRL", new BigDecimal(5));
		assertNotNull(transactionDTO);
		assertEquals(new BigDecimal("30.95"), transactionDTO.getTargetValue());
	}
}

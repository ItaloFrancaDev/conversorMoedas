package com.italofranca.currencyconverter.resource;

import com.italofranca.currencyconverter.dto.ExceptionDTO;
import com.italofranca.currencyconverter.dto.ExchangeRatesApiDTO;
import com.italofranca.currencyconverter.model.Transaction;
import com.italofranca.currencyconverter.model.User;
import com.italofranca.currencyconverter.service.ExchangeRatesApiService;
import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class ConverterResourceTest {

	@InjectMock
	@RestClient
	ExchangeRatesApiService exchangeRatesAPIService;

	@ConfigProperty(name = "app.exchangeratesapi.access_key")
	String exchangeRatesApiKey;

	private User user;

	@BeforeEach
	@Transactional
	void setUp() {
		Transaction.deleteAll();
		User.deleteAll();
		user = new User("Test");
		user.persistAndFlush();
	}

	@Test
	void whenNoParameterRequest_ThenReturnErrorStatus400() {
		given()
				.header("Content-Type", MediaType.APPLICATION_JSON)
				.when().get("/api/v1/convert")
				.then()
				.statusCode(Status.BAD_REQUEST.getStatusCode());
	}

	@Test
	void whenSourceParameterIsNull_ThenReturnErrorStatus400() {
		given()
				.header("Content-Type", MediaType.APPLICATION_JSON)
				.when().get("/api/v1/convert?user_id=1&source=")
				.then()
				.statusCode(Status.BAD_REQUEST.getStatusCode());
	}

	@Test
	void whenTargetParameterIsNull_ThenReturnErrorStatus400() {
		given()
				.header("Content-Type", MediaType.APPLICATION_JSON)
				.when().get("/api/v1/convert?user_id=1&source=EUR")
				.then()
				.statusCode(Status.BAD_REQUEST.getStatusCode());
	}

	@Test
	void whenValueParameterIsNull_ThenReturnErrorStatus400() {
		given()
				.header("Content-Type", MediaType.APPLICATION_JSON)
				.when().get("/api/v1/convert?user_id=1&source=EUR&target=BRL")
				.then()
				.statusCode(Status.BAD_REQUEST.getStatusCode());
	}

	@Test
	void whenValueParameterIsNegative_ThenReturnErrorStatus400() {
		given()
				.header("Content-Type", MediaType.APPLICATION_JSON)
				.when().get("/api/v1/convert?user_id=1&source=EUR&target=BRL&value=-20")
				.then()
				.statusCode(Status.BAD_REQUEST.getStatusCode());
	}

	@Test
	void whenAllParametersIsOk_ThenReturnSuccessStatus200() {
		var exchangeRatesApiDTO = new ExchangeRatesApiDTO(LocalDate.now(), "EUR", Map.of("BRL", new BigDecimal("6.194345")));
		Mockito.when(exchangeRatesAPIService.getExchangeRates("EUR", "BRL", exchangeRatesApiKey)).thenReturn(exchangeRatesApiDTO);

		given()
				.header("Content-Type", MediaType.APPLICATION_JSON)
				.when().get("/api/v1/convert?user_id=" + user.id + "&source=EUR&target=BRL&value=5")
				.then()
				.statusCode(Status.OK.getStatusCode());
	}

	@Test
	void whenCurrencyNotFound_ThenReturnStatus400() {
		LocalDate date = LocalDate.now();
		var exchangeRatesApiDTO = new ExchangeRatesApiDTO();
		exchangeRatesApiDTO.setDate(date);
		exchangeRatesApiDTO.setBase("EUR");
		exchangeRatesApiDTO.setRates(Map.of("ZZZ", new BigDecimal("5.3")));

		ExceptionDTO exceptionDTO = new ExceptionDTO();
		exceptionDTO.setMessage("You have provided one or more invalid Currency Codes Test");
		exchangeRatesApiDTO.setError(exceptionDTO);
		Mockito.when(exchangeRatesAPIService.getExchangeRates("EUR", "ZZZ", exchangeRatesApiKey)).thenReturn(exchangeRatesApiDTO);

		given()
				.header("Content-Type", MediaType.APPLICATION_JSON)
				.when().get("/api/v1/convert?user_id=" + user.id + "&source=EUR&target=ZZZ&value=5")
				.then()
				.statusCode(Status.BAD_REQUEST.getStatusCode());

		assertEquals(date, exchangeRatesApiDTO.getDate());
		assertEquals("EUR", exchangeRatesApiDTO.getBase());
		assertEquals(Map.of("ZZZ", new BigDecimal("5.3")), exchangeRatesApiDTO.getRates());
	}

	@Test
	@Transactional
	void whenUserNotFound_ThenReturnStatus400() {
		PanacheMock.mock(User.class);
		Mockito.when(User.getById(1L)).thenReturn(null);

		given()
				.header("Content-Type", MediaType.APPLICATION_JSON)
				.when().get("/api/v1/convert?user_id=1&source=EUR&target=ZZZ&value=5")
				.then()
				.statusCode(Status.BAD_REQUEST.getStatusCode());
	}

}
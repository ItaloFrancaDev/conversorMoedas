package com.thiagosena.currencyconverter.resource;

import com.thiagosena.currencyconverter.dto.ExchangeRatesApiDTO;
import com.thiagosena.currencyconverter.model.Transaction;
import com.thiagosena.currencyconverter.model.User;
import com.thiagosena.currencyconverter.service.ExchangeRatesApiService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
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

@QuarkusTest
class ConverterResourceTest {

	@InjectMock
	@RestClient
	ExchangeRatesApiService exchangeRatesAPIService;

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
				.when()
				.get("/api/v1/convert")
				.then()
				.statusCode(Status.BAD_REQUEST.getStatusCode());
	}

	@Test
	void whenSourceParameterIsNull_ThenReturnErrorStatus400() {
		given()
				.header("Content-Type", MediaType.APPLICATION_JSON)
				.when()
				.get("/api/v1/convert?user_id=1")
				.then()
				.statusCode(Status.BAD_REQUEST.getStatusCode());
	}

	@Test
	void whenTargetParameterIsNull_ThenReturnErrorStatus400() {
		given()
				.header("Content-Type", MediaType.APPLICATION_JSON)
				.when()
				.get("/api/v1/convert?user_id=1&source=EUR")
				.then()
				.statusCode(Status.BAD_REQUEST.getStatusCode());
	}

	@Test
	void whenValueParameterIsNull_ThenReturnErrorStatus400() {
		given()
				.header("Content-Type", MediaType.APPLICATION_JSON)
				.when()
				.get("/api/v1/convert?user_id=1&source=EUR&target=BRL")
				.then()
				.statusCode(Status.BAD_REQUEST.getStatusCode());
	}



	@Test
	void whenAllParametersIsOk_ThenReturnSuccessStatus200() {
		var exchangeRatesApiDTO = new ExchangeRatesApiDTO(LocalDate.now(), "EUR", Map.of("BRL", new BigDecimal("6.194345")));
		Mockito.when(exchangeRatesAPIService.getExchangeRates("EUR", "BRL", "1")).thenReturn(exchangeRatesApiDTO);

		given()
				.header("Content-Type", MediaType.APPLICATION_JSON)
				.when()
				.get("/api/v1/convert?user_id=" + user.id + "&source=EUR&target=BRL&value=5")
				.then()
				.statusCode(Status.OK.getStatusCode());
	}

}
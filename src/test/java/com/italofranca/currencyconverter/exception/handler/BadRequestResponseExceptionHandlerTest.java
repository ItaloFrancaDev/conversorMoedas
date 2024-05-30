package com.italofranca.currencyconverter.exception.handler;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class BadRequestResponseExceptionHandlerTest {

	@Test
	void whenBadRequestInExchangeRatesApi_ThenReturnErrorStatus400AndSameMessage() {
		BadRequestResponseExceptionHandler ex = new BadRequestResponseExceptionHandler();
		ex.handles(400, null);
		ResponseBuilder responseBuilder = Response.status(400);
		String message = "{\"error\": {\"code\":\"test_123\",\"message\": \"Test Fail\"}}";
		InputStream inputStream = new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8));
		Response response = responseBuilder.entity(inputStream).build();
		BadRequestException error = ex.toThrowable(response);

		assertNotNull(error);
		assertEquals("Test Fail", error.getMessage());
	}

	@Test
	void whenBadRequestInExchangeRatesApiReturnsNonJsonFormat_ThenReturnErrorStatus400() {
		BadRequestResponseExceptionHandler ex = new BadRequestResponseExceptionHandler();
		ex.handles(400, null);
		ResponseBuilder responseBuilder = Response.status(400);
		String message = "Test Fail";
		InputStream inputStream = new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8));
		Response response = responseBuilder.entity(inputStream).build();
		BadRequestException error = ex.toThrowable(response);

		assertNotNull(error);
		assertEquals("An exception occurred in exchengeratesapi.io", error.getMessage());
	}

	@Test
	void whenBadRequestInExchangeRatesApiNoReturnsMessage_ThenReturnErrorStatus400() {
		BadRequestResponseExceptionHandler ex = new BadRequestResponseExceptionHandler();
		ex.handles(404, null);
		ResponseBuilder responseBuilder = Response.status(404);
		String message = "{\"error\": {\"code\":\"test_123\"}}";
		InputStream inputStream = new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8));
		Response response = responseBuilder.entity(inputStream).build();
		BadRequestException error = ex.toThrowable(response);

		assertNotNull(error);
		assertEquals("An exception occurred in exchengeratesapi.io", error.getMessage());
	}

	@Test
	void whenBadRequestInExchangeRatesApiReturnsNothing_ThenReturnErrorStatus400() {
		BadRequestResponseExceptionHandler ex = new BadRequestResponseExceptionHandler();
		ex.handles(404, null);
		ResponseBuilder responseBuilder = Response.status(404);
		String message = "{}";
		InputStream inputStream = new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8));
		Response response = responseBuilder.entity(inputStream).build();
		BadRequestException error = ex.toThrowable(response);

		assertNotNull(error);
		assertEquals("An exception occurred in exchengeratesapi.io", error.getMessage());
	}
}

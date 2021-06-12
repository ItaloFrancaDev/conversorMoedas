package com.thiagosena.currencyconverter.exception.handler;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class ServiceUnavailableExceptionHandlerTest {

	@Test
	void whenServiceUnavailable_ThenReturnErrorStatus503() {
		ServiceUnavailableExceptionHandler ex = new ServiceUnavailableExceptionHandler();
		Response response = ex.toResponse(new ProcessingException("service unavailable test"));
		assertNotNull(response);
		assertEquals(response.getStatus(), Status.SERVICE_UNAVAILABLE.getStatusCode());
	}

	@Test
	void whenServiceUnavailable_ThenReturnErrorStatus503WithCause() {
		ServiceUnavailableExceptionHandler ex = new ServiceUnavailableExceptionHandler();
		Response response = ex.toResponse(new ProcessingException("service unavailable test", new Exception("cause test")));
		assertNotNull(response);
		assertEquals(response.getStatus(), Status.SERVICE_UNAVAILABLE.getStatusCode());
	}
}

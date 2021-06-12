package com.thiagosena.currencyconverter.exception.handler;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class GenericExceptionHandlerTest {

	@Test
	void whenGenericException_ThenReturnErrorStatus500() {
		GenericExceptionHandler ex = new GenericExceptionHandler();
		Response response = ex.toResponse(new Exception("generic exception test"));
		assertNotNull(response);
		assertEquals(response.getStatus(), Status.INTERNAL_SERVER_ERROR.getStatusCode());
	}
}

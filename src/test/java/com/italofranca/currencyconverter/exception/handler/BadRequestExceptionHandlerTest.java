package com.italofranca.currencyconverter.exception.handler;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class BadRequestExceptionHandlerTest {

	@Test
	void whenBadRequest_ThenReturnErrorStatus400() {
		BadRequestExceptionHandler ex = new BadRequestExceptionHandler();
		Response response = ex.toResponse(new BadRequestException("bad request exception test"));
		assertNotNull(response);
		assertEquals(response.getStatus(), Status.BAD_REQUEST.getStatusCode());
	}
}

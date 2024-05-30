package com.italofranca.currencyconverter.exception.handler;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class ResourceNotFoundExceptionHandlerTest {

	@Test
	void whenNotFound_ThenReturnErrorStatus404() {
		ResourceNotFoundExceptionHandler ex = new ResourceNotFoundExceptionHandler();
		Response response = ex.toResponse(new NotFoundException("resource not found test"));
		assertNotNull(response);
		assertEquals(response.getStatus(), Status.NOT_FOUND.getStatusCode());
	}
}

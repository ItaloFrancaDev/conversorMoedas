package com.thiagosena.currencyconverter.exception.handler;

import com.thiagosena.currencyconverter.dto.ApiErrorDTO;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ServiceUnavailableExceptionHandler implements ExceptionMapper<ProcessingException> {

	@Override
	public Response toResponse(ProcessingException ex) {
		var apiError = new ApiErrorDTO(Status.SERVICE_UNAVAILABLE, "The service exchangeratesapi.io is unavailable", ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage());
		return Response.status(apiError.getStatus()).entity(apiError).build();
	}
}

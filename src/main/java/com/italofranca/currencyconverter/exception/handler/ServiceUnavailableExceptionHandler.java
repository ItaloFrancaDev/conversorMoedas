package com.italofranca.currencyconverter.exception.handler;

import com.italofranca.currencyconverter.dto.ErrorDTO;
import com.italofranca.currencyconverter.dto.ExceptionDTO;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ServiceUnavailableExceptionHandler implements ExceptionMapper<ProcessingException> {

	@Override
	public Response toResponse(ProcessingException ex) {
		var apiError = new ExceptionDTO(Status.SERVICE_UNAVAILABLE.getStatusCode(), Status.SERVICE_UNAVAILABLE, "The service exchangeratesapi.io is unavailable", ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage());
		var exception = new ErrorDTO(apiError);
		return Response.status(apiError.getStatus()).entity(exception).build();
	}
}

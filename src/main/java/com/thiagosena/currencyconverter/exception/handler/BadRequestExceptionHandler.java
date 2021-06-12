package com.thiagosena.currencyconverter.exception.handler;

import com.thiagosena.currencyconverter.dto.ApiErrorDTO;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BadRequestExceptionHandler implements ExceptionMapper<BadRequestException> {

	@Override
	public Response toResponse(BadRequestException ex) {
		var apiError = new ApiErrorDTO(Status.BAD_REQUEST, ex.getMessage(), null);
		return Response.status(apiError.getStatus()).entity(apiError).build();
	}
}

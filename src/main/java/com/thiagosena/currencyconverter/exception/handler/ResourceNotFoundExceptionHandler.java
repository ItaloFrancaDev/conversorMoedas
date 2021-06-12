package com.thiagosena.currencyconverter.exception.handler;

import com.thiagosena.currencyconverter.dto.ApiErrorDTO;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ResourceNotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {

	@Override
	public Response toResponse(NotFoundException ex) {
		var apiError = new ApiErrorDTO(Status.NOT_FOUND, "Ops... Page not found.", ex.toString());
		return Response.status(apiError.getStatus()).entity(apiError).build();
	}
}

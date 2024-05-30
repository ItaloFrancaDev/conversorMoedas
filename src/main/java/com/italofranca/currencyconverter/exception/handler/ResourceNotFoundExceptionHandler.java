package com.italofranca.currencyconverter.exception.handler;

import com.italofranca.currencyconverter.dto.ErrorDTO;
import com.italofranca.currencyconverter.dto.ExceptionDTO;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ResourceNotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {

	@Override
	public Response toResponse(NotFoundException ex) {
		var apiError = new ExceptionDTO(Status.NOT_FOUND.getStatusCode(), Status.NOT_FOUND, "Ops... Page not found.", ex.toString());
		var exception = new ErrorDTO(apiError);
		return Response.status(apiError.getStatus()).entity(exception).build();
	}
}

package com.italofranca.currencyconverter.exception.handler;

import com.italofranca.currencyconverter.dto.ErrorDTO;
import com.italofranca.currencyconverter.dto.ExceptionDTO;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BadRequestExceptionHandler implements ExceptionMapper<BadRequestException> {

	@Override
	public Response toResponse(BadRequestException ex) {
		var apiErrorDTO = new ExceptionDTO(Status.BAD_REQUEST.getStatusCode(),Status.BAD_REQUEST, ex.getMessage(), null);
		var exception = new ErrorDTO(apiErrorDTO);
		return Response.status(apiErrorDTO.getStatus()).entity(exception).build();
	}
}

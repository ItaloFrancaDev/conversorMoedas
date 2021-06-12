package com.thiagosena.currencyconverter.exception.handler;

import com.thiagosena.currencyconverter.dto.ApiErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionHandler implements ExceptionMapper<Exception> {

	private static final Logger LOG = LoggerFactory.getLogger(GenericExceptionHandler.class);

	@Override
	public Response toResponse(Exception ex) {
		var apiError = new ApiErrorDTO(Status.INTERNAL_SERVER_ERROR, "Ops... We were not waiting for it.", ex.toString());
		LOG.error("Error: ", ex);
		return Response.status(apiError.getStatus()).entity(apiError).build();
	}
}

package com.italofranca.currencyconverter.exception.handler;

import com.italofranca.currencyconverter.dto.ErrorDTO;
import com.italofranca.currencyconverter.dto.ExceptionDTO;
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
		var apiError = new ExceptionDTO(Status.INTERNAL_SERVER_ERROR.getStatusCode(), Status.INTERNAL_SERVER_ERROR, "Ops... We were not waiting for it.", ex.toString());
		var exception = new ErrorDTO(apiError);
		LOG.error("Error: ", ex);
		return Response.status(apiError.getStatus()).entity(exception).build();
	}
}

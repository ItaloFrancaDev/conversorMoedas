package com.italofranca.currencyconverter.exception.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.italofranca.currencyconverter.dto.ErrorDTO;
import io.smallrye.openapi.runtime.util.StringUtil;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.ByteArrayInputStream;

@Provider
public class BadRequestResponseExceptionHandler implements ResponseExceptionMapper<BadRequestException> {

	@Override
	public BadRequestException toThrowable(Response response) {
		ErrorDTO msg; // see below
		try {
			msg = getBody(response);
		} catch (JsonProcessingException e) {
			return new BadRequestException("An exception occurred in exchengeratesapi.io");
		}
		return new BadRequestException(msg.getError() != null && StringUtil.isNotEmpty(msg.getError().getMessage()) ? msg.getError().getMessage() : "An exception occurred in exchengeratesapi.io");
	}

	@Override
	public boolean handles(int status, MultivaluedMap<String, Object> headers) {
		return status == 400;
	}

	private ErrorDTO getBody(Response response) throws JsonProcessingException {
		ByteArrayInputStream is = (ByteArrayInputStream) response.getEntity();
		var bytes = new byte[is.available()];
		is.read(bytes, 0, is.available());
		return new ObjectMapper().readValue(new String(bytes), ErrorDTO.class);
	}

}

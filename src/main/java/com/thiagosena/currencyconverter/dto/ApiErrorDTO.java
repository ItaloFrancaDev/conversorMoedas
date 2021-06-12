package com.thiagosena.currencyconverter.dto;

import javax.ws.rs.core.Response.Status;
import java.util.Collections;
import java.util.List;

public class ApiErrorDTO {
	private final String message;

	private final List<String> errors;

	private final Status status;

	public ApiErrorDTO(Status status, String message, String error) {
		this.status = status;
		this.message = message;
		errors = Collections.singletonList(error);
	}

	public Status getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public List<String> getErrors() {
		return errors;
	}

}

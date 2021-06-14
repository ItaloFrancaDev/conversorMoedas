package com.thiagosena.currencyconverter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.ws.rs.core.Response.Status;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExceptionDTO {
	private String message;

	private String exception;

	private Status status;

	private int statusCode;

	public ExceptionDTO() {
	}

	public ExceptionDTO(int statusCode, Status status, String message, String error) {
		this.statusCode = statusCode;
		this.status = status;
		this.message = message;
		this.exception = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getException() {
		return exception;
	}

	public Status getStatus() {
		return status;
	}

	public int getStatusCode() {
		return statusCode;
	}
}

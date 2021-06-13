package com.thiagosena.currencyconverter.dto;

import javax.ws.rs.core.Response.Status;

public class ExceptionDTO {
	private String message;

	private String exception;

	private Status status;

	private int code;

	public ExceptionDTO() {
	}

	public ExceptionDTO(int code, Status status, String message, String error) {
		this.code = code;
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

	public int getCode() {
		return code;
	}
}

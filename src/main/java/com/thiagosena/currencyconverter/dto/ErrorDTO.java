package com.thiagosena.currencyconverter.dto;

public class ErrorDTO {
	private ExceptionDTO error;

	public ErrorDTO(ExceptionDTO error) {
		this.error = error;
	}

	public ExceptionDTO getError() {
		return error;
	}

}

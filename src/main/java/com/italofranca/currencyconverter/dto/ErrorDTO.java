package com.italofranca.currencyconverter.dto;

public class ErrorDTO {
	private ExceptionDTO error;

	public ErrorDTO() {
	}

	public ErrorDTO(ExceptionDTO error) {
		this.error = error;
	}

	public ExceptionDTO getError() {
		return error;
	}

}

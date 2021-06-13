package com.thiagosena.currencyconverter.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class ExchangeRatesApiDTO {
	private LocalDate date;

	private String base;

	private Map<String, BigDecimal> rates;

	private ExceptionDTO error;

	public ExchangeRatesApiDTO() {
	}

	public ExchangeRatesApiDTO(LocalDate date, String base, Map<String, BigDecimal> rates) {
		this.date = date;
		this.base = base;
		this.rates = rates;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public Map<String, BigDecimal> getRates() {
		return rates;
	}

	public void setRates(Map<String, BigDecimal> rates) {
		this.rates = rates;
	}

	public ExceptionDTO getError() {
		return error;
	}

	public void setError(ExceptionDTO error) {
		this.error = error;
	}
}

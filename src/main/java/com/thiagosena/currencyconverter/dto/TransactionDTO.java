package com.thiagosena.currencyconverter.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDTO {

	private Long transactionId;

	private Long userId;

	private String sourceCurrency;

	private BigDecimal sourceValue;

	private String targetCurrency;

	private BigDecimal targeValue;

	private BigDecimal convertionRate;

	private LocalDateTime dateTime;

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getSourceCurrency() {
		return sourceCurrency;
	}

	public void setSourceCurrency(String sourceCurrency) {
		this.sourceCurrency = sourceCurrency;
	}

	public BigDecimal getSourceValue() {
		return sourceValue;
	}

	public void setSourceValue(BigDecimal sourceValue) {
		this.sourceValue = sourceValue;
	}

	public String getTargetCurrency() {
		return targetCurrency;
	}

	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;
	}

	public BigDecimal getTargeValue() {
		return targeValue;
	}

	public void setTargeValue(BigDecimal targeValue) {
		this.targeValue = targeValue;
	}

	public BigDecimal getConvertionRate() {
		return convertionRate;
	}

	public void setConvertionRate(BigDecimal convertionRate) {
		this.convertionRate = convertionRate;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
}

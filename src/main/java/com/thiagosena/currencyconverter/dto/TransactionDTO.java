package com.thiagosena.currencyconverter.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDTO {

	private Long id;

	@NotNull
	private Long userId;

	@NotNull
	@Size(min = 3, max = 3)
	private String sourceCurrency;

	@NotNull
	@DecimalMin("0.00")
	private BigDecimal sourceValue;

	@NotNull
	@Size(min = 3, max = 3)
	private String targetCurrency;

	@NotNull
	@DecimalMin("0.00")
	private BigDecimal targetValue;

	@NotNull
	private BigDecimal conversionRate;

	@NotNull
	private LocalDateTime dateTime;

	public TransactionDTO() {
	}

	public TransactionDTO(Long userId, String sourceCurrency, BigDecimal sourceValue, String targetCurrency) {
		this.userId = userId;
		this.sourceCurrency = sourceCurrency;
		this.sourceValue = sourceValue;
		this.targetCurrency = targetCurrency;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public String getSourceCurrency() {
		return sourceCurrency;
	}

	public BigDecimal getSourceValue() {
		return sourceValue;
	}

	public String getTargetCurrency() {
		return targetCurrency;
	}

	public BigDecimal getTargetValue() {
		return targetValue;
	}

	public void setTargetValue(BigDecimal targetValue) {
		this.targetValue = targetValue;
	}

	public BigDecimal getConversionRate() {
		return conversionRate;
	}

	public void setConversionRate(BigDecimal conversionRate) {
		this.conversionRate = conversionRate;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
}

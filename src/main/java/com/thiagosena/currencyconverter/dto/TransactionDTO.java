package com.thiagosena.currencyconverter.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDTO {

	public Long transactionId;

	@NotNull
	public Long userId;

	@NotNull
	@Size(min = 3, max = 3)
	public String sourceCurrency;

	@NotNull
	@DecimalMin("0.00")
	public BigDecimal sourceValue;

	@NotNull
	@Size(min = 3, max = 3)
	public String targetCurrency;

	@NotNull
	@DecimalMin("0.00")
	public BigDecimal targetValue;

	@NotNull
	public BigDecimal convertionRate;

	@NotNull
	public LocalDateTime dateTime;

	public TransactionDTO() {
	}

	public TransactionDTO(Long userId, String sourceCurrency, BigDecimal sourceValue, String targetCurrency) {
		this.userId = userId;
		this.sourceCurrency = sourceCurrency;
		this.sourceValue = sourceValue;
		this.targetCurrency = targetCurrency;
	}
}

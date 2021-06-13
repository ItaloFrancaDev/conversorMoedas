package com.thiagosena.currencyconverter.service;

import com.thiagosena.currencyconverter.dto.TransactionDTO;

import java.math.BigDecimal;

public interface ConvertService {
	TransactionDTO convert(Long userId, String source, String target, BigDecimal value);
}

package com.italofranca.currencyconverter.service;

import com.italofranca.currencyconverter.dto.TransactionDTO;

import java.math.BigDecimal;

public interface ConvertService {
	TransactionDTO convert(Long userId, String source, String target, BigDecimal value);
}

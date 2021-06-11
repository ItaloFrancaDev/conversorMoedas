package com.thiagosena.currencyconverter.service;

import com.thiagosena.currencyconverter.dto.TransactionDTO;

public interface ConvertService {
	TransactionDTO convert(TransactionDTO transactionDTO);
}

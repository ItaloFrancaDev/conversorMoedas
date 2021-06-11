package com.thiagosena.currencyconverter.service;

import com.thiagosena.currencyconverter.dto.TransactionDTO;

public interface ExchangeRatesApiService {
	TransactionDTO convert(TransactionDTO transactionDTO);
}

package com.thiagosena.currencyconverter.service.impl;

import com.thiagosena.currencyconverter.dto.TransactionDTO;
import com.thiagosena.currencyconverter.service.ConvertService;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConvertServiceImpl implements ConvertService {

	@Override
	public TransactionDTO convert(TransactionDTO transactionDTO) {
		// source: EUR
		// target: BRL
		// value: 5 eur
		// rate: 6.13 brl (1 euro)
		// valueTarget = value * rate

		// variables to save transaction in database
		//		- transactionId
		//		- userId;
		//		- sourceCurrency;
		//		- sourceValue;
		//		- targetCurrency;
		//		- targetValue;
		//		- conversionRate;
		//		- dateTime;
		return null;
	}
}

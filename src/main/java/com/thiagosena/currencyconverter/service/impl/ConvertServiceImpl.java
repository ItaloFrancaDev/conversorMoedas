package com.thiagosena.currencyconverter.service.impl;

import com.thiagosena.currencyconverter.dto.ExchangeRatesApiDTO;
import com.thiagosena.currencyconverter.dto.TransactionDTO;
import com.thiagosena.currencyconverter.mapper.TransactionMapper;
import com.thiagosena.currencyconverter.model.User;
import com.thiagosena.currencyconverter.service.ConvertService;
import com.thiagosena.currencyconverter.service.ExchangeRatesApiService;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@ApplicationScoped
public class ConvertServiceImpl implements ConvertService {

	@Inject
	@RestClient
	ExchangeRatesApiService exchangeRatesApiService;

	@Inject
	TransactionMapper transactionMapper;

	@ConfigProperty(name = "app.exchangeratesapi.access_key")
	String exchangeRatesApiKey;

	@Override
	@Transactional
	public TransactionDTO convert(TransactionDTO transactionDTO) {
		var user = User.getById(transactionDTO.getUserId());
		if (user == null) {
			throw new BadRequestException(String.format("User with id %s not found", transactionDTO.getUserId()));
		}

		ExchangeRatesApiDTO exchangeRates = exchangeRatesApiService.getExchangeRates(transactionDTO.getSourceCurrency(), transactionDTO.getTargetCurrency(), exchangeRatesApiKey);

		if(exchangeRates != null) {
			transactionDTO.setTargetValue(exchangeRates.getRates().get(transactionDTO.getTargetCurrency()).multiply(transactionDTO.getSourceValue()));
			transactionDTO.setDateTime(LocalDateTime.now(ZoneOffset.UTC));
			transactionDTO.setConversionRate(exchangeRates.getRates().get(transactionDTO.getTargetCurrency()));

			var transaction = transactionMapper.transactionDTOtoTransaction(transactionDTO);

			transaction.persistAndFlush();
			transactionDTO.setId(user.id);

			return transactionDTO;
		}
		return new TransactionDTO();
	}
}

package com.italofranca.currencyconverter.service.impl;

import com.italofranca.currencyconverter.dto.ExchangeRatesApiDTO;
import com.italofranca.currencyconverter.dto.TransactionDTO;
import com.italofranca.currencyconverter.mapper.TransactionMapper;
import com.italofranca.currencyconverter.model.User;
import com.italofranca.currencyconverter.service.ConvertService;
import com.italofranca.currencyconverter.service.ExchangeRatesApiService;
import io.netty.util.internal.StringUtil;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import java.math.BigDecimal;
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

	private void fieldsValidate(Long userId, String source, String target, BigDecimal value) throws BadRequestException {
		if (userId == null) {
			throw new BadRequestException("user_id is required");
		} else if (StringUtil.isNullOrEmpty(source)) {
			throw new BadRequestException("source is required");
		} else if (StringUtil.isNullOrEmpty(target)) {
			throw new BadRequestException("target is required");
		} else if (value == null) {
			throw new BadRequestException("value is required");
		} else if (value.compareTo(BigDecimal.valueOf(0.01)) < 0) {
			throw new BadRequestException("value cannot be negative ");
		}
	}

	@Override
	@Transactional
	public TransactionDTO convert(Long userId, String source, String target, BigDecimal value) {
		fieldsValidate(userId, source, target, value);

		var transactionDTO = new TransactionDTO(userId, source, value, target);

		var user = User.getById(transactionDTO.getUserId());
		if (user == null) {
			throw new BadRequestException(String.format("User with id %s not found", transactionDTO.getUserId()));
		}

		ExchangeRatesApiDTO exchangeRates = exchangeRatesApiService.getExchangeRates(transactionDTO.getSourceCurrency(), transactionDTO.getTargetCurrency(), exchangeRatesApiKey);

		if (exchangeRates.getError() != null) {
			throw new BadRequestException(exchangeRates.getError().getMessage());
		}

		transactionDTO.setTargetValue(exchangeRates.getRates().get(transactionDTO.getTargetCurrency()).multiply(transactionDTO.getSourceValue()));
		transactionDTO.setDateTime(LocalDateTime.now(ZoneOffset.UTC));
		transactionDTO.setConversionRate(exchangeRates.getRates().get(transactionDTO.getTargetCurrency()));

		var transaction = transactionMapper.transactionDTOtoTransaction(transactionDTO);

		transaction.persistAndFlush();
		transactionDTO.setId(user.id);

		return transactionDTO;
	}
}

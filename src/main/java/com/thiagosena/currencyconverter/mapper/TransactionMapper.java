package com.thiagosena.currencyconverter.mapper;

import com.thiagosena.currencyconverter.dto.TransactionDTO;
import com.thiagosena.currencyconverter.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface TransactionMapper {

	@Mapping(target = "id", source = "dto.id")
	@Mapping(target = "userId", source = "dto.userId")
	@Mapping(target = "sourceCurrency", source = "dto.sourceCurrency")
	@Mapping(target = "sourceValue", source = "dto.sourceValue")
	@Mapping(target = "targetCurrency", source = "dto.targetCurrency")
	@Mapping(target = "conversionRate", source = "dto.conversionRate")
	@Mapping(target = "dateTime", source = "dto.dateTime")
	Transaction transactionDTOtoTransaction(TransactionDTO dto);
}

package com.thiagosena.currencyconverter.mapper;

import com.thiagosena.currencyconverter.dto.TransactionDTO;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
class TransactionMapperTest {

	@Inject
	TransactionMapper transactionMapper;

	@Test
	void whenConvertDtoToTransaction_ThenReturnTransaction() {
		TransactionDTO dto = new TransactionDTO(1L, "EUR", BigDecimal.TEN, "BRL");
		dto.setId(1L);
		dto.setConversionRate(new BigDecimal("5"));
		dto.setDateTime(LocalDateTime.now(ZoneOffset.UTC));

		var transaction = transactionMapper.transactionDTOtoTransaction(dto);

		assertNotNull(transaction);
		assertEquals(dto.getId(), transaction.id);
		assertEquals(dto.getUserId(), transaction.userId);
		assertEquals(dto.getSourceCurrency(), transaction.sourceCurrency);
		assertEquals(dto.getSourceValue(), transaction.sourceValue);
		assertEquals(dto.getTargetCurrency(), transaction.targetCurrency);
		assertEquals(dto.getConversionRate(), transaction.conversionRate);
		assertEquals(dto.getDateTime(), transaction.dateTime);
	}

	@Test
	void whenConvertDtoIsNull_ThenReturnTransactionNull() {
		var transaction = transactionMapper.transactionDTOtoTransaction(null);
		assertNull(transaction);
	}
}

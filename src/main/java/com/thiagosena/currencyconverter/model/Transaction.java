package com.thiagosena.currencyconverter.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.common.constraint.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import javax.ws.rs.BadRequestException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Transaction extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "transaction_sequence")
	@SequenceGenerator(name = "transaction_sequence", sequenceName = "NR_TRANSACTION_ID_SEQ", allocationSize = 1)
	public Long id;

	@NotNull
	@Column(name = "user_id")
	public Long userId;

	@NotNull
	@Size(min = 3, max = 3)
	@Column(name = "source_currency")
	public String sourceCurrency;

	@NotNull
	@DecimalMin("0.00")
	@Column(name = "source_value")
	public BigDecimal sourceValue;

	@NotNull
	@Size(min = 3, max = 3)
	@Column(name = "target_currency")
	public String targetCurrency;

	@NotNull
	@Column(name = "conversion_rate")
	public BigDecimal conversionRate;

	@NotNull
	@Column(name = "date_time")
	public LocalDateTime dateTime;

	public static List<Transaction> findAllByUserId(Long userId) {
		if(userId == null) {
			throw new BadRequestException("user_id is required");
		}
		return list("userId", userId);
	}

}

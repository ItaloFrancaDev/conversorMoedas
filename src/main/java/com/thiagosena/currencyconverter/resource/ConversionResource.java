package com.thiagosena.currencyconverter.resource;

import com.thiagosena.currencyconverter.dto.TransactionDTO;
import com.thiagosena.currencyconverter.service.ConvertService;
import io.netty.util.internal.StringUtil;

import javax.inject.Inject;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;

@Path("/api/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ConversionResource {

	@Inject
	ConvertService convertService;

	@GET
	@Path("/convert")
	public TransactionDTO convert(
			@QueryParam("user_id") @NotNull @DecimalMin("1") Long userId,
			@QueryParam("source") @NotNull @Size(min = 3, max = 3) String source,
			@QueryParam("target") @NotNull @Size(min = 3, max = 3) String target,
			@QueryParam("value") @NotNull @DecimalMin("0.01") BigDecimal value
	) throws BadRequestException {
		fieldsValidate(userId, source, target, value);
		var transactionDTO = new TransactionDTO(userId, source, value, target);
		return convertService.convert(transactionDTO);
	}

	private void fieldsValidate(Long userId, String source, String target, BigDecimal value) throws BadRequestException {
		if(userId == null) {
			throw new BadRequestException("user_id is required");
		} else if(StringUtil.isNullOrEmpty(source)) {
			throw new BadRequestException("source is required");
		} else if(StringUtil.isNullOrEmpty(target)) {
			throw new BadRequestException("target is required");
		} else if(value == null) {
			throw new BadRequestException("value is required");
		}
	}
}

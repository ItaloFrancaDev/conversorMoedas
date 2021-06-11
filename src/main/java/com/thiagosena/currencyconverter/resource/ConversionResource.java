package com.thiagosena.currencyconverter.resource;

import com.thiagosena.currencyconverter.dto.TransactionDTO;
import com.thiagosena.currencyconverter.service.ConvertService;

import javax.inject.Inject;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
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
			@QueryParam("user_id") @DecimalMin("1") Long userId,
			@QueryParam("source") @Size(min = 3, max = 3) String source,
			@QueryParam("target") @Size(min = 3, max = 3) String target,
			@QueryParam("value") @DecimalMin("0.01") BigDecimal value
	) {
		var transactionDTO = new TransactionDTO(userId, source, value, target);
		return convertService.convert(transactionDTO);
	}
}

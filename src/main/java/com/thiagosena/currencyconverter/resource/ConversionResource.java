package com.thiagosena.currencyconverter.resource;

import com.thiagosena.currencyconverter.dto.TransactionDTO;
import com.thiagosena.currencyconverter.service.ConvertService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;

@Path("/api/v1")
@Tag(name = "Conversion")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ConversionResource {

	@Inject
	ConvertService convertService;

	@GET
	@Path("/convert")
	@Operation(summary = "Convert one currency to another that returns result in json format",
			description = "This API provides automation capability for converting one currency into another with https://exchangeratesapi.io service.")
	public TransactionDTO convert(
			@QueryParam("user_id") @NotNull @DecimalMin("1") Long userId,
			@QueryParam("source") @NotNull @Size(min = 3, max = 3) @DefaultValue("EUR") String source,
			@QueryParam("target") @NotNull @Size(min = 3, max = 3) String target,
			@QueryParam("value") @NotNull @DecimalMin("0.01") BigDecimal value
	) throws BadRequestException {
		return convertService.convert(userId, source, target, value);
	}


}

package com.thiagosena.currencyconverter.resource;

import com.thiagosena.currencyconverter.model.Transaction;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/v1")
@Tag(name = "Transactions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TrasactionResource {

	@GET
	@Path("/transaction")
	public List<Transaction> getAllTrasactionByUserId(@QueryParam("user_id") @NotNull Long userId) {
		return Transaction.findAllByUserId(userId);
	}
}

package com.thiagosena.currencyconverter.resource;

import com.thiagosena.currencyconverter.model.Transaction;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
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
	@Operation(summary = "Get all transaction by user")
	@APIResponse(responseCode = "200", description = "Returned all transactions by user")
	@APIResponse(responseCode = "400", description = "Bad Request - make sure the userId field is entered.")
	@APIResponse(responseCode = "500", description = "Internal Server Error")
	public List<Transaction> getAllTrasactionByUserId(@QueryParam("user_id") @NotNull Long userId) {
		return Transaction.findAllByUserId(userId);
	}
}

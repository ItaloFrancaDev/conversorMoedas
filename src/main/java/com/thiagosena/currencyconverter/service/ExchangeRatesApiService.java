package com.thiagosena.currencyconverter.service;

import com.thiagosena.currencyconverter.dto.ExchangeRatesApiDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/latest")
@ApplicationScoped
@RegisterRestClient
public interface ExchangeRatesApiService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	ExchangeRatesApiDTO getExchangeRates(@QueryParam("base") String base, @QueryParam("symbols") String symbols, @QueryParam("access_key") String apiKey);
}

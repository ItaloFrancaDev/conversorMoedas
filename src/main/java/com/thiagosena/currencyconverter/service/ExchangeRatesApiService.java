package com.thiagosena.currencyconverter.service;

import com.thiagosena.currencyconverter.dto.ExchangeRatesApiDTO;
import com.thiagosena.currencyconverter.exception.handler.BadRequestResponseExceptionHandler;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/latest")
@ApplicationScoped
@RegisterRestClient
@RegisterClientHeaders
@RegisterProvider(BadRequestResponseExceptionHandler.class)
public interface ExchangeRatesApiService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	ExchangeRatesApiDTO getExchangeRates(@QueryParam("base") String base, @QueryParam("symbols") String symbols, @QueryParam("access_key") String apiKey) throws ResteasyWebApplicationException;
}

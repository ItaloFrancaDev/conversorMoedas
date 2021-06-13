package com.thiagosena.currencyconverter.resource;

import com.thiagosena.currencyconverter.dto.UserDTO;
import com.thiagosena.currencyconverter.model.User;
import com.thiagosena.currencyconverter.service.UserService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/v1")
@Tag(name = "Users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	@Inject
	UserService userService;

	@POST
	@Path("/user")
	@Operation(summary = "Register an user")
	@APIResponse(responseCode = "201", description = "Created User")
	@APIResponse(responseCode = "400", description = "Bad Request - check object fields rules")
	@APIResponse(responseCode = "500", description = "Internal Server Error")
	public Response save(@Valid UserDTO userDTO) {
		return Response.ok(userService.persist(userDTO)).status(201).build();
	}

	@GET
	@Path("/users")
	@Operation(summary = "Get all users")
	@APIResponse(responseCode = "200", description = "Returned all users")
	@APIResponse(responseCode = "400", description = "Bad Request - check object fields rules")
	@APIResponse(responseCode = "500", description = "Internal Server Error")
	public List<User> listAll() {
		return User.getAll();
	}
}

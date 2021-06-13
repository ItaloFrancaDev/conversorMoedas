package com.thiagosena.currencyconverter.resource;

import com.thiagosena.currencyconverter.dto.UserDTO;
import com.thiagosena.currencyconverter.model.User;
import com.thiagosena.currencyconverter.service.UserService;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
	public User save(@Valid UserDTO userDTO) {
		return userService.persist(userDTO);
	}

	@GET
	@Path("/users")
	public List<User> listAll() {
		return User.getAll();
	}
}

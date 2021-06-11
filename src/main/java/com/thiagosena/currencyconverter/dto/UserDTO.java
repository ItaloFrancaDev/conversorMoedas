package com.thiagosena.currencyconverter.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {

	@NotNull
	@NotBlank
	@Size(min = 3, max = 256, message = "The name parameter cannot exceed 256 characters and must be at least 3 characters")
	public String name;

	public UserDTO() {
	}

	public UserDTO(String name) {
		this.name = name;
	}
}

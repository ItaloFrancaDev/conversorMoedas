package com.thiagosena.currencyconverter.service;

import com.thiagosena.currencyconverter.dto.UserDTO;
import com.thiagosena.currencyconverter.model.User;

public interface UserService {
	User persist(UserDTO userDTO);
}

package com.italofranca.currencyconverter.service;

import com.italofranca.currencyconverter.dto.UserDTO;
import com.italofranca.currencyconverter.model.User;

public interface UserService {
	User persist(UserDTO userDTO);
}

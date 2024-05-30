package com.italofranca.currencyconverter.service.impl;

import com.italofranca.currencyconverter.dto.UserDTO;
import com.italofranca.currencyconverter.model.User;
import com.italofranca.currencyconverter.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;

@ApplicationScoped
public class UserServiceImpl implements UserService {

	@Override
	@Transactional
	public User persist(UserDTO userDTO) {
		if (userDTO == null) {
			throw new BadRequestException("The User object is required");
		} else if (userDTO.name.length() < 3 || userDTO.name.length() > 256) {
			throw new BadRequestException("The name parameter cannot exceed 256 characters and must be at least 3 characters");
		}
		var user = new User(userDTO.name);
		user.persistAndFlush();
		return user;
	}
}

package com.thiagosena.currencyconverter.service.impl;

import com.thiagosena.currencyconverter.dto.UserDTO;
import com.thiagosena.currencyconverter.model.User;
import com.thiagosena.currencyconverter.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UserServiceImpl implements UserService {

	@Override
	@Transactional
	public User persist(UserDTO userDTO) {
		User user = new User(userDTO.name);
		user.persistAndFlush();
		return user;
	}

	@Override
	public List<User> listAll() {
		return User.listAll();
	}
}

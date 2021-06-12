package com.thiagosena.currencyconverter.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.common.constraint.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class User extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_sequence")
	@SequenceGenerator(name = "user_sequence", sequenceName = "NR_USER_ID_SEQ", allocationSize = 1)
	public Long id;

	@NotNull
	@NotBlank
	@Size(min = 3, max = 256)
	public String name;

	public User() {
	}

	public User(String name) {
		this.name = name;
	}

	public static User getById(Long userId) {
		return findById(userId);
	}

	public static List<User> getAll() {
		return listAll();
	}
}

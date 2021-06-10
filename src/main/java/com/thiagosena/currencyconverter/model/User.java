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

@Entity
public class User extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_sequence")
	@SequenceGenerator(name = "user_sequence", sequenceName = "NR_USER_ID_SEQ", allocationSize = 1)
	public Long id;

	@NotNull
	@NotBlank
	@Size(min = 3, max = 256, message = "The name parameter cannot exceed 256 characters and must be at least 3 characters")
	public String name;

}

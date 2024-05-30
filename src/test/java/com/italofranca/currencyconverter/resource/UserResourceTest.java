package com.italofranca.currencyconverter.resource;

import com.italofranca.currencyconverter.dto.UserDTO;
import com.italofranca.currencyconverter.model.Transaction;
import com.italofranca.currencyconverter.model.User;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;

@QuarkusTest
class UserResourceTest {

	@BeforeEach
	@Transactional
	void setUp() {
		Transaction.deleteAll();
		User.deleteAll();

		User user1 = new User("Test1");
		User user2 = new User("Test2");
		user1.persistAndFlush();
		user2.persistAndFlush();
	}

	@Test
	void whenPersistUser_ThenReturnOne() {
		given()
				.header("Content-Type", MediaType.APPLICATION_JSON)
				.body(new UserDTO("Test1"))
				.when()
				.post("/api/v1/user")
				.then()
				.statusCode(201)
				.body("name", is("Test1"));
	}

	@Test
	void whenListAllUsers_ThenReturnAll() {
		given()
				.when().get("/api/v1/users")
				.then()
				.statusCode(200)
				.body("$.size()", is(2),
						"name", containsInAnyOrder("Test1", "Test2"));

	}

}
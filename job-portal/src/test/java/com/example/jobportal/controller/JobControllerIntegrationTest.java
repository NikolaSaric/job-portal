package com.example.jobportal.controller;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import io.restassured.RestAssured;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class JobControllerIntegrationTest {
	@LocalServerPort
	private int port;

	@BeforeEach
	public void setUp() throws Exception {
		RestAssured.port = port;
	}
	
	
	@Test
	@Order(1)
	public void get_returnAllJobsAsDtos() {

		when().get("/api/job").then().statusCode(200)
		.assertThat().body("size()", is(3))
		.assertThat().body("title", hasItems("Test 1", "Test 2", "Test 3"))
		.assertThat().body("description", hasItems("Some description for test 1", "Some description for test 2", "Some description for test 3"))
		.assertThat().body("salary", hasItems(500, 1000, 1500))
		.assertThat().body("contact", hasItems("+381 000 000 00", "+381 111", "+381 31 52 52353"));
	}
	
	@Test
	@Order(2)
	public void post_returnNewJobAsDto() {

		Map<String, String> job = new HashMap<>();
		job.put("id", null);
		job.put("title", "New Job");
		job.put("description", "Description of new job");
		job.put("salary", "700");
		job.put("contact", "123 - 456");
		
		given().contentType("application/json")
		.body(job)
		.when().post("/api/job")
		.then().statusCode(201)
		.assertThat().body("title", equalTo("New Job"))
		.assertThat().body("description", equalTo("Description of new job"))
		.assertThat().body("salary", equalTo(700))
		.assertThat().body("contact", equalTo("123 - 456"));
	}
	
	@Test
	public void postTitleIsTooShort_returnBadRequest() {

		Map<String, String> job = new HashMap<>();
		job.put("id", null);
		job.put("title", "Ne");
		job.put("description", "Description of new job");
		job.put("salary", "700");
		job.put("contact", "123 - 456");
		
		given().contentType("application/json")
		.body(job)
		.when().post("/api/job")
		.then().statusCode(400);
	}
	
	@Test
	public void postDescriptionIsBlank_returnBadRequest() {

		Map<String, String> job = new HashMap<>();
		job.put("id", null);
		job.put("title", "New Job");
		job.put("description", "   ");
		job.put("salary", "700");
		job.put("contact", "123 - 456");
		
		given().contentType("application/json")
		.body(job)
		.when().post("/api/job")
		.then().statusCode(400);
	}
	
	@Test
	public void postSalaryIsNegative_returnBadRequest() {

		Map<String, String> job = new HashMap<>();
		job.put("id", null);
		job.put("title", "New Job");
		job.put("description", "Description of new job");
		job.put("salary", "-700");
		job.put("contact", "123 - 456");
		
		given().contentType("application/json")
		.body(job)
		.when().post("/api/job")
		.then().statusCode(400);
	}
	
	@Test
	public void postContactIsMissing_returnBadRequest() {

		Map<String, String> job = new HashMap<>();
		job.put("id", null);
		job.put("title", "New Job");
		job.put("description", "Description of new job");
		job.put("salary", "700");
		
		given().contentType("application/json")
		.body(job)
		.when().post("/api/job")
		.then().statusCode(400);
	}
}

package br.com.restful.integration.controller;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.restful.configs.TestConfigs;
import br.com.restful.integration.container.AbstractIntegrationTest;
import br.com.restful.integration.viewObject.PersonValueObject;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class PersonControllerJsonTest extends AbstractIntegrationTest {

	private static RequestSpecification specification;
	private static ObjectMapper objectMapper;

	private static PersonValueObject person;

	private static final String FIRST_NAME = "John";
	private static final String LAST_NAME = "Doe";
	private static final String ADDRESS = "Some address";
	private static final String GENDER = "Male";

	private static final String INVALID_CORS = "Invalid CORS request";

	private void mockPerson() {
		person.setFirstName(FIRST_NAME);
		person.setLastName(LAST_NAME);
		person.setAddress(ADDRESS);
		person.setGender(GENDER);
	}

	@BeforeAll
	public static void setUp() {
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		person = new PersonValueObject();
	}

	@Test
	@Order(1)
	public void testCreate() throws JsonMappingException, JsonProcessingException {
		mockPerson();

		specification = new RequestSpecBuilder()
				.addHeader(TestConfigs.HEADER_PARAM_ORIGIN, "http://localhost:4200")
				.setBasePath(" /api/person/v1")
				.setPort(TestConfigs.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();

		String content = given()
				.spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_JSON)
				.body(person)
				.when()
				.post()
				.then()
				.statusCode(200)
				.extract()
				.body()
				.asString();

		PersonValueObject createdPerson = objectMapper.readValue(content, PersonValueObject.class);
		person = createdPerson;

		assertTrue(createdPerson.getId() > 0);

		assertNotNull(createdPerson);
		assertNotNull(createdPerson.getFirstName());
		assertNotNull(createdPerson.getLastName());
		assertNotNull(createdPerson.getAddress());
		assertNotNull(createdPerson.getGender());

		assertTrue(createdPerson.getFirstName().equals(FIRST_NAME));
		assertTrue(createdPerson.getLastName().equals(LAST_NAME));
		assertTrue(createdPerson.getAddress().equals(ADDRESS));
		assertTrue(createdPerson.getGender().equals(GENDER));

	}

	@Test
	@Order(2)
	public void testCreateWithWrongOrigin() {
		mockPerson();

		specification = new RequestSpecBuilder()
				.addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCALHOST_9000)
				.setBasePath(" /api/person/v1")
				.setPort(TestConfigs.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();

		String content = given()
				.spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_JSON)
				.body(person)
				.when()
				.post()
				.then()
				.statusCode(403)// Invalid cors request
				.extract()
				.body()
				.asString();

		assertNotNull(content);
		assertTrue(content.equals(INVALID_CORS));

	}

	@Test
	@Order(3)
	public void testFindById() throws JsonMappingException, JsonProcessingException {
		mockPerson();

		specification = new RequestSpecBuilder()
				.addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCALHOST_4200)
				.setBasePath(" /api/person/v1")
				.setPort(TestConfigs.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();

		String content = given()
				.spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_JSON)
				.pathParam("id", person.getId())
				.when()
				.get("{id}")
				.then()
				.statusCode(200)
				.extract()
				.body()
				.asString();

		PersonValueObject entity = objectMapper.readValue(content, PersonValueObject.class);
		person = entity;

		assertNotNull(entity);
		assertTrue(entity.getId() > 0);
		assertNotNull(entity.getFirstName());
		assertNotNull(entity.getLastName());
		assertNotNull(entity.getAddress());
		assertNotNull(entity.getGender());

		assertTrue(entity.getFirstName().equals(FIRST_NAME));
		assertTrue(entity.getLastName().equals(LAST_NAME));
		assertTrue(entity.getAddress().equals(ADDRESS));
		assertTrue(entity.getGender().equals(GENDER));

	}

	@Test
	@Order(4)
	public void testFindByIdWithWrongOrigin() throws JsonMappingException, JsonProcessingException {
		mockPerson();

		specification = new RequestSpecBuilder()
				.addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCALHOST_9000)
				.setBasePath(" /api/person/v1")
				.setPort(TestConfigs.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();

		String content = given()
				.spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_JSON)
				.pathParam("id", person.getId())
				.when()
				.get("{id}")
				.then()
				.statusCode(403)
				.extract()
				.body()
				.asString();

		assertNotNull(content);

		assertTrue(content.equals(INVALID_CORS));

	}
}
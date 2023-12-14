package com.kevinraupp.api.studydockeraws.integrationtests.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevinraupp.api.studydockeraws.config.TestConfigs;
import com.kevinraupp.api.studydockeraws.integrationtests.testcontainers.AbstractIntegrationTest;
import com.kevinraupp.api.studydockeraws.integrationtests.vo.PersonVOTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerJsonTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;
    private static PersonVOTest person;

    @BeforeAll
    public static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        person = new PersonVOTest();
    }

    @Test
    @Order(1)
    public void testCreate() throws JsonProcessingException {
        mockPerson();
        specification = new RequestSpecBuilder().addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_KEVIN)
                .setBasePath("/api/person/v1").setPort(TestConfigs.SERVER_PORT).addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();

        var content = given().spec(specification).contentType(TestConfigs.CONTENT_TYPE_JSON).body(person).when().post()
                .then().statusCode(200).extract().body().asString();


        PersonVOTest createdPerson = objectMapper.readValue(content, PersonVOTest.class);
        person = createdPerson;


        assertNotNull(createdPerson);

        assertNotNull(createdPerson.getId());
        assertNotNull(createdPerson.getFirstName());
        assertNotNull(createdPerson.getLastName());
        assertNotNull(createdPerson.getAddress());
        assertNotNull(createdPerson.getGender());

        assertTrue(createdPerson.getId() > 0);

        assertEquals("Kevin", person.getFirstName());
        assertEquals("Raupp", person.getLastName());
        assertEquals("Gravatai", person.getAddress());
        assertEquals("M", person.getGender());
    }

    @Test
    @Order(2)
    public void testCreateWithWrongOrigin() throws JsonProcessingException {
        mockPerson();
        specification = new RequestSpecBuilder().addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_KEVIN2)
                .setBasePath("/api/person/v1").setPort(TestConfigs.SERVER_PORT).addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();

        var content = given().spec(specification).contentType(TestConfigs.CONTENT_TYPE_JSON).body(person).when().post()
                .then().statusCode(403).extract().body().asString();

        assertNotNull(content);

        assertEquals("Invalid CORS request", content);
    }


    @Test
    @Order(3)
    public void testFindById() throws JsonProcessingException {
        mockPerson();
        specification = new RequestSpecBuilder().addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_KEVIN)
                .setBasePath("/api/person/v1").setPort(TestConfigs.SERVER_PORT).addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();

        var content = given().spec(specification).contentType(TestConfigs.CONTENT_TYPE_JSON).pathParams("id", person.getId()).when().get("{id}")
                .then().statusCode(200).extract().body().asString();


        PersonVOTest createdPerson = objectMapper.readValue(content, PersonVOTest.class);
        person = createdPerson;


        assertNotNull(createdPerson);

        assertNotNull(createdPerson.getId());
        assertNotNull(createdPerson.getFirstName());
        assertNotNull(createdPerson.getLastName());
        assertNotNull(createdPerson.getAddress());
        assertNotNull(createdPerson.getGender());

        assertTrue(createdPerson.getId() > 0);

        assertEquals("Kevin", person.getFirstName());
        assertEquals("Raupp", person.getLastName());
        assertEquals("Gravatai", person.getAddress());
        assertEquals("M", person.getGender());
    }

    @Test
    @Order(4)
    public void testFindByIdWithWrongOrigin() throws JsonProcessingException {
        mockPerson();
        specification = new RequestSpecBuilder().addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_KEVIN2)
                .setBasePath("/api/person/v1").setPort(TestConfigs.SERVER_PORT).addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();

        var content = given().spec(specification).contentType(TestConfigs.CONTENT_TYPE_JSON).pathParams("id", person.getId()).when().get("{id}")
                .then().statusCode(403).extract().body().asString();

        assertNotNull(content);

        assertEquals("Invalid CORS request", content);
    }

    private void mockPerson() {
        person.setFirstName("Kevin");
        person.setLastName("Raupp");
        person.setAddress("Gravatai");
        person.setGender("M");
    }
}
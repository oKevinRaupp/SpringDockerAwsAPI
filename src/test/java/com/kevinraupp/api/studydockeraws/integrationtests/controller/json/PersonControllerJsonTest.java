package com.kevinraupp.api.studydockeraws.integrationtests.controller.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevinraupp.api.studydockeraws.config.TestConfigs;
import com.kevinraupp.api.studydockeraws.integrationtests.testcontainers.AbstractIntegrationTest;
import com.kevinraupp.api.studydockeraws.integrationtests.vo.AccountCredentialsVOTest;
import com.kevinraupp.api.studydockeraws.integrationtests.vo.PersonVOTest;
import com.kevinraupp.api.studydockeraws.integrationtests.vo.TokenVOTest;
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

    static TokenVOTest tokenVO;

    @Test
    @Order(0)
    public void testSignin() throws JsonProcessingException {
        AccountCredentialsVOTest credentials = new AccountCredentialsVOTest("kevin", "admin123");

        var tokenVO = given().basePath("/auth/signin").port(TestConfigs.SERVER_PORT).contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(credentials).when().post()
                .then().statusCode(200).extract().body().as(TokenVOTest.class);


        assertNotNull(tokenVO.getAccessToken());
        assertNotNull(tokenVO.getRefreshToken());
        assertNotNull(tokenVO.getCreated());
        assertNotNull(tokenVO.getExpiration());
        assertNotNull(tokenVO.getUsername());
        assertNotNull(tokenVO.getAuthenticated());

    }

    @Test
    @Order(1)
    public void refresh() throws JsonProcessingException {
        AccountCredentialsVOTest credentials = new AccountCredentialsVOTest("kevin", "admin123");

        var tokenVO = given().basePath("/auth/signin").port(TestConfigs.SERVER_PORT).contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(credentials).when().post()
                .then().statusCode(200).extract().body().as(TokenVOTest.class);

        TokenVOTest newTokenVO = given().basePath("/auth/refresh").port(TestConfigs.SERVER_PORT).contentType(TestConfigs.CONTENT_TYPE_JSON)
                .pathParam("username", tokenVO.getUsername()).header(TestConfigs.HEADER_PARAM_AUTHORIZATION, "Bearer " + tokenVO.getRefreshToken())
                .when().put("{username}")
                .then().statusCode(200).extract().body().as(TokenVOTest.class);


        assertNotNull(newTokenVO.getAccessToken());
        assertNotNull(newTokenVO.getRefreshToken());
        assertNotNull(newTokenVO.getCreated());
        assertNotNull(newTokenVO.getExpiration());
        assertNotNull(newTokenVO.getUsername());
        assertNotNull(newTokenVO.getAuthenticated());
    }

    @Test
    @Order(2)
    public void authorization() throws JsonProcessingException {
        AccountCredentialsVOTest credentials = new AccountCredentialsVOTest("kevin", "admin123");

        var accessToken = given().basePath("/auth/signin").port(TestConfigs.SERVER_PORT).contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(credentials).when().post()
                .then().statusCode(200).extract().body().as(TokenVOTest.class).getAccessToken();

        specification = new RequestSpecBuilder().addHeader(TestConfigs.HEADER_PARAM_AUTHORIZATION, "Bearer " + accessToken)
                .setBasePath("/api/person/v1").setPort(TestConfigs.SERVER_PORT).addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
    }

    @Test
    @Order(3)
    public void testCreate() throws JsonProcessingException {
        mockPerson();

        var content = given().spec(specification).contentType(TestConfigs.CONTENT_TYPE_JSON).header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_KEVIN)
                .body(person).when().post()
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
    public void testCreateWithWrongOrigin() throws JsonProcessingException {
        mockPerson();

        var content = given().spec(specification).contentType(TestConfigs.CONTENT_TYPE_JSON).header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_KEVIN2)
                .body(person).when().post()
                .then().statusCode(403).extract().body().asString();

        assertNotNull(content);

        assertEquals("Invalid CORS request", content);
    }


    @Test
    @Order(5)
    public void testFindById() throws JsonProcessingException {
        mockPerson();

        var content = given().spec(specification).contentType(TestConfigs.CONTENT_TYPE_JSON).header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_KEVIN)
                .pathParams("id", person.getId()).when().get("{id}")
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
    @Order(6)
    public void testFindByIdWithWrongOrigin() throws JsonProcessingException {
        mockPerson();

        var content = given().spec(specification).contentType(TestConfigs.CONTENT_TYPE_JSON).header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_KEVIN2)
                .pathParams("id", person.getId()).when().get("{id}")
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
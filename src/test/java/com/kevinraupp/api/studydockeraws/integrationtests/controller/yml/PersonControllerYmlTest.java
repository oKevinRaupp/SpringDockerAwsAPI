package com.kevinraupp.api.studydockeraws.integrationtests.controller.yml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kevinraupp.api.studydockeraws.config.TestConfigs;
import com.kevinraupp.api.studydockeraws.integrationtests.controller.yml.mapper.YMLMapper;
import com.kevinraupp.api.studydockeraws.integrationtests.testcontainers.AbstractIntegrationTest;
import com.kevinraupp.api.studydockeraws.integrationtests.vo.AccountCredentialsVOTest;
import com.kevinraupp.api.studydockeraws.integrationtests.vo.PersonVOTest;
import com.kevinraupp.api.studydockeraws.integrationtests.vo.TokenVOTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerYmlTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static YMLMapper objectMapper;
    private static PersonVOTest person;

    @BeforeAll
    public static void setup() {

        objectMapper = new YMLMapper();

        person = new PersonVOTest();
    }

    static TokenVOTest tokenVO;

    @Test
    @Order(0)
    public void testSignin() throws JsonProcessingException {

        AccountCredentialsVOTest credentials = new AccountCredentialsVOTest("kevin", "admin123");

        var tokenVO = given().config(RestAssuredConfig.config().encoderConfig(EncoderConfig.encoderConfig()
                        .encodeContentTypeAs(TestConfigs.CONTENT_TYPE_YML, ContentType.TEXT))).basePath("/auth/signin")
                .port(TestConfigs.SERVER_PORT).contentType(TestConfigs.CONTENT_TYPE_YML).accept(TestConfigs.CONTENT_TYPE_YML).body(credentials, objectMapper)
                .when().post().then().statusCode(200).extract()
                .body().as(TokenVOTest.class, objectMapper).getAccessToken();

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_AUTHORIZATION, "Bearer " + tokenVO).setBasePath("/api/person/v1")
                .setPort(TestConfigs.SERVER_PORT).addFilter(new RequestLoggingFilter(LogDetail.ALL)).addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
    }

    @Test
    @Order(1)
    public void testCreate() throws JsonProcessingException {

        mockPerson();

        var createdPerson = given().spec(specification).config(RestAssuredConfig.config().encoderConfig(EncoderConfig.encoderConfig()
                        .encodeContentTypeAs(TestConfigs.CONTENT_TYPE_YML, ContentType.TEXT))).contentType(TestConfigs.CONTENT_TYPE_YML)
                .accept(TestConfigs.CONTENT_TYPE_YML).body(person, objectMapper).when().post()
                .then().statusCode(200).extract().body().as(PersonVOTest.class, objectMapper);

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
    public void testFindById() throws JsonProcessingException {

        mockPerson();

        var createdPerson = given().spec(specification).config(RestAssuredConfig.config().encoderConfig(EncoderConfig.encoderConfig()
                        .encodeContentTypeAs(TestConfigs.CONTENT_TYPE_YML, ContentType.TEXT))).contentType(TestConfigs.CONTENT_TYPE_YML)
                .accept(TestConfigs.CONTENT_TYPE_YML).header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_KEVIN)
                .pathParams("id", person.getId())
                .when().get("{id}").then().statusCode(200).extract().body().as(PersonVOTest.class, objectMapper);

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
    @Order(3)
    public void testUpdate() throws JsonProcessingException {

        person.setFirstName("Teste");

        var createdPerson = given().spec(specification).config(RestAssuredConfig.config().encoderConfig(EncoderConfig.encoderConfig()
                        .encodeContentTypeAs(TestConfigs.CONTENT_TYPE_YML, ContentType.TEXT))).contentType(TestConfigs.CONTENT_TYPE_YML)
                .accept(TestConfigs.CONTENT_TYPE_YML).body(person, objectMapper).when().post()
                .then().statusCode(200).extract().body().as(PersonVOTest.class, objectMapper);

        person = createdPerson;


        assertNotNull(createdPerson);

        assertNotNull(createdPerson.getId());
        assertNotNull(createdPerson.getFirstName());
        assertNotNull(createdPerson.getLastName());
        assertNotNull(createdPerson.getAddress());
        assertNotNull(createdPerson.getGender());

        assertEquals(person.getId(), createdPerson.getId());

        assertEquals("Teste", person.getFirstName());
        assertEquals("Raupp", person.getLastName());
        assertEquals("Gravatai", person.getAddress());
        assertEquals("M", person.getGender());
    }

    @Test
    @Order(4)
    public void testDelete() {

        given().spec(specification).contentType(TestConfigs.CONTENT_TYPE_YML).accept(TestConfigs.CONTENT_TYPE_YML)
                .pathParam("id", person.getId()).when().delete("{id}")
                .then().statusCode(204);
    }

    @Test
    @Order(5)
    public void testFindAll() throws JsonProcessingException {

        var content = given().spec(specification).config(RestAssuredConfig.config().encoderConfig(EncoderConfig.encoderConfig()
                        .encodeContentTypeAs(TestConfigs.CONTENT_TYPE_YML, ContentType.TEXT))).contentType(TestConfigs.CONTENT_TYPE_YML)
                .accept(TestConfigs.CONTENT_TYPE_YML).when().get().then()
                .statusCode(200).extract().body().as(PersonVOTest[].class, objectMapper);

        List<PersonVOTest> people = Arrays.asList(content);

        PersonVOTest foundPersonOne = people.get(0);

        assertNotNull(foundPersonOne.getId());
        assertNotNull(foundPersonOne.getFirstName());
        assertNotNull(foundPersonOne.getLastName());
        assertNotNull(foundPersonOne.getAddress());
        assertNotNull(foundPersonOne.getGender());

        assertEquals(1, foundPersonOne.getId());

        assertEquals("Eliseu", foundPersonOne.getFirstName());
        assertEquals("Meyer", foundPersonOne.getLastName());
        assertEquals("Gravataí - RS - Brasil", foundPersonOne.getAddress());
        assertEquals("M", foundPersonOne.getGender());

        PersonVOTest foundPersonFour = people.get(3);

        assertNotNull(foundPersonFour.getId());
        assertNotNull(foundPersonFour.getFirstName());
        assertNotNull(foundPersonFour.getLastName());
        assertNotNull(foundPersonFour.getAddress());
        assertNotNull(foundPersonFour.getGender());

        assertEquals(4, foundPersonFour.getId());

        assertEquals("Paulo", foundPersonFour.getFirstName());
        assertEquals("Estevam", foundPersonFour.getLastName());
        assertEquals("Curitiba - PR - Brasil", foundPersonFour.getAddress());
        assertEquals("M", foundPersonFour.getGender());
    }


    private void mockPerson() {
        person.setFirstName("Kevin");
        person.setLastName("Raupp");
        person.setAddress("Gravatai");
        person.setGender("M");
        person.setEnabled(false);
    }
}
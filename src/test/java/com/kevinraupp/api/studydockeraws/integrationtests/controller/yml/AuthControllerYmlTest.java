package com.kevinraupp.api.studydockeraws.integrationtests.controller.yml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kevinraupp.api.studydockeraws.config.TestConfigs;
import com.kevinraupp.api.studydockeraws.integrationtests.controller.yml.mapper.YMLMapper;
import com.kevinraupp.api.studydockeraws.integrationtests.testcontainers.AbstractIntegrationTest;
import com.kevinraupp.api.studydockeraws.integrationtests.vo.AccountCredentialsVOTest;
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

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthControllerYmlTest extends AbstractIntegrationTest {

    private static TokenVOTest tokenVO;
    private static YMLMapper mapper;
    private static RequestSpecification specification;

    @BeforeAll
    public static void setUp() {
        mapper = new YMLMapper();
    }

    @Test
    @Order(0)
    public void testSignin() throws JsonProcessingException {
        AccountCredentialsVOTest credentials = new AccountCredentialsVOTest("kevin", "admin123");

        specification = new RequestSpecBuilder()
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();

        tokenVO = given().spec(specification).config(RestAssuredConfig.config().encoderConfig(EncoderConfig.encoderConfig()
                        .encodeContentTypeAs(TestConfigs.CONTENT_TYPE_YML, ContentType.TEXT))).accept(TestConfigs.CONTENT_TYPE_YML)
                .basePath("/auth/signin").port(TestConfigs.SERVER_PORT).contentType(TestConfigs.CONTENT_TYPE_YML)
                .body(credentials, mapper).when().post()
                .then().statusCode(200).extract().body().as(TokenVOTest.class, mapper);


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

        TokenVOTest newTokenVO = given().spec((specification)).config(RestAssuredConfig.config().encoderConfig(EncoderConfig.encoderConfig()
                        .encodeContentTypeAs(TestConfigs.CONTENT_TYPE_YML, ContentType.TEXT))).accept(TestConfigs.CONTENT_TYPE_YML)
                .basePath("/auth/refresh").port(TestConfigs.SERVER_PORT).contentType(TestConfigs.CONTENT_TYPE_JSON)
                .pathParam("username", tokenVO.getUsername()).header(TestConfigs.HEADER_PARAM_AUTHORIZATION, "Bearer " + tokenVO.getRefreshToken())
                .when().put("{username}")
                .then().statusCode(200).extract().body().as(TokenVOTest.class, mapper);


        assertNotNull(newTokenVO.getAccessToken());
        assertNotNull(newTokenVO.getRefreshToken());
        assertNotNull(newTokenVO.getCreated());
        assertNotNull(newTokenVO.getExpiration());
        assertNotNull(newTokenVO.getUsername());
        assertNotNull(newTokenVO.getAuthenticated());

    }

}
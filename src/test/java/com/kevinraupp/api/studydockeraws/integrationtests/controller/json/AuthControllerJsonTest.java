package com.kevinraupp.api.studydockeraws.integrationtests.controller.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kevinraupp.api.studydockeraws.config.TestConfigs;
import com.kevinraupp.api.studydockeraws.integrationtests.testcontainers.AbstractIntegrationTest;
import com.kevinraupp.api.studydockeraws.integrationtests.vo.AccountCredentialsVOTest;
import com.kevinraupp.api.studydockeraws.integrationtests.vo.TokenVOTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthControllerJsonTest extends AbstractIntegrationTest {

    private static TokenVOTest tokenVO;

    @Test
    @Order(0)
    public void testSignin() throws JsonProcessingException {
        AccountCredentialsVOTest credentials = new AccountCredentialsVOTest("kevin", "admin123");

        tokenVO = given().basePath("/auth/signin").port(TestConfigs.SERVER_PORT).contentType(TestConfigs.CONTENT_TYPE_JSON)
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

}
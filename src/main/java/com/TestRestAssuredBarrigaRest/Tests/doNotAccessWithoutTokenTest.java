package com.TestRestAssuredBarrigaRest.Tests;

import com.TestRestAssuredBarrigaRest.Core.BaseTest;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class doNotAccessWithoutTokenTest extends BaseTest {

    @Test
    public void doNotAccessWithoutToken(){
        given()
                .when()
                .get("/contas")
                .then()
                .statusCode(401);
    }
}

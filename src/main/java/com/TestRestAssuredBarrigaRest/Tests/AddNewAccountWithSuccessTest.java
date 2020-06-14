package com.TestRestAssuredBarrigaRest.Tests;

import com.TestRestAssuredBarrigaRest.Core.BaseTest;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class AddNewAccountWithSuccessTest extends BaseTest {

 @Test
    public void addNewAccountWithSuccess(){

        given().body("{\"nome\" : \"New Conta 01\"}").header("Authorization", "JWT " + TOKEN)
                .when().post("/contas")
                .then().statusCode(201);
    }
}

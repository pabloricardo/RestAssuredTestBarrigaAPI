package com.TestRestAssuredBarrigaRest.Tests;

import com.TestRestAssuredBarrigaRest.Core.BaseTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddNewAccountWithSuccessTest extends BaseTest {

    @Test
    @Order(1)
    public void addNewAccountWithSuccess() {

        given().body("{\"nome\" : \"New Conta 01\"}").header("Authorization", "JWT " + TOKEN)
                .when().post("/contas")
                .then().statusCode(201);
    }

    @Test
    @Order(2)
    public void doNotAddAccountWithRepeatedName(){
        given().body("{\"nome\" : \"New Conta 01\"}").header("Authorization", "JWT " + TOKEN)
                .when().post("/contas")
                .then().statusCode(400).body("error", is("Já existe uma conta com esse nome!"));
    }

}

package com.TestRestAssuredBarrigaRest.Tests;

import com.TestRestAssuredBarrigaRest.Core.BaseTest;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class EditAccountTest extends BaseTest {
    @Test
    public void editAccount(){

        String nameEdited = "NameEdited";
        String json = "{\"nome\": \"%s\"}";
        json = String.format(json, nameEdited);
        given().header("Authorization", "JWT " + TOKEN).body(json)
                .when().put("/contas/184088")
                .then().statusCode(200).body("nome", is(nameEdited)).log().all();
    }
}
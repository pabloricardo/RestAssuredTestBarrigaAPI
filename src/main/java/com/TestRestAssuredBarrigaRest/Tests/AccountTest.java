package com.TestRestAssuredBarrigaRest.Tests;

import com.TestRestAssuredBarrigaRest.Core.BaseTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountTest extends BaseTest {

    @Test
    public void T01_addNewAccountWithSuccess() {
        CONTA_ID =
        given().body("{\"nome\" : \"" + CONTA_NAME + "\"}").header("Authorization", "JWT " + TOKEN)
                .when().post("/contas")
                .then().statusCode(201).extract().path("id");
    }

    @Test
    public void T02_doNotAddAccountWithRepeatedName(){
        given().body("{\"nome\" : \""+CONTA_NAME+"\"}").header("Authorization", "JWT " + TOKEN)
                .when().post("/contas")
                .then().statusCode(400).body("error", is("Já existe uma conta com esse nome!"));
    }

    @Test
    public void T03_editAccount(){

        String nameEdited = CONTA_NAME + " nameEdited";
        String json = "{\"nome\": \"%s\"}";
        json = String.format(json, nameEdited);
        given().header("Authorization", "JWT " + TOKEN).body(json).pathParam("id", CONTA_ID)
                .when().put("/contas/{id}")
                .then().statusCode(200).body("nome", is(nameEdited));
    }

}

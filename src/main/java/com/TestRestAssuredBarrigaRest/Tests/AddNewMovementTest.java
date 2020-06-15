package com.TestRestAssuredBarrigaRest.Tests;

import com.TestRestAssuredBarrigaRest.Core.BaseTest;
import com.TestRestAssuredBarrigaRest.entities.Movement;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class AddNewMovementTest extends BaseTest {

    @Test
    public void addNewMovement(){
        Movement movement = new Movement();
        movement.setConta_id(184088);
        movement.setDescricao("Desc movement");
        movement.setEnvolvido("Involved movement");
        movement.setTipo("REC");
        movement.setData_transacao("01/01/2000");
        movement.setData_pagamento("10/05/2010");
        movement.setValor(100f);
        movement.setStatus(true);

        given().header("Authorization", "JWT " + TOKEN).body(movement)
                .when().post("/transacoes")
                .then().statusCode(201);
    }
}

package com.TestRestAssuredBarrigaRest.Tests;

import com.TestRestAssuredBarrigaRest.Core.BaseTest;
import com.TestRestAssuredBarrigaRest.entities.Movement;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AddNewMovementTest extends BaseTest {

    private Movement getValidMovement() {
        Movement movement = new Movement();
        movement.setConta_id(184088);
        movement.setDescricao("Desc movement");
        movement.setEnvolvido("Involved movement");
        movement.setTipo("REC");
        movement.setData_transacao("01/01/2000");
        movement.setData_pagamento("10/05/2010");
        movement.setValor(100f);
        movement.setStatus(true);
        return movement;
    }

    @Test
    public void addNewMovement(){
        Movement movement = getValidMovement();

        given().header("Authorization", "JWT " + TOKEN).body(movement)
                .when().post("/transacoes")
                .then().statusCode(201);
    }

    @Test
    public void addMovementFutureDate(){
        Movement movement = getValidMovement();
        movement.setData_transacao("14/11/2100");

        given().header("Authorization", "JWT " + TOKEN).body(movement)
                .when().post("/transacoes")
                .then().statusCode(400)
                        .body("$", hasSize(1))
                        .body("msg", hasItem("Data da Movimentação deve ser menor ou igual à data atual"));
    }

    @Test
    public void validateRequiredsFields(){
        given().header("Authorization", "JWT " + TOKEN).body("{}")
        .when().post("/transacoes")
        .then()
            .statusCode(400)
            .body("$", hasSize(8))
            .body("msg",hasItems(
                            "Data da Movimentação é obrigatório",
                            "Data do pagamento é obrigatório",
                            "Descrição é obrigatório",
                            "Interessado é obrigatório",
                            "Valor é obrigatório",
                            "Valor deve ser um número",
                            "Conta é obrigatório",
                            "Situação é obrigatório"));
    }

    @Test
    public void doNotRemoveAccountWithMovimetation(){
        given().header("Authorization", "JWT " + TOKEN)
                .when().delete("/contas/184088")
                .then().statusCode(500).body("constraint", is("transacoes_conta_id_foreign"));
    }
}

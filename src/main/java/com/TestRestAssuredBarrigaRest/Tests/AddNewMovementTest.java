package com.TestRestAssuredBarrigaRest.Tests;

import com.TestRestAssuredBarrigaRest.Core.BaseTest;
import com.TestRestAssuredBarrigaRest.Core.UtilsTest;
import com.TestRestAssuredBarrigaRest.entities.Movement;
import org.junit.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AddNewMovementTest extends BaseTest {

    private Movement getValidMovement() {
        Movement movement = new Movement();
        movement.setConta_id(CONTA_ID);
        movement.setDescricao("Desc movement");
        movement.setEnvolvido("Involved movement");
        movement.setTipo("REC");
        movement.setData_transacao(UtilsTest.getDataDifDays(-1));
        movement.setData_pagamento(UtilsTest.getDataDifDays(5));
        movement.setValor(100f);
        movement.setStatus(true);
        return movement;
    }

    @Test
    public void addNewMovement(){
        Movement movement = getValidMovement();

        MOVEMENT_ID = given().header("Authorization", "JWT " + TOKEN).body(movement)
                .when().post("/transacoes")
                .then().statusCode(201).extract().path("id");
    }

    @Test
    public void addMovementFutureDate(){
        Movement movement = getValidMovement();
        movement.setData_transacao(UtilsTest.getDataDifDays(2));

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
        given().header("Authorization", "JWT " + TOKEN).pathParam("id", CONTA_ID)
                .when().delete("/contas/{id}")
                .then().statusCode(500).body("constraint", is("transacoes_conta_id_foreign"));
    }

    @Test
    public void calculateSalary(){
        given().header("Authorization", "JWT " + TOKEN)
                .when().get("/saldo")
                .then().statusCode(200).body("find{it.conta_id == "+CONTA_ID+"}.saldo", is("100.00"));
    }

    @Test
    public void removeTransaction(){
        given().header("Authorization", "JWT " + TOKEN).pathParam("id", MOVEMENT_ID)
                .when().delete("/transacoes/{id}")
                .then().statusCode(204);
    }
}

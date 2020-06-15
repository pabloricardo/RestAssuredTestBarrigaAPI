package com.TestRestAssuredBarrigaRest.Core;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UtilsTest implements Constant {

    public static String ReturnToken() {
        Map<String,String> credentials = new HashMap<>();
        credentials.put("email", EMAIL);
        credentials.put("senha", PASSWORD);

        String token =
                given().body(credentials)
                        .when().post("/signin")
                        .then().statusCode(200).extract().path("token");
        return token;
    }
}

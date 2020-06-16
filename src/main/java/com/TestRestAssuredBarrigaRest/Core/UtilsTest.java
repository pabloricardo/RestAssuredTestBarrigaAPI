package com.TestRestAssuredBarrigaRest.Core;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

    public static int returIdByAccountName(){
        int id = given().when().get().then().extract().path("id");
        return id;
    }

    public static String getDataDifDays(Integer days){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, days);
        return getDateFormated(cal.getTime());
    }

    private static String getDateFormated(Date date){
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }
}

package com.TestRestAssuredBarrigaRest.Core;

import io.restassured.http.ContentType;

public interface Constant {

    String APP_BASE_URL = "https://barrigarest.wcaquino.me";
    Integer APP_PORT = 443; // http -> 80
    String APP_BASE_PATH = "";

    ContentType APP_CONTENT_TYPE = ContentType.JSON;

    Long MAX_TIMEOUT = 5000L;

    String USER = "test01";
    String EMAIL = "test01@test01.com";
    String PASSWORD = "123456";
}

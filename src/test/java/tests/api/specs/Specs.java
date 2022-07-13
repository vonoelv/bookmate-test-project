package tests.api.specs;

import helpers.CustomAllureListener;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class Specs {

    public static final String X_CSRF_TOKEN = "MvNs6b6K-W6rTr_lQCuVGVE0tZARBk0D4CNw";
    public static final String CSRF = "89yrWhB0wTfmGZ3tLJa5DI5i";
    public static final String BMS = "KHSKwIsx%2B884xLkxTijCxSIjHNrAa8mEVdXJMdH%2FhNmmqfDAODspoCTux%2BW8NY5nQw9iHMroj08%2BtRIOpB3DWYDHPspBm1qzJgmhPLQRs9Q%2FRcoLP3Z%2FE1Jts6A3Rl8qIm%2BFbMqeWi5x1HoX1GMb9p0jQgZ1jxmJRH0HlbxlhKWlCf0THFfXiBBdCrFh2v5cEZYif9CthKo2Urq8o3MHX4B3iMHBNNF8eeOH5c4aTXJQuoZciNpl3OVHgcIz%2BFJph2Xy3s5rTJD0%2FLdsAPZs8c%2F966qe8q%2FQwhLvPL4%3D--MHNFRAmjU9da%2Bb8V--5Yig2r40J3E4U1UJU%2FMiuQ%3D%3D";

    public static RequestSpecification request = with()
            .filter(CustomAllureListener.withCustomTemplates())
            .log().all()
            .baseUri("https://bookmate.com/p/api/v5")
            .contentType(JSON)
            .header("x-csrf-token", X_CSRF_TOKEN)
            .cookie("_csrf", CSRF)
            .cookie("bms", BMS);

    public static ResponseSpecification response = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(200)
            .build();
}

package tests.api.specs;

import config.App;
import config.Project;
import helpers.CustomAllureListener;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class Specs {

    public static RequestSpecification request = with()
            .filter(CustomAllureListener.withCustomTemplates())
            .log().all()
            .baseUri(Project.config.apiBaseUrl())
            .contentType(JSON)
            .header("x-csrf-token", App.config.xCsrfToken())
            .cookie("_csrf", App.config.csrf())
            .cookie("bms", App.config.bms());

    public static ResponseSpecification response = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(200)
            .build();
}

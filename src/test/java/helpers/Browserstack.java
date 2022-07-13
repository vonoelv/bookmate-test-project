package helpers;

import config.ProjectConfig;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;

public class Browserstack {
    static final ProjectConfig CFG = ConfigFactory.create(ProjectConfig.class);

    public static String videoUrl(String sessionId) {
        return given()
                .auth().basic(CFG.user(), CFG.key())
                .when()
                .get("https://api-cloud.browserstack.com/app-automate/sessions/" + sessionId +".json")
                .then()
                .statusCode(200)
                .extract()
                .path("automation_session.video_url");
    }
}

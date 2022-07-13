package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.ProjectConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class BrowserstackMobileDriver implements WebDriverProvider {

    static final ProjectConfig CFG = ConfigFactory.create(ProjectConfig.class);
    private static final String endpointForApkUpload = "https://%s:%s@api-cloud.browserstack.com/app-automate/upload";
    private static final String UPLOADED_APK_URL = uploadAPK();
    private static final String remoteDriver = "http://hub.browserstack.com/wd/hub";

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        System.out.println("Creating driver...");
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);
        mutableCapabilities.setCapability("browserstack.appium_version", "1.22.0");
        mutableCapabilities.setCapability("browserstack.user", CFG.user());
        mutableCapabilities.setCapability("browserstack.key", CFG.key());
        mutableCapabilities.setCapability("app", UPLOADED_APK_URL);
        mutableCapabilities.setCapability("device", "Samsung Galaxy S22 Ultra");
        mutableCapabilities.setCapability("os_version", "12.0");
        mutableCapabilities.setCapability("project", "Bookmate");
        mutableCapabilities.setCapability("build", "browserstack-build-1");
        mutableCapabilities.setCapability("name", "bookmate_test");

        return new RemoteWebDriver(getBrowserstackUrl(), mutableCapabilities);
    }

    public static URL getBrowserstackUrl() {
        try {
            return new URL(remoteDriver);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Nonnull
    private static String uploadAPK() {
        System.out.println("APK uploading...");
        return given()
                .multiPart("file", new File("src/test/resources/apk/Bookmate_8.0.3.apk"))
                .when()
                .post(format(endpointForApkUpload, CFG.user(), CFG.key()))
                .then()
                .statusCode(200)
                .body("app_url", is(notNullValue()))
                .extract().path("app_url").toString();
    }
}

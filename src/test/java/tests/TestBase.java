package tests;

import com.codeborne.selenide.Configuration;
import config.Project;
import drivers.BrowserstackMobileDriver;
import drivers.LocalMobileDriver;
import drivers.SelenoidMobileDriver;
import drivers.WebUiDriver;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.Attachments.*;

public class TestBase {

    @BeforeAll
    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide());

        switch (Project.config.runIn()) {
            case "browser_selenoid":
            case "browser_local":
                WebUiDriver.configure();
                break;
            case "android_selenoid":
                Configuration.browser = SelenoidMobileDriver.class.getName();
                break;
            case "android_browserstack":
                Configuration.browser = BrowserstackMobileDriver.class.getName();
                break;
            case "android_emulator":
            case "android_real":
                Configuration.browser = LocalMobileDriver.class.getName();
                break;
        }
    }

    @BeforeEach
    @Step("Open the application(or browser)")
    public void beforeEach() {
        open();
    }

    @AfterEach
    @Step("Save artifacts and close webdriver")
    public void afterEach() {
        String sessionId = getSessionId();
        screenshotAs("Last screenshot");
        pageSource();

        switch (Project.config.runIn()) {
            case "android_browserstack":
                videoBrowserstack(sessionId);
                browserstackFullInfoLink(sessionId);
                break;
            case "android_selenoid":
                videoSelenoid(sessionId);
                break;
            case "browser_selenoid":
                videoSelenoid(sessionId);
            case "browser_local":
                //https://github.com/selenide/selenide/issues/1636
                //https://stackoverflow.com/questions/59192232/selenium-trying-to-get-firefox-console-logs-results-in-webdrivererror-http-me
                if (!Project.config.browser().equals("firefox")) {
                    browserConsoleLogs();
                }
                break;
        }
        closeWebDriver();
    }
}

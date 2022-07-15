package tests;

import com.codeborne.selenide.Configuration;
import drivers.BrowserstackMobileDriver;
import drivers.LocalMobileDriver;
import drivers.SelenoidMobileDriver;
import drivers.WebUiDriver;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static config.Project.config;
import static helpers.Attachments.*;

public class TestBase {

    @BeforeAll
    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide());

        switch (config.tool()) {
            case "ui_selenoid":
            case "ui_local":
                WebUiDriver.configure();
                break;
            case "mobile_selenoid":
                Configuration.browser = SelenoidMobileDriver.class.getName();
                break;
            case "mobile_browserstack":
                Configuration.browser = BrowserstackMobileDriver.class.getName();
                break;
            case "mobile_emulator":
            case "mobile_real":
                Configuration.browser = LocalMobileDriver.class.getName();
                break;
        }
        Configuration.browserSize = null;
    }

    @BeforeEach
    public void beforeEach() {
        open();
    }

    @AfterEach
    public void afterEach() {
        String sessionId = getSessionId();
        screenshotAs("Last screenshot");
        pageSource();

        switch (config.tool()) {
            case "mobile_browserstack":
                videoBrowserstack(sessionId);
                break;
            case "mobile_selenoid":
                videoSelenoid(sessionId);
                break;
            case "ui_remote":
                videoSelenoid(sessionId);
            case "ui_local":
                //https://github.com/selenide/selenide/issues/1636
                //https://stackoverflow.com/questions/59192232/selenium-trying-to-get-firefox-console-logs-results-in-webdrivererror-http-me
                if (!config.browser().equals("firefox")) {
                    browserConsoleLogs();
                }
                break;
        }
        closeWebDriver();
    }
}

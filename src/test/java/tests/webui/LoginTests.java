package tests.webui;

import com.github.kklisura.cdt.services.ChromeDevToolsService;
import helpers.Cdp;
import helpers.Location;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.RemoteWebDriver;
import tests.TestBase;
import tests.webui.pages.LoginPage;
import tests.webui.pages.MainPage;
import tests.webui.pages.SettingsPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static config.Project.config;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

@Tag("WebUI")
@Story("Login")
@Owner("vonoelv")
class LoginTests extends TestBase {
    public MainPage mainPage = new MainPage();

    @BeforeEach
    @Override
    public void beforeEach() throws Exception {
        open("about:blank");
        switch (config.tool()) {
            case "ui_selenoid":
                ChromeDevToolsService devtools = Cdp.from(format("ws://selenoid.autotests.cloud:4444/devtools/%s/page", ((RemoteWebDriver) getWebDriver()).getSessionId().toString()));
                devtools.getEmulation().setUserAgentOverride(requireNonNull(executeJavaScript("return navigator.userAgent;")),
                        "en-US,en", null, null);
                break;
            case "ui_local":
                Cdp.setUserAgentOverride("en-US,en");
                break;
        }
        open("");
        mainPage.acceptCookiesIfNeeded();
        //.switchToLanguage("English");
    }

    @Test
    @DisplayName("Successful login through UI")
    void checkLogin() {
        mainPage.login("jojiyik256@lenfly.com", "qwerty")
                .openInMainMenu("Settings");
        new SettingsPage().checkEmail("jojiyik256@lenfly.com");
    }

    @Test
    @DisplayName("Unsuccessful login through UI - incorrect password")
    void checkLoginWithWrongPassword() {
        //gogib54376@satedly.com // qwerty12345
        mainPage.login("jojiyik256@lenfly.com", "qwerty12345");
        new LoginPage().checkLoginFormError("Incorrect username or password");
    }
}

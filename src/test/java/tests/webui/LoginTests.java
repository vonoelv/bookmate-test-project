package tests.webui;

import config.App;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.webui.pages.LoginPage;
import tests.webui.pages.SettingsPage;

import static com.codeborne.selenide.Selenide.open;

@Tag("WebUI")
@Epic("WebUI")
@Feature("Login")
@Owner("vonoelv")
class LoginTests extends WebUiTestBase {

    public static final SettingsPage settingsPage = new SettingsPage();
    public static final LoginPage loginPage = new LoginPage();

    @BeforeEach
    @Override
    public void beforeEach() {
        open("");
        mainPage.acceptCookiesIfNeeded();
    }

    @Test
    @Story("Login by email")
    @DisplayName("Successful login through UI")
    void checkLogin() {
        mainPage
                .login(App.config.login(), App.config.password())
                .openInMainMenu("Settings");
        settingsPage.checkEmail(App.config.login());
    }

    @Test
    @Story("Login by email")
    @DisplayName("Unsuccessful login through UI - incorrect password")
    void checkLoginWithWrongPassword() {
        mainPage.login(App.config.login(), App.config.password() + "12345");
        loginPage.checkLoginFormError("Incorrect username or password");
    }
}

package tests.web;

import config.App;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.web.pages.LoginPage;
import tests.web.pages.SettingsPage;

import static com.codeborne.selenide.Selenide.open;

@Tag("Web")
@Epic("Web")
@Feature("Login")
@Owner("vonoelv")
class LoginTests extends WebTestBase {

    public static final SettingsPage settingsPage = new SettingsPage();
    public static final LoginPage loginPage = new LoginPage();

    @BeforeEach
    @Override
    public void beforeEach() {
        open("");
        mainPage.acceptCookies();
    }

    @Test
    @Story("Login by email")
    @DisplayName("Successful login")
    void checkLogin() {
        mainPage
                .login(App.config.login(), App.config.password())
                .openInMainMenu("Settings");
        settingsPage.checkEmail(App.config.login());
    }

    @Test
    @Story("Login by email")
    @DisplayName("Unsuccessful login - incorrect password")
    void checkLoginWithWrongPassword() {
        mainPage.login(App.config.login(), App.config.password() + "12345");
        loginPage.checkLoginFormError("Incorrect username or password");
    }
}

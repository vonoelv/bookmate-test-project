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
import tests.TestBase;
import tests.webui.pages.LoginPage;
import tests.webui.pages.MainPage;
import tests.webui.pages.SettingsPage;

import static com.codeborne.selenide.Selenide.open;

@Tag("WebUI")
@Epic("WebUI")
@Feature("Login")
@Owner("vonoelv")
class LoginTests extends TestBase {
    public MainPage mainPage = new MainPage();

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
        new SettingsPage().checkEmail(App.config.login());
    }

    @Test
    @Story("Login by email")
    @DisplayName("Unsuccessful login through UI - incorrect password")
    void checkLoginWithWrongPassword() {
        mainPage.login(App.config.login(), App.config.password() + "12345");
        new LoginPage().checkLoginFormError("Incorrect username or password");
    }
}

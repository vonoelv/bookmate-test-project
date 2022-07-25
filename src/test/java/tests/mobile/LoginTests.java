package tests.mobile;


import config.App;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Android")
@Epic("Android")
@Feature("Login")
@Owner("vonoelv")
class LoginTests extends AndroidTestBase {

    @Test
    @Story("Login by email")
    @DisplayName("Login is successful for a valid user")
    void checkLoginWithValidUser() {
        welcomePage
                .waitPageLoading()
                .pressAlreadyRegistered()
                .pressEmailLogin()
                .LoginWith(App.config.login(), App.config.password());
        myBooksPage.waitFullyLoaded();
        mainBarPage
                .openProfile()
                .checkLoginName(App.config.login().substring(0, App.config.login().indexOf("@")));
    }

    @Test
    @Story("Login by email")
    @DisplayName("Login is prohibited for in case of wrong password")
    void checkLoginWithWrongPassword() {
        welcomePage
                .waitPageLoading()
                .pressAlreadyRegistered()
                .pressEmailLogin()
                .LoginWith(App.config.login(), App.config.password() + "12345")
                .checkLoginErrorMessage("Incorrect username or password")
                .checkEmailLoginDialogOpened();
    }
}

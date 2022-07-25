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
    void checkLoginAndAdded() {
        welcomePage
                .waitPageLoading()
                .pressAlreadyRegistered()
                .pressEmailLogin()
                .LoginWith(App.config.login(), App.config.login());
        mainBarPage
                .openProfile()
                .checkLoginName(App.config.login().substring(0, App.config.login().indexOf("@")));
    }
}

package tests.mobile;


import io.appium.java_client.AppiumBy;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;
import tests.mobile.pages.prelogin.WelcomePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

@Tag("Mobile")
@Feature("Login")
@Owner("vonoelv")
class LoginTests extends TestBase {

    @Test
    @Disabled("Just draft")
    @DisplayName("Draft test: Login and check Library")
    void checkLibrary() {
        $(AppiumBy.id("com.bookmate:id/text_view_already_registered")).click();
        $(AppiumBy.id("com.bookmate:id/text_view_email")).click();
        $(AppiumBy.id("com.bookmate:id/input_credentials")).sendKeys("jojiyik256@lenfly.com");
        $(AppiumBy.id("com.bookmate:id/input_password")).sendKeys("qwerty");
        $(AppiumBy.id("com.bookmate:id/loading_button")).click();
        $(AppiumBy.id("com.bookmate:id/showcase")).click();
    }

    @Test
    @DisplayName("Login is successful for a valid user")
    void checkLoginAndAdded() {
        new WelcomePage()
                .pressAlreadyRegistered()
                .pressEmailLogin()
                .LoginWith("jojiyik256@lenfly.com", "qwerty")
                .openProfile()
                .checkLoginName("jojiyik256");
    }
}

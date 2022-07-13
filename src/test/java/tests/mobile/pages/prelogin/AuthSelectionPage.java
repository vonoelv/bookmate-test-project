package tests.mobile.pages.prelogin;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static java.util.Objects.requireNonNullElseGet;

public class AuthSelectionPage {
    private final SelenideElement emailLoginButton = $(AppiumBy.id("com.bookmate:id/text_view_email"));
    public static EmailLoginPage emailLoginPage = new EmailLoginPage();

    @Step("Press login with email")
    public EmailLoginPage pressEmailLogin() {
        emailLoginButton.click();
        return getEmailLoginPage();
    }

    private EmailLoginPage getEmailLoginPage() {
        return requireNonNullElseGet(emailLoginPage, EmailLoginPage::new);
    }
}

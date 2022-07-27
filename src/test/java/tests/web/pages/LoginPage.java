package tests.web.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final SelenideElement emailOption = $(".auth__subaction").$$(".auth__link").first();
    private final SelenideElement emailField = $(".input[name=email]");
    private final SelenideElement passwordField = $(".input[name=password]");
    private final SelenideElement submitButton = $(".button_submit");
    private final SelenideElement loginFormError = $(".login-form__error");
    private final SelenideElement userPictureInLoginField = $(".credential-input__userpic");

    @Step("Logging with email:{email} and password:{password}")
    public LoginPage login(String email, String password) {
        emailOption.shouldBe(visible).click();
        emailField.setValue(email);
        passwordField.click();
        userPictureInLoginField.shouldBe(visible);
        passwordField.setValue(password);
        submitButton.shouldNotBe(disabled).click();
        return this;
    }

    @Step("Verify login form error")
    public LoginPage checkLoginFormError(String expectedErrMessage) {
        loginFormError.shouldBe(visible).shouldHave(text(expectedErrMessage));
        return this;
    }

    @Step("Login page should be visible")
    public LoginPage checkLoginPageIsVisible() {
        emailOption.shouldBe(visible);
        return this;
    }

}

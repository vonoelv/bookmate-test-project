package tests.mobile.pages.prelogin;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import tests.mobile.pages.main.MyBooksPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static java.util.Objects.requireNonNullElseGet;

public class EmailLoginPage {

    private final SelenideElement emailField = $(AppiumBy.id("com.bookmate:id/input_credentials"));
    private final SelenideElement passwordFiled = $(AppiumBy.id("com.bookmate:id/input_password"));
    private final SelenideElement loginButton = $(AppiumBy.id("com.bookmate:id/loading_button"));
    private MyBooksPage myBooksPage;

    @Step("Enter email")
    public EmailLoginPage setEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

    @Step("Enter password")
    public EmailLoginPage setPassword(String password) {
        passwordFiled.sendKeys(password);
        return this;
    }

    @Step("Press login button")
    public EmailLoginPage pressLogin() {
        loginButton.click();
        return this;
    }

    @Step("Login with {email}/{password}")
    public MyBooksPage LoginWith(String email, String password) {
        setEmail(email);
        setPassword(password);
        pressLogin();
        $(AppiumBy.id("com.bookmate:id/my_books")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        return getMyBooksPage();
    }

    private MyBooksPage getMyBooksPage() {
        return requireNonNullElseGet(myBooksPage, MyBooksPage::new);
    }
}

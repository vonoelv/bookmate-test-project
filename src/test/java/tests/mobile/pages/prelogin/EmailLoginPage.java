package tests.mobile.pages.prelogin;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class EmailLoginPage {

    public static final SelenideElement errorMessage = $(By.xpath("//android.widget.Toast"));
    public static final SelenideElement loginTitle = $(AppiumBy.id("com.bookmate:id/text_view_title"));
    private final SelenideElement emailField = $(AppiumBy.id("com.bookmate:id/input_credentials"));
    private final SelenideElement passwordFiled = $(AppiumBy.id("com.bookmate:id/input_password"));
    private final SelenideElement loginButton = $(AppiumBy.id("com.bookmate:id/loading_button"));

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
    public EmailLoginPage LoginWith(String email, String password) {
        setEmail(email);
        setPassword(password);
        pressLogin();
        return this;
    }

    @Step("Verify error message")
    public EmailLoginPage checkLoginErrorMessage(String errorText) {
        String actualText = new WebDriverWait(getWebDriver(), Duration.ofSeconds(1))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Toast")))
                .getText();
        assertThat(actualText).isEqualTo(errorText);

        //This one does not work:
        //errorMessage.shouldHave(text(errorText));
        //Element should have text "Incorrect username or password" {By.xpath: //android.widget.Toast}
        //Element: 'Ups, failed to described the element [caused by: StaleElementReferenceException: androidx.test.uiautomator.StaleObjectException]'
        return this;
    }

    @Step("Verify 'Log in(email)' dialog is opened")
    public void checkEmailLoginDialogOpened() {
        loginTitle.shouldHave(text("Log in"));
    }

}

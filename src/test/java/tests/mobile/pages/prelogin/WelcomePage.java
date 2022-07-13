package tests.mobile.pages.prelogin;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;
import static java.util.Objects.requireNonNullElseGet;

public class WelcomePage {
    private final SelenideElement continueButton = $(AppiumBy.id("com.bookmate:id/button_main_content_layout"));
    private final SelenideElement alreadyRegisteredButton = $(AppiumBy.id("com.bookmate:id/text_view_already_registered"));
    public static AuthSelectionPage authSelectionPage = new AuthSelectionPage();

    public WelcomePage() {
        step("Wait until app is loaded",
                () -> alreadyRegisteredButton.shouldBe(visible, Duration.ofSeconds(10)));
    }

    @Step("Press continue")
    public WelcomePage pressContinue() {
        continueButton.click();
        return this;
    }

    @Step("Press already registered")
    public AuthSelectionPage pressAlreadyRegistered() {
        alreadyRegisteredButton.click();
        return authSelectionPage();
    }

    private AuthSelectionPage authSelectionPage() {
        return requireNonNullElseGet(authSelectionPage, AuthSelectionPage::new);
    }
}

package tests.mobile.pages.prelogin;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class WelcomePage {
    private final SelenideElement closeButton = $(AppiumBy.id("com.bookmate:id/close_btn"));
    private final SelenideElement continueButton = $(AppiumBy.id("com.bookmate:id/button_main_content_layout"));
    private final SelenideElement alreadyRegisteredButton = $(AppiumBy.id("com.bookmate:id/text_view_already_registered"));
    private static final AuthSelectionPage authSelectionPage = new AuthSelectionPage();
    private static final TopicsSelectionPage topicsSelectionPage = new TopicsSelectionPage();

    @Step("Wait until app is loaded")
    public WelcomePage waitPageLoading() {
        alreadyRegisteredButton.shouldBe(visible, Duration.ofSeconds(15));
        return this;
    }

    @Step("Press continue")
    public WelcomePage pressContinue() {
        continueButton.click();
        return this;
    }

    @Step("Press already registered")
    public AuthSelectionPage pressAlreadyRegistered() {
        alreadyRegisteredButton.click();
        return authSelectionPage;
    }

    @Step("Press close button")
    public TopicsSelectionPage pressCloseButton() {
        closeButton.click();
        return topicsSelectionPage;
    }
}

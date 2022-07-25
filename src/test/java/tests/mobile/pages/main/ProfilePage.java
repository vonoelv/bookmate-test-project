package tests.mobile.pages.main;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {

    private final SelenideElement loginName = $(AppiumBy.id("com.bookmate:id/text_view_login"));
    private final SelenideElement registerButtonTitle = $(AppiumBy.id("com.bookmate:id/text_view_button_title"));
    private final SelenideElement registerButton = $(AppiumBy.id("com.bookmate:id/register_button"));

    private final SelenideElement banner = $(AppiumBy.id("com.bookmate:id/textView"));

    @Step("Check login name is {expectedLoginName}")
    public void checkLoginName(String expectedLoginName) {
        loginName.shouldHave(text("@" + expectedLoginName));
    }

    @Step("Verify Sign up invitation banner with sign in button is shown")
    public void checkSignUpInvitationShown(String bannerText, String buttonText) {
        banner.shouldHave(text(bannerText));
        registerButton.shouldBe(visible);
        registerButtonTitle.shouldHave(text(buttonText));
    }
}

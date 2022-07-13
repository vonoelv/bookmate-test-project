package tests.mobile.pages.main;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {

    private final SelenideElement loginName = $(AppiumBy.id("com.bookmate:id/text_view_login"));

    @Step("Check login name is {expectedLoginName}")
    public void checkLoginName(String expectedLoginName) {
        loginName.shouldHave(text("@" + expectedLoginName));
    }
}

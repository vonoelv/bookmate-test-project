package tests.mobile.pages.main;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ActivityPage {

    public static final SelenideElement pageTitle = $(AppiumBy.id("com.bookmate:id/feed_title"));

    @Step("Verify 'Activity' is opened")
    public void checkIsOpened() {
        pageTitle.shouldHave(text("Activity"));
    }
}

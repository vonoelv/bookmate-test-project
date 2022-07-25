package tests.mobile.pages.main;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class MyBooksPage {
    public static final SelenideElement pageTitle = $(AppiumBy.id("com.bookmate:id/title"));
    public static final SelenideElement mainPageContent = $(AppiumBy.id("com.bookmate:id/my_books"));
    private final SelenideElement added = $(AppiumBy.xpath("//android.widget.TextView[@text='Added']/.."));
    private final SelenideElement series = $(AppiumBy.xpath("//android.widget.TextView[@text='Series']/.."));
    private final SelenideElement impressions = $(AppiumBy.xpath("//android.widget.TextView[@text='Impressions']/.."));
    private final SelenideElement quotes = $(AppiumBy.xpath("//android.widget.TextView[@text='Quotes']/.."));
    private final SelenideElement banner = $(AppiumBy.id("com.bookmate:id/myBooksBannerTextView"));
    private final SelenideElement bannerButton = $(AppiumBy.id("com.bookmate:id/myBooksBannerButton"));

    @Step("Press Added")
    public MyBooksPage pressAdded() {
        added.click();
        return this;
    }

    @Step("Press Series")
    public MyBooksPage pressSeries() {
        series.click();
        return this;
    }

    @Step("Press Impressions")
    public MyBooksPage pressImpressions() {
        impressions.click();
        return this;
    }

    @Step("Press Quotes")
    public MyBooksPage pressQuotes() {
        quotes.click();
        return this;
    }

    @Step("Verify Sign up invitation banner with sign in button is shown")
    public void checkSignUpInvitationShown(String bannerText, String buttonText) {
        banner.shouldHave(text(bannerText));
        bannerButton.shouldHave(text(buttonText));
    }

    @Step("Verify 'My books' is opened")
    public void checkIsOpened() {
        pageTitle.shouldHave(text("My books"));
    }

    @Step("Wait until 'My books' is fully loaded")
    public void waitFullyLoaded() {
        mainPageContent.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}

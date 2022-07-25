package tests.mobile.pages.main;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class MyBooksPage {

    private final SelenideElement added = $(AppiumBy.xpath("//android.widget.TextView[@text='Added']/.."));
    private final SelenideElement series = $(AppiumBy.xpath("//android.widget.TextView[@text='Series']/.."));
    private final SelenideElement impressions = $(AppiumBy.xpath("//android.widget.TextView[@text='Impressions']/.."));
    private final SelenideElement quotes = $(AppiumBy.xpath("//android.widget.TextView[@text='Quotes']/.."));

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
}

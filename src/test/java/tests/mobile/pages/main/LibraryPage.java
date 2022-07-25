package tests.mobile.pages.main;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LibraryPage {

    public static final SelenideElement pageTitle = $(AppiumBy.id("com.bookmate:id/showcase_title"));
    private final SelenideElement search = $(AppiumBy.id("com.bookmate:id/start_search"));
    private final SearchPage searchPage = new SearchPage();

    @Step("Press Search")
    public SearchPage pressSearch() {
        search.click();
        return searchPage;
    }

    @Step("Verify 'Library' is opened")
    public void checkIsOpened() {
        pageTitle.shouldHave(text("Library"));
    }
}

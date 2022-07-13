package tests.mobile.pages.main;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class LibraryPage {

    private final SelenideElement search = $(AppiumBy.id("com.bookmate:id/start_search"));
    private final SelenideElement inputSearch = $(AppiumBy.id("com.bookmate:id/input_search"));
    private final ElementsCollection resultTitles =
            $$(AppiumBy.xpath("//android.widget.TextView[@resource-id='com.bookmate:id/item_title']"));

    @Step("Press Search")
    public LibraryPage pressSearch() {
        search.click();
        return this;
    }

    @Step("Perform search: '{searchText}'")
    public LibraryPage performSearch(String searchText) {
        pressSearch();
        inputSearch.sendKeys(searchText);
        return this;
    }

    @Step("Verify title '{title}' is shown in the results list")
    public LibraryPage checkTitleIsInResults(String title) {
        resultTitles.shouldHave(itemWithText(title));
        return this;
    }
}

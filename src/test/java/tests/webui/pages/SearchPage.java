package tests.webui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class SearchPage {

    public static final SelenideElement searchInput = $("#search-input");
    public static final SelenideElement searchResults = $(".list__body");
    public static final SelenideElement searchFiltersBox = $(".search-filters__inner");

    @Step("Perform search for: {searchText}")
    public SearchPage performSearch(String searchText) {
        searchInput.click();
        searchInput.setValue(searchText);
        return this;
    }

    @Step("Check item {expectedText} exists in the search results")
    public SearchPage checkItemIsInResults(String itemType, String expectedText) {
        searchResults.$(withText(expectedText)).shouldBe(visible);
        return this;
    }

    @Step("Select filter {itemType} for search results")
    public SearchPage selectFilter(String itemType) {
        searchFiltersBox.$(byText(itemType)).click();
        return this;
    }
}

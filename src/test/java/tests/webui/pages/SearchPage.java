package tests.webui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class SearchPage {

    public static final SelenideElement searchInput = $("#search-input");

    @Step("Perform search for: {searchText}")
    public SearchPage performSearch(String searchText) {
        searchInput.click();
        searchInput.setValue(searchText);
        return this;
    }

    @Step("Check item {expectedText} exists in the search results")
    public SearchPage checkItemIsInResults(String itemType, String expectedText) {
        //TODO: extract element
        $(".list__body").$(withText(expectedText)).shouldBe(visible);
        return this;
    }

    @Step("Select filter {itemType} for search results")
    public SearchPage selectFilter(String itemType) {
        $(".search-filters__inner").$(byText(itemType)).click();
        return this;
    }
}

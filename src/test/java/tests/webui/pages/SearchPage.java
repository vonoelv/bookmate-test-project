package tests.webui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SearchPage {

    public static final SelenideElement searchInput = $("#search-input");

    @Step("Perform search for: {searchText}")
    public SearchPage performSearch(String searchText) {
        searchInput.click();
        searchInput.setValue(searchText);
        return this;
    }

    @Step("Check title {expectedTitle} exists in the search results")
    public void checkTitleIsInResults(String expectedTitle) {
        //TODO: extract element
        //RECHECK THIS
        $(".book").$(".book__title").shouldHave(text(expectedTitle));
    }
}

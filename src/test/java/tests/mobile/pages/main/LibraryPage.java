package tests.mobile.pages.main;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class LibraryPage {

    private final SelenideElement search = $(AppiumBy.id("com.bookmate:id/start_search"));
    private final SelenideElement inputSearch = $(AppiumBy.id("com.bookmate:id/input_search"));
    private final ElementsCollection resultTitles =
            $$(AppiumBy.xpath("//android.widget.TextView[@resource-id='com.bookmate:id/item_title']"));
    private final SearchPage searchPage = new SearchPage();

    @Step("Press Search")
    public SearchPage pressSearch() {
        search.click();
        return searchPage;
    }
}

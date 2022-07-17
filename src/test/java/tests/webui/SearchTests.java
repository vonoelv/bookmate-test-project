package tests.webui;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;
import tests.webui.pages.MainPage;
import tests.webui.pages.SearchPage;

import static com.codeborne.selenide.Selenide.open;

@Tag("WebUI")
@Epic("WebUI")
@Feature("Search")
@Owner("vonoelv")
class SearchTests extends TestBase {

    public SearchPage searchPage = new SearchPage();
    public MainPage mainPage = new MainPage();

    @BeforeEach
    @Override
    @Step("Open https://bookmate.com/search")
    public void beforeEach() {
        open("/search");
        mainPage.acceptCookiesIfNeeded();
    }

    @Test
    @DisplayName("An existing book can be found")
    void searchTest() {
        searchPage
                .performSearch("Rikki-Tikki-Tavi")
                .checkTitleIsInResults("Rikki-Tikki-Tavi");
    }
}

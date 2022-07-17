package tests.webui;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;
import tests.webui.pages.MainPage;
import tests.webui.pages.SearchPage;

import static com.codeborne.selenide.Selenide.open;

@Tag("WebUI")
@Feature("Search")
@Owner("vonoelv")
class SearchTests extends TestBase {

    public SearchPage searchPage = new SearchPage();
    public MainPage mainPage = new MainPage();

    @BeforeEach
    @Override
    public void beforeEach() {
        open("");
        mainPage.acceptCookiesIfNeeded();
    }

    @Test
    @DisplayName("An existing book can be found")
    void searchTest() {
        open("/search");
        searchPage
                .performSearch("Rikki-Tikki-Tavi")
                .checkTitleIsInResults("Rikki-Tikki-Tavi");
    }
}

package tests.webui;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

    @CsvSource(value = {
            "Books | Rikki-Tikki-Tavi",
            "Audiobooks | Side Effects",
            "Comics | The Story of Verona",
            "Series | A Riley Paige Mystery",
            "Authors | E. M Remarque",
            "Bookshelves | 24 Free Vintage Sci-Fi"
    }, delimiter = '|')

    @ParameterizedTest(name = "{arguments}")
    @DisplayName("An existing item can be found with filter:")
    void searchTestWithFilter(String itemType, String itemName) {
        searchPage
                .performSearch(itemName)
                .selectFilter(itemType)
                .checkItemIsInResults(itemType, itemName);
    }
}

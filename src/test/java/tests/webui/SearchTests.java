package tests.webui;

import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Selenide.open;

@Tag("WebUI")
@Epic("WebUI")
@Feature("Search")
@Owner("vonoelv")
class SearchTests extends WebUiTestBase {

    @BeforeEach
    @Override
    @Step("Open {Project.config.baseUrl()}/search")
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
    void checkSearchWithFilter(String itemType, String itemName) {
        Allure.getLifecycle().updateTestCase(test ->
                test.setName("An existing item can be found with filter: [Filter, Search text]"));

        searchPage
                .performSearch(itemName)
                .selectFilter(itemType)
                .checkItemIsInResults(itemType, itemName);
    }
}

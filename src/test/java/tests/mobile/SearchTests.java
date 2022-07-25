package tests.mobile;

import config.App;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@Tag("Android")
@Epic("Android")
@Feature("Search")
@Owner("vonoelv")
class SearchTests extends AndroidTestBase {

    @BeforeEach
    @Override
    @Step("Open the app, login and open Library page")
    public void beforeEach() {
        super.beforeEach();
        welcomePage
                .pressCloseButton()
                .selectTopic("Personal Growth")
                .selectTopic("Science Fiction")
                .pressContinueButton()
                .selectBook(0)
                .selectBook(1)
                .pressContinueButton();
        myBooksPage.waitFullyLoaded();
        mainBarPage.openLibrary();
    }

    @CsvSource(value = {
            "Books | Rikki-Tikki-Tavi",
            "Audiobooks | Side Effects",
            "Comics | The Story of Verona"
    }, delimiter = '|')

    @ParameterizedTest(name = "{arguments}")
    @DisplayName("An existing item can be found with filter:")
    void checkSearchWithFilter(String itemType, String itemName) {
        Allure.getLifecycle().updateTestCase(test ->
                test.setName("An existing item can be found with filter: [Filter, Search text]"));

        libraryPage
                .pressSearch()
                .enterSearchText(itemName)
                .selectFilter(itemType)
                .checkTextIsInResults(itemName);
    }
}

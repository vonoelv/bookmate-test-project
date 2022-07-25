package tests.mobile;

import config.App;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
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
                .waitPageLoading()
                .pressAlreadyRegistered()
                .pressEmailLogin()
                .LoginWith(App.config.login(), App.config.password());
        mainBarPage.openLibrary();
    }

    @CsvSource(value = {
            "Books | Rikki-Tikki-Tavi",
            "Audiobooks | Side Effects",
            "Comics | The Story of Verona",
            "Series | A Riley Paige Mystery",
            "Authors | E. M Remarque",
            "Shelves | 24 Free Vintage Sci-Fi"
    }, delimiter = '|')

    @ParameterizedTest(name = "{arguments}")
    @DisplayName("An existing book can be found")
    void checkSearchForBook(String itemType, String itemName) {
        libraryPage
                .pressSearch()
                .enterSearchText(itemName)
                .selectFilter(itemType)
                .checkTextIsInResults(itemName);
    }
}

package tests.mobile;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.mobile.pages.main.LibraryPage;

@Tag("Android")
@Epic("Android")
@Feature("Search")
@Owner("vonoelv")
class SearchTests extends MainTests {

    @BeforeEach
    @Override
    @Step("Open the app, login and open Library page")
    public void beforeEach() {
        super.beforeEach();
        loginWithAnyAvailableUser().openLibrary();
    }

    @Test
    @DisplayName("An existing book can be found")
    void checkSearchForBook() {
        new LibraryPage()
                .performSearch("Rikki-Tikki-Tavi")
                .checkTitleIsInResults("Rikki-Tikki-Tavi");
    }
}

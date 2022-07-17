package tests.mobile;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Android")
@Epic("Android")
@Feature("Search")
@Owner("vonoelv")
class SearchTests extends MainTests {

    @Test
    @DisplayName("An existing book can be found")
    void checkSearchForBook() {
        loginWithAnyAvailableUser()
                .openLibrary()
                .performSearch("Rikki-Tikki-Tavi")
                .checkTitleIsInResults("Rikki-Tikki-Tavi");
    }
}

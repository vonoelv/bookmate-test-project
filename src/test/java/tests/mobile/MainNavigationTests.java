package tests.mobile;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Android")
@Epic("Android")
@Feature("Main navigation through the app")
@Owner("vonoelv")
class MainNavigationTests extends AndroidTestBase {

    @BeforeEach
    @Override
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
    }

    @Test
    @Story("Main bar tabs")
    @DisplayName("Ability to open 'My books'")
    void checkMyBooksOpening() {
        mainBarPage.openMyBooks();
        myBooksPage.checkIsOpened();
    }

    @Test
    @Story("Main bar tabs")
    @DisplayName("Ability to open 'Library'")
    void checkLibraryOpening() {
        mainBarPage.openLibrary();
        libraryPage.checkIsOpened();
    }

    @Test
    @Story("Main bar tabs")
    @DisplayName("Ability to open 'Activity'")
    void checkActivityOpening() {
        mainBarPage.openActivity();
        activityPage.checkIsOpened();
    }
}

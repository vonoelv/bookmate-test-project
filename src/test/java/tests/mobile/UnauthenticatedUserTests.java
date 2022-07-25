package tests.mobile;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Android")
@Epic("Android")
@Feature("Unauthenticated user capabilities")
@Owner("vonoelv")
class UnauthenticatedUserTests extends AndroidTestBase {

    @Test
    @Story("Open the app without logging in")
    @DisplayName("Ability to open Library without logging in")
    void checkTopicsSelection() {
        welcomePage
                .pressCloseButton()
                .selectTopic("Personal Growth")
                .selectTopic("Science Fiction")
                .pressContinueButton()
                .selectBook(0)
                .selectBook(1)
                .pressContinueButton();
        libraryPage.checkIsOpened();
    }

    @Test
    @Story("Unauthenticated user banners")
    @DisplayName("Sign up banner in My books for an unauthenticated user")
    void checkMyBooksSignUpInvitationForUnauthorized() {
        welcomePage
                .pressCloseButton()
                .selectTopic("Personal Growth")
                .selectTopic("Science Fiction")
                .pressContinueButton()
                .selectBook(0)
                .selectBook(1)
                .pressContinueButton();
        mainBarPage.openMyBooks();
        myBooksPage.checkSignUpInvitationShown(
                "Sign up to read from different devices and keep your reading progress!",
                "Sign in or Register");
    }

    @Test
    @Story("Unauthenticated user banners")
    @DisplayName("Sign up banner in Profile for an unauthenticated user")
    void checkProfileSignUpInvitationForUnauthorized() {
        welcomePage
                .pressCloseButton()
                .selectTopic("Personal Growth")
                .selectTopic("Science Fiction")
                .pressContinueButton()
                .selectBook(0)
                .selectBook(1)
                .pressContinueButton();
        mainBarPage.openProfile();
        profilePage.checkSignUpInvitationShown(
                "Sign up to gain access to the library from all your devices",
                "Sign in or Register");
    }
}

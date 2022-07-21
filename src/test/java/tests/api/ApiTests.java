package tests.api;

import config.App;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.api.models.book.Book;
import tests.api.models.bookshelfs.Bookshelf;
import tests.api.steps.ApiSteps;


@Tag("API")
@Epic("API")
@Owner("vonoelv")
class ApiTests {
    ApiSteps apiSteps = new ApiSteps();

    //SHOULD be generated:
    // TEST_BOOKSHELF_<random_string>
    // TEST_BOOKSHELF_ANNOTATION_<random_string>
    String NEW_BOOKSHELF_NAME = "One more bookshelf99";
    String NEW_BOOKSHELF_ANNOTATION = "Annotation for bookshelf99";

    @Test
    @DisplayName("Ability to add a book to the library")
    void checkAddingBookToLibrary() {
        apiSteps.ensureBookIsNotInLibrary(App.config.book1Uuid());
        Book addedBook = apiSteps.addBookToLibrary(App.config.book1Uuid());
        apiSteps.checkAddedBookResponseParameters(addedBook, App.config.book1Uuid());
        apiSteps.checkBookIsInLibrary(addedBook.getUuid());
    }

    @Test
    @DisplayName("Ability to remove a book from the library")
    void checkRemovingBookFromLibrary() {
        String libraryCardUuid = apiSteps.ensureBookIsInLibrary(App.config.book2Uuid());
        apiSteps.removeBookFromLibrary(libraryCardUuid);
        apiSteps.checkBookIsNotInLibrary(App.config.book2Uuid());
    }

    @Test
    @DisplayName("Ability to create new bookshelf")
    void checkCreatingNewBookshelf() {
        Bookshelf bookshelf = apiSteps.createNewBookshelf(NEW_BOOKSHELF_NAME, NEW_BOOKSHELF_ANNOTATION);
        apiSteps.checkNewBookshelfResponseParameters(bookshelf, NEW_BOOKSHELF_NAME, NEW_BOOKSHELF_ANNOTATION);
        apiSteps.checkBookshelfExists(bookshelf);
    }

    @Test
    @DisplayName("Ability to delete a bookshelf")
    void checkDeletingABookshelf() {
        Bookshelf bookshelf = apiSteps.createNewBookshelf(NEW_BOOKSHELF_NAME, NEW_BOOKSHELF_ANNOTATION);
        apiSteps.deleteBookshelf(bookshelf.getUuid());
        apiSteps.checkBookshelfDoesNotExist(bookshelf);
    }
}

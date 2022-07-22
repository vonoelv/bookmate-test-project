package tests.api;

import config.App;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.api.models.bookshelves.post.Bookshelf;
import tests.api.models.bookshelves.posts.BookshelfPost;
import tests.api.models.profile.library_cards.post.Book;
import tests.api.steps.ApiSteps;

import static helpers.RandomUtils.*;


@Tag("API")
@Epic("API")
@Owner("vonoelv")
class ApiTests {
    ApiSteps apiSteps = new ApiSteps();

    @AfterAll
    static void cleanUp() {
        ApiSteps apiSteps = new ApiSteps();
        apiSteps.getAllBookshelves().forEach((bookshelf) -> apiSteps.deleteBookshelf(bookshelf.getUuid()));
        apiSteps.getAllBooks().forEach((book) -> apiSteps.removeBookFromLibrary(book.getUsersLibraryCard().getUuid()));
    }

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
        String bookshelfName = getRandomBookshelfName();
        String bookshelfAnnotation = getRandomBookshelfAnnotation();
        Bookshelf createdBookshelf = apiSteps.createNewBookshelf(bookshelfName, bookshelfAnnotation);
        apiSteps.checkNewBookshelfResponseParameters(createdBookshelf, bookshelfName, bookshelfAnnotation);
        apiSteps.checkBookshelfExists(createdBookshelf);
    }

    @Test
    @DisplayName("Ability to delete a bookshelf")
    void checkDeletingABookshelf() {
        Bookshelf bookshelf = apiSteps.createNewBookshelf(getRandomBookshelfName(), getRandomBookshelfAnnotation());
        apiSteps.deleteBookshelf(bookshelf.getUuid());
        apiSteps.checkBookshelfDoesNotExist(bookshelf);
    }

    @Test
    @DisplayName("Ability to add a book to a bookshelf")
    void checkAddingBookToBookshelf() {
        Bookshelf bookshelf = apiSteps.createNewBookshelf(getRandomBookshelfName(), getRandomBookshelfAnnotation());
        String annotation = getRandomBookshelfBookAnnotation();
        BookshelfPost bookshelfPost = apiSteps.addBookToBookshelf(App.config.book1Uuid(), bookshelf.getUuid(), annotation);
        apiSteps.checkAddBookOnBookshelfResponseParameters(
                bookshelfPost, annotation, App.config.book1Uuid(), bookshelf.getUuid());
        apiSteps.checkBookIsOnBookshelf(App.config.book1Uuid(), bookshelf.getUuid());
    }
}

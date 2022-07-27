package tests.api;

import config.App;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.api.models.profile.library_cards.post.Book;
import tests.api.pages.BooksPage;

@Tag("API")
@Epic("API")
@Owner("vonoelv")
class BooksTests {

    BooksPage booksPage = new BooksPage();

    @AfterAll
    static void cleanUp() {
        BooksPage booksPage = new BooksPage();
        booksPage.getAllBooks().forEach((book) ->
                booksPage.removeBookFromLibrary(book.getUsersLibraryCard().getUuid()));
    }

    @Test
    @DisplayName("Ability to add a book to the library")
    void checkAddingBookToLibrary() {
        booksPage.ensureBookIsNotInLibrary(App.config.book1Uuid());
        Book addedBook = booksPage.addBookToLibrary(App.config.book1Uuid());
        booksPage.checkAddedBookResponseParameters(addedBook, App.config.book1Uuid());
        booksPage.checkBookIsInLibrary(addedBook.getUuid());
    }

    @Test
    @DisplayName("Ability to remove a book from the library")
    void checkRemovingBookFromLibrary() {
        String libraryCardUuid = booksPage.ensureBookIsInLibrary(App.config.book2Uuid());
        booksPage.removeBookFromLibrary(libraryCardUuid);
        booksPage.checkBookIsNotInLibrary(App.config.book2Uuid());
    }
}

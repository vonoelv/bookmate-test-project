package tests.api;

import config.App;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.api.models.bookshelves.post_put.Bookshelf;
import tests.api.models.bookshelves.posts.BookshelfPost;
import tests.api.pages.BookshelvesPage;

import static helpers.RandomUtils.*;


@Tag("API")
@Epic("API")
@Owner("vonoelv")
class BookshelvesTests {
    BookshelvesPage bookshelvesPage = new BookshelvesPage();

    @AfterAll
    static void cleanUp() {
        BookshelvesPage bookshelvesPage = new BookshelvesPage();
        bookshelvesPage.getAllBookshelves().forEach((bookshelf) ->
                bookshelvesPage.deleteBookshelf(bookshelf.getUuid()));
    }

    @Test
    @DisplayName("Ability to create new bookshelf")
    void checkCreatingNewBookshelf() {
        String bookshelfName = getRandomBookshelfName();
        String bookshelfAnnotation = getRandomBookshelfAnnotation();
        Bookshelf createdBookshelf = bookshelvesPage.createNewBookshelf(bookshelfName, bookshelfAnnotation);
        bookshelvesPage.checkBookshelfResponseParameters(createdBookshelf, bookshelfName, bookshelfAnnotation);
        bookshelvesPage.checkBookshelfExists(createdBookshelf);
    }

    @Test
    @DisplayName("Ability to delete a bookshelf")
    void checkDeletingABookshelf() {
        Bookshelf bookshelf = bookshelvesPage.createNewBookshelf(getRandomBookshelfName(), getRandomBookshelfAnnotation());
        bookshelvesPage.deleteBookshelf(bookshelf.getUuid());
        bookshelvesPage.checkBookshelfDoesNotExist(bookshelf);
    }

    @Test
    @DisplayName("Ability to add a book to a bookshelf")
    void checkAddingBookToBookshelf() {
        Bookshelf bookshelf = bookshelvesPage.createNewBookshelf(getRandomBookshelfName(), getRandomBookshelfAnnotation());
        String annotation = getRandomBookshelfBookAnnotation();
        BookshelfPost bookshelfPost = bookshelvesPage.addBookToBookshelf(App.config.book1Uuid(), bookshelf.getUuid(), annotation);
        bookshelvesPage.checkAddBookOnBookshelfResponseParameters(
                bookshelfPost, annotation, App.config.book1Uuid(), bookshelf.getUuid());
        bookshelvesPage.checkBookIsOnBookshelf(App.config.book1Uuid(), bookshelf.getUuid());
    }

    @Test
    @DisplayName("Ability to remove a book from a bookshelf")
    void checkRemoveBookFromBookshelf() {
        Bookshelf bookshelf = bookshelvesPage.createNewBookshelf(getRandomBookshelfName(), getRandomBookshelfAnnotation());
        String annotation = getRandomBookshelfBookAnnotation();
        BookshelfPost bookshelfPost = bookshelvesPage.addBookToBookshelf(App.config.book1Uuid(), bookshelf.getUuid(), annotation);
        bookshelvesPage.removeBookFromBookshelf(bookshelfPost);
        bookshelvesPage.checkBookIsNotOnBookshelf(App.config.book1Uuid(), bookshelf.getUuid());
    }

    @Test
    @DisplayName("Ability to get bookshelf data")
    void checkBookshelfDataGetting() {
        Bookshelf bookshelf = bookshelvesPage.createNewBookshelf(getRandomBookshelfName(), getRandomBookshelfAnnotation());
        Bookshelf bookshelfFromResponse = bookshelvesPage.getBookshelfByUuid(bookshelf.getUuid());
        bookshelvesPage.checkBookshelfParameters(bookshelfFromResponse, bookshelf.getAnnotation(), bookshelf.getUuid(), bookshelf.getTitle());
    }

    @Test
    @DisplayName("Ability to edit bookshelf")
    void checkBookshelfEditing() {
        Bookshelf initialBookshelf = bookshelvesPage.createNewBookshelf(getRandomBookshelfName(), getRandomBookshelfAnnotation());

        String bookshelfName = getRandomBookshelfName();
        String bookshelfAnnotation = getRandomBookshelfAnnotation();
        Bookshelf editedBookshelf = bookshelvesPage.editBookshelf(initialBookshelf.getUuid(), bookshelfName, bookshelfAnnotation);

        bookshelvesPage.checkBookshelfResponseParameters(editedBookshelf, bookshelfName, bookshelfAnnotation);
        bookshelvesPage.checkBookshelfDataByUuid(initialBookshelf.getUuid(), bookshelfName, bookshelfAnnotation);
    }
}

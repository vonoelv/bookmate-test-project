package tests.api.steps;

import helpers.CustomAllureListener;
import io.qameta.allure.Step;
import tests.api.models.bookshelfs.Bookshelf;
import tests.api.models.bookshelfs.NewBookshelfResponse;
import tests.api.models.profile.AddedBook;
import tests.api.models.profile.BookshelvesResponse;
import tests.api.models.profile.UsersBook;
import tests.api.models.profile.UsersBooks;
import tests.api.models.book.Book;
import tests.api.models.book.LibraryCardWrapper;
import tests.api.specs.Specs;

import java.io.File;
import java.util.List;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.api.specs.Specs.*;

public class ApiSteps {

    @Step("Perform request to add book to library")
    public Book addBookToLibrary(String bookUuid) {
        AddedBook addedBook = new AddedBook().setBookUuid(bookUuid);
        return given()
                .spec(Specs.request)
                .body(addedBook)
                .when()
                .post("/profile/library_cards")
                .then()
                .spec(Specs.response)
                .extract().response().as(LibraryCardWrapper.class)
                .getLibraryCard().getBook();
    }

    @Step("Perform request to remove book from user's library")
    public void removeBookFromLibrary(String LibraryCardUuid) {
        given()
                .spec(Specs.request)
                .when()
                .delete("/profile/library_cards/" + LibraryCardUuid)
                .then()
                .log().all()
                .statusCode(204);
    }

    @Step("Request list of all books in user's library")
    public List<UsersBook> getAllBooks() {
        return given()
                .spec(Specs.request)
                .when()
                .get("/profile/books?page=1&per_page=20")
                .then()
                .spec(Specs.response)
                .extract().response().as(UsersBooks.class)
                .getBooks();
    }

    @Step("Ensure book is not in user's library")
    public void ensureBookIsNotInLibrary(String bookUuid) {
        getAllUsersBooksByUuid(bookUuid)
                .findFirst()
                .ifPresent(usersBook -> removeBookFromLibrary(usersBook.getUsersLibraryCard().getUuid()));
    }

    @Step("Ensure book is in user's library")
    public String ensureBookIsInLibrary(String bookUuid) {
        String libraryCardUuid = getAllUsersBooksByUuid(bookUuid)
                .map((usersBook) -> usersBook.getUsersLibraryCard().getUuid())
                .findFirst().orElse("");
        if (libraryCardUuid.equals("")) {
            libraryCardUuid = addBookToLibrary(bookUuid).getLibraryCardUuid();
        }
        return libraryCardUuid;
    }

    @Step("Verify the book is in the user's library")
    public void checkBookIsInLibrary(String bookUuid) {
        assertThat(isBookInLibrary(bookUuid)).isTrue();

    }

    @Step("Verify the book is not in the user's library")
    public void checkBookIsNotInLibrary(String bookUuid) {
        assertThat(isBookInLibrary(bookUuid)).isFalse();
    }

    @Step("Verify uuid in the response")
    public void checkAddedBookResponseParameters(Book book, String expectedUuid) {
        assertThat(book.getUuid()).isEqualTo(expectedUuid);
    }

    private boolean isBookInLibrary(String bookUuid) {
        return getAllBooks().stream()
                .anyMatch((usersBook) -> usersBookHasUuid(usersBook, bookUuid));
    }

    private boolean usersBookHasUuid(UsersBook usersBook, String bookUuid) {
        return usersBook.getUuid().equals(bookUuid);
    }

    private Stream<UsersBook> getAllUsersBooksByUuid(String bookUuid) {
        return getAllBooks().stream()
                .filter(usersBook -> usersBookHasUuid(usersBook, bookUuid));
    }

    @Step("Perform request to create new bookshelf")
    public Bookshelf createNewBookshelf(String name, String annotation) {
        return given()
                .filter(CustomAllureListener.withCustomTemplates())
                .log().all()
                .baseUri("https://bookmate.com/p/api/v5")
                .header("x-csrf-token", X_CSRF_TOKEN)
                .cookie("_csrf", CSRF)
                .cookie("bms", BMS)
                .contentType("multipart/form-data")
                .multiPart("bookshelf[title]", name)
                .multiPart("bookshelf[annotation]", annotation)
                .multiPart("bookshelf[cover]", new File("./images/icons/bookmate.png"))
                .multiPart("bookshelf[state]", "published")
                .when()
                .post("/bookshelves")
                .then()
                .spec(response)
                .extract().response().as(NewBookshelfResponse.class)
                .getBookshelf();
    }

    @Step("Verify the bookshelf exists")
    public void checkBookshelfExists(Bookshelf bookshelf) {
        assertThat(getAllBookshelves()).contains(bookshelf);
    }

    @Step("Verify the bookshelf does not exist")
    public void checkBookshelfDoesNotExist(Bookshelf bookshelf) {
        assertThat(getAllBookshelves()).doesNotContain((bookshelf));
    }

    @Step("Request list of all user's bookshelves")
    public List<Bookshelf> getAllBookshelves() {
        return given()
                .spec(Specs.request)
                .when()
                .get("/profile/bookshelves?page=1&per_page=10")
                .then()
                .extract().response().as(BookshelvesResponse.class)
                .getBookshelves();
    }

    @Step("Verify title and annotation in the response")
    public void checkNewBookshelfResponseParameters(Bookshelf bookshelf, String expectedName, String expectedAnnotation) {
        assertThat(bookshelf.getTitle()).isEqualTo(expectedName);
        assertThat(bookshelf.getAnnotation()).isEqualTo(expectedAnnotation);
    }

    @Step("Perform request to delete the bookshelf")
    public void deleteBookshelf(String bookshelfUuid) {
        given()
                .spec(Specs.request)
                .when()
                .delete("/bookshelves/" + bookshelfUuid)
                .then()
                .log().all()
                .statusCode(204);
    }
}

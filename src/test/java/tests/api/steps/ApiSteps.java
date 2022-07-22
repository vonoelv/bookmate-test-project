package tests.api.steps;

import io.qameta.allure.Step;
import tests.api.models.bookshelves.Bookshelf;
import tests.api.models.bookshelves.NewBookshelfResponse;
import tests.api.models.profile.books.UsersBook;
import tests.api.models.profile.books.UsersBooks;
import tests.api.models.profile.bookshelves.BookshelvesResponse;
import tests.api.models.profile.library_cards.AddedBook;
import tests.api.models.profile.library_cards.Book;
import tests.api.models.profile.library_cards.LibraryCardWrapper;
import tests.api.specs.Specs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.api.specs.Specs.request;
import static tests.api.specs.Specs.response;

public class ApiSteps {

    @Step("Perform request to add book to library")
    public Book addBookToLibrary(String bookUuid) {
        AddedBook addedBook = new AddedBook().setBookUuid(bookUuid);
        return given()
                .spec(request)
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
                .spec(request)
                .when()
                .delete("/profile/library_cards/" + LibraryCardUuid)
                .then()
                .log().all()
                .statusCode(204);
    }

    @Step("Request list of all books in user's library")
    public List<UsersBook> getAllBooks() {
        Function<Integer, List<UsersBook>> requestOnePage = (page) -> given()
                .spec(request)
                .formParams("page", page, "per_page", 20)
                .when()
                .get("/profile/books")
                .then()
                .spec(Specs.response)
                .extract().response().as(UsersBooks.class)
                .getBooks();
        return getAllPages(requestOnePage);
    }

    @Step("Ensure book is not in user's library")
    public void ensureBookIsNotInLibrary(String bookUuid) {
        UsersBook usersBook = getUsersBookByUuid(bookUuid);
        if (usersBook.isInitialized()) {
            removeBookFromLibrary(usersBook.getUsersLibraryCard().getUuid());
        }
    }

    @Step("Ensure book is in user's library")
    public String ensureBookIsInLibrary(String bookUuid) {
        UsersBook usersBook = getUsersBookByUuid(bookUuid);
        String libraryCardUuid;
        if (!usersBook.isInitialized()) {
            libraryCardUuid = addBookToLibrary(bookUuid).getLibraryCardUuid();
        } else {
            libraryCardUuid = usersBook.getUsersLibraryCard().getUuid();
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

    private UsersBook getUsersBookByUuid(String bookUuid) {
        return getAllBooks().stream()
                .filter(usersBook -> usersBookHasUuid(usersBook, bookUuid))
                .findFirst()
                .orElseGet(UsersBook::new);
    }

    @Step("Perform request to create new bookshelf")
    public Bookshelf createNewBookshelf(String name, String annotation) {
        return given()
                .spec(request)
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
        Function<Integer, List<Bookshelf>> requestOnePage = (page) ->
                given()
                        .spec(request)
                        .formParams("page", page, "per_page", 10)
                        .when()
                        .get("/profile/bookshelves")
                        .then()
                        .extract().response().as(BookshelvesResponse.class)
                        .getBookshelves();
        return getAllPages(requestOnePage);
    }

    private static <T> List<T> getAllPages(Function<Integer, List<T>> requestOnePage) {
        List<T> result = new ArrayList<>();
        List<T> onePage;
        int page = 1;
        do {
            onePage = requestOnePage.apply(page);
            result.addAll(onePage);
            page++;
        } while (!onePage.isEmpty());
        return result;
    }

    @Step("Verify title and annotation in the response")
    public void checkNewBookshelfResponseParameters(Bookshelf bookshelf, String expectedName, String expectedAnnotation) {
        assertThat(bookshelf.getTitle()).isEqualTo(expectedName);
        assertThat(bookshelf.getAnnotation()).isEqualTo(expectedAnnotation);
    }

    @Step("Perform request to delete the bookshelf")
    public void deleteBookshelf(String bookshelfUuid) {
        given()
                .spec(request)
                .when()
                .delete("/bookshelves/" + bookshelfUuid)
                .then()
                .log().all()
                .statusCode(204);
    }
}

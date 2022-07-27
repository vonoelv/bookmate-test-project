package tests.api.pages;

import io.qameta.allure.Step;
import tests.api.models.profile.books.get.GetAllUsersBooksRequests;
import tests.api.models.profile.books.get.UsersBook;
import tests.api.models.profile.library_cards.post.AddBookToLibraryRequest;
import tests.api.models.profile.library_cards.post.AddBookToLibraryResponse;
import tests.api.models.profile.library_cards.post.Book;
import tests.api.specs.Specs;

import java.util.List;
import java.util.function.Function;

import static helpers.Pagination.getAllPages;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.api.specs.Specs.request;

public class BooksPage {

    @Step("Perform request to add book to library")
    public Book addBookToLibrary(String bookUuid) {
        AddBookToLibraryRequest body = new AddBookToLibraryRequest().setBookUuid(bookUuid);
        return given()
                .spec(request)
                .body(body)
                .when()
                .post("/profile/library_cards")
                .then()
                .spec(Specs.response)
                .extract().response().as(AddBookToLibraryResponse.class)
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

    private UsersBook getUsersBookByUuid(String bookUuid) {
        return getAllBooks().stream()
                .filter(usersBook -> usersBookHasUuid(usersBook, bookUuid))
                .findFirst()
                .orElseGet(UsersBook::new);
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
                .extract().response().as(GetAllUsersBooksRequests.class)
                .getBooks();
        return getAllPages(requestOnePage);
    }

    private boolean usersBookHasUuid(UsersBook usersBook, String bookUuid) {
        return usersBook.getUuid().equals(bookUuid);
    }

    @Step("Verify the book is in the user's library")
    public void checkBookIsInLibrary(String bookUuid) {
        assertThat(isBookInLibrary(bookUuid)).isTrue();
    }

    @Step("Verify the book is not in the user's library")
    public void checkBookIsNotInLibrary(String bookUuid) {
        assertThat(isBookInLibrary(bookUuid)).isFalse();
    }

    private boolean isBookInLibrary(String bookUuid) {
        return getAllBooks().stream()
                .anyMatch((usersBook) -> usersBookHasUuid(usersBook, bookUuid));
    }

    @Step("Verify uuid in the response")
    public void checkAddedBookResponseParameters(Book book, String expectedUuid) {
        assertThat(book.getUuid()).isEqualTo(expectedUuid);
    }
}

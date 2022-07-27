package tests.api.pages;

import io.qameta.allure.Step;
import tests.api.models.bookshelves.get.GetBookshelfResponse;
import tests.api.models.bookshelves.post_put.Bookshelf;
import tests.api.models.bookshelves.post_put.BookshelfResponse;
import tests.api.models.bookshelves.posts.BookshelfPost;
import tests.api.models.bookshelves.posts.get.GetAllBooksOnBookshelfResponse;
import tests.api.models.bookshelves.posts.post.AddBookOnBookshelfResponse;
import tests.api.models.bookshelves.posts.post.AddBookToBookshelfRequest;
import tests.api.models.bookshelves.posts.post.Post;
import tests.api.models.profile.bookshelves.get.BookshelvesResponse;

import java.io.File;
import java.util.List;
import java.util.function.Function;

import static helpers.Pagination.getAllPages;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.api.specs.Specs.request;
import static tests.api.specs.Specs.response;

public class BookshelvesPage {

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
                .extract().response().as(BookshelfResponse.class)
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

    @Step("Verify title and annotation in the response")
    public void checkBookshelfResponseParameters(Bookshelf bookshelf, String expectedName, String expectedAnnotation) {
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

    @Step("Perform request to add the book to the bookshelf")
    public BookshelfPost addBookToBookshelf(String bookUuid, String bookshelfUuid, String annotation) {
        AddBookToBookshelfRequest body = new AddBookToBookshelfRequest(new Post("book", bookUuid, annotation));
        return given()
                .spec(request)
                .body(body)
                .when()
                .post("/bookshelves/" + bookshelfUuid + "/posts")
                .then()
                .spec(response)
                .extract().response().as(AddBookOnBookshelfResponse.class)
                .getPost();
    }

    @Step("Verify the book is on the bookshelf")
    public void checkBookIsOnBookshelf(String bookUuid, String bookshelfUuid) {
        assertThat(getAllPostsForBookshelf(bookshelfUuid).stream()
                .anyMatch((post) -> post.getResource().getUuid().equals(bookUuid)))
                .isTrue();
    }

    private List<BookshelfPost> getAllPostsForBookshelf(String bookshelfUuid) {
        Function<Integer, List<BookshelfPost>> requestOnePage = (page) ->
                given()
                        .spec(request)
                        .formParams("page", page, "per_page", 20)
                        .when()
                        .get("/bookshelves/" + bookshelfUuid + "/posts")
                        .then()
                        .spec(response)
                        .extract().response().as(GetAllBooksOnBookshelfResponse.class)
                        .getPosts();
        return getAllPages(requestOnePage);
    }

    @Step("Verify annotation, uuids of the book and the bookshelf in the response")
    public void checkAddBookOnBookshelfResponseParameters(BookshelfPost bookshelfPost, String annotation,
                                                          String bookUuid, String bookshelfUuid) {
        assertThat(bookshelfPost.getAnnotation()).isEqualTo(annotation);
        assertThat(bookshelfPost.getResource().getUuid()).isEqualTo(bookUuid);
        assertThat(bookshelfPost.getBookshelf().getUuid()).isEqualTo(bookshelfUuid);
    }

    @Step("Perform request to remove the book from the bookshelf")
    public void removeBookFromBookshelf(BookshelfPost post) {
        given()
                .spec(request)
                .contentType(JSON)
                .body("{}")
                .when()
                .delete("/bookshelves/" + post.getBookshelf().getUuid() + "/posts/" + post.getUuid())
                .then()
                .log().all()
                .statusCode(204);
    }

    @Step("Verify the book is not on the bookshelf")
    public void checkBookIsNotOnBookshelf(String bookUuid, String bookshelfUuid) {
        assertThat(getAllPostsForBookshelf(bookshelfUuid).stream()
                .noneMatch((post) -> post.getResource().getUuid().equals(bookUuid)))
                .isTrue();
    }

    @Step("Request the bookshelf by uuid")
    public Bookshelf getBookshelfByUuid(String bookshelfUuid) {
        return given()
                .spec(request)
                .when()
                .get("/bookshelves/" + bookshelfUuid)
                .then()
                .spec(response)
                .extract().response().as(GetBookshelfResponse.class)
                .getBookshelf();
    }

    @Step("Verify annotation, uuids and title of the bookshelf")
    public void checkBookshelfParameters(Bookshelf bookshelf, String annotation, String uuid, String title) {
        assertThat(bookshelf.getAnnotation()).isEqualTo(annotation);
        assertThat(bookshelf.getUuid()).isEqualTo(uuid);
        assertThat(bookshelf.getTitle()).isEqualTo(title);
    }

    @Step("Edit a bookshelf")
    public Bookshelf editBookshelf(String uuid, String name, String annotation) {
        return given()
                .spec(request)
                .contentType("multipart/form-data")
                .multiPart("bookshelf[title]", name)
                .multiPart("bookshelf[annotation]", annotation)
                .multiPart("bookshelf[cover]", new File("./images/icons/bookmate.png"))
                .multiPart("bookshelf[state]", "published")
                .when()
                .put("/bookshelves/" + uuid)
                .then()
                .spec(response)
                .extract().response().as(BookshelfResponse.class)
                .getBookshelf();
    }


    public void checkBookshelfDataByUuid(String uuid, String name, String annotation) {
        checkBookshelfParameters(getBookshelfByUuid(uuid), annotation, uuid, name);
    }
}

package tests.api.models.bookshelves.posts.get;

import lombok.Data;
import tests.api.models.bookshelves.posts.BookshelfPost;

import java.util.List;

@Data
public class GetAllBooksOnBookshelfResponse {
    List<BookshelfPost> posts;
}

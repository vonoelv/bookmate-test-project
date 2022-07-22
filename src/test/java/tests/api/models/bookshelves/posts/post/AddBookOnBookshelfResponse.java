package tests.api.models.bookshelves.posts.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import tests.api.models.bookshelves.posts.BookshelfPost;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddBookOnBookshelfResponse {
    BookshelfPost post;
}
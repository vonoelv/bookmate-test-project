package tests.api.models.bookshelves.posts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import tests.api.models.bookshelves.post.Bookshelf;
import tests.api.models.bookshelves.posts.post.Resource;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookshelfPost {
    String annotation;
    Bookshelf bookshelf;
    Resource resource;
    String uuid;
}
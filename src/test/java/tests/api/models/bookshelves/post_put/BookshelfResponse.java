package tests.api.models.bookshelves.post_put;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookshelfResponse {
    Bookshelf bookshelf;
}

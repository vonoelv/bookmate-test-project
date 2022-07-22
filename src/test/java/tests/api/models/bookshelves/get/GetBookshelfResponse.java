package tests.api.models.bookshelves.get;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import tests.api.models.bookshelves.post_put.Bookshelf;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetBookshelfResponse {
    Bookshelf bookshelf;
}

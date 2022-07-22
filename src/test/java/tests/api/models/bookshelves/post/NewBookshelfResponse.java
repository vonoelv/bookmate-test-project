package tests.api.models.bookshelves.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewBookshelfResponse {
    Bookshelf bookshelf;
}

package tests.api.models.bookshelfs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewBookshelfResponse {
    Bookshelf bookshelf;
}

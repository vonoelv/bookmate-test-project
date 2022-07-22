package tests.api.models.profile.bookshelves.get;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import tests.api.models.bookshelves.post_put.Bookshelf;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookshelvesResponse {
    List<Bookshelf> bookshelves;
}

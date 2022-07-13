package tests.api.models.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import tests.api.models.bookshelfs.Bookshelf;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookshelvesResponse {
    List<Bookshelf> bookshelves;
}

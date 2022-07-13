package tests.api.models.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LibraryCardWrapper {

    @JsonProperty("library_card")
    private LibraryCard libraryCard;
}

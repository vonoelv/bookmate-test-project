package tests.api.models.profile.library_cards.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddBookToLibraryResponse {
    @JsonProperty("library_card")
    private LibraryCard libraryCard;
}

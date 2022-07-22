package tests.api.models.profile.library_cards.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddBookToLibraryRequest {
    @JsonProperty("book_uuid")
    private String bookUuid;
}
package tests.api.models.profile.library_cards;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    private String title;
    private String uuid;
    @JsonProperty("library_card_uuid")
    private String libraryCardUuid;
    @JsonProperty("in_library")
    private boolean inLibrary;
    @JsonProperty("is_uploaded")
    private boolean isUploaded;
}
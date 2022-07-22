package tests.api.models.profile.books.get;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersBook {
    private String title;
    private String uuid;
    private String type;
    @JsonProperty("library_card")
    UsersLibraryCard usersLibraryCard;

    public boolean isInitialized() {
        return (title != null) && (uuid != null) && (type != null) && (usersLibraryCard != null);
    }
}

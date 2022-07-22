package tests.api.models.profile.library_cards.post;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LibraryCard {
    private Book book;
}
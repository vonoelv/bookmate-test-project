package tests.api.models.profile.books.get;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetAllUsersBooksRequests {
    private List<UsersBook> books;
}

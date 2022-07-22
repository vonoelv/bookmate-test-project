package tests.api.models.bookshelves.posts.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Post {
    @JsonProperty("resource_name")
    String resourceName;
    @JsonProperty("resource_uuid")
    String resourceUuid;
    String annotation;
}
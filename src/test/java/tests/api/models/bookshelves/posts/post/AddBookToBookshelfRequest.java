package tests.api.models.bookshelves.posts.post;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddBookToBookshelfRequest {
    Post post;
}
package tests.api.models.bookshelves.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Objects;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Bookshelf {
    String title;
    String annotation;
    String uuid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bookshelf bookshelf = (Bookshelf) o;
        return title.equals(bookshelf.title) && annotation.equals(bookshelf.annotation) && uuid.equals(bookshelf.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, annotation, uuid);
    }
}

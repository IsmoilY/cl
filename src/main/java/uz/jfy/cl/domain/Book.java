package uz.jfy.cl.domain;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Book implements Serializable {

    private String id;
    private String ISBN;
    private String genre;
    private Author author;
    private LocalDateTime publishedAt;
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!Objects.equals(id, book.id)) return false;
        return Objects.equals(ISBN, book.ISBN);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ISBN != null ? ISBN.hashCode() : 0);
        return result;
    }
}

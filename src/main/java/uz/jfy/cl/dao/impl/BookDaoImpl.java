package uz.jfy.cl.dao.impl;

import org.springframework.stereotype.Repository;
import uz.jfy.cl.dao.BookDao;
import uz.jfy.cl.domain.Author;
import uz.jfy.cl.domain.Book;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    @Override
    public List<Book> findAll() {
        List<Book> list = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            Book book = Book.builder()
                    .id(String.valueOf(i))
                    .ISBN(String.format("N0000%d", i))
                    .genre(String.format("genre %d", i))
                    .publishedAt(LocalDateTime.now())
                    .author(new Author("firstName" + i, "lastName" + i))
                    .description(String.format("some decription %d", i))
                    .build();
            list.add(book);
        }

        return list;
    }

}

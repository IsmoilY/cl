package uz.jfy.cl.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.jfy.cl.domain.Author;
import uz.jfy.cl.domain.Book;
import uz.jfy.cl.service.BookService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {

    //    @Qualifier("mapCachedBookService")
    @Autowired
    private BookService service;

    @GetMapping
    public List<Book> findAll() {
        return service.findAll();
    }

    @SneakyThrows
    @PutMapping
    public List<Book> update() {
        var allBooks = service.findAll();

        for (var book : allBooks) {
            book.setGenre("Science");
        }

        Thread.sleep(5000);
        return service.add(allBooks, allBooks);
    }

    @SneakyThrows
    @PostMapping
    public List<Book> create() {
        var booksToBeAdded = new ArrayList<Book>();
        var allBooks = service.findAll();

        int bookId = 999;

        Book book = Book.builder()
                .id(String.valueOf(bookId))
                .ISBN(String.format("N0000%d", bookId))
                .genre(String.format("genre %d", bookId))
                .publishedAt(LocalDateTime.now())
                .author(new Author("firstName" + bookId, "lastName" + bookId))
                .description(String.format("some decription %d", bookId))
                .build();

        booksToBeAdded.add(book);

        Thread.sleep(5000);
        var result = service.add(allBooks, booksToBeAdded);
        log.info("size: {}", result.size());
        return result;
    }

}

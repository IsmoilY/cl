package uz.jfy.cl.service;

import uz.jfy.cl.domain.Book;

import java.util.List;

public interface BookService {

    String CACHE_NAME = "books";

    List<Book> findAll();

    List<Book> add(List<Book> list, List<Book> toBeAdded);

}

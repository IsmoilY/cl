package uz.jfy.cl.dao;

import uz.jfy.cl.domain.Book;

import java.util.List;

public interface BookDao {

    List<Book> findAll();

}

package uz.jfy.cl.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import uz.jfy.cl.dao.BookDao;
import uz.jfy.cl.domain.Book;
import uz.jfy.cl.service.BookService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Primary
@Repository
public class BookServiceImpl implements BookService {

    private final BookDao dao;

    @Override
    @Cacheable(cacheNames = CACHE_NAME, key = "'bookList'", sync = true)
    public List<Book> findAll() {
        log.warn("fetching books");
        return dao.findAll();
    }

    @Override
    @CachePut(cacheNames = CACHE_NAME, key = "'bookList'")
    public List<Book> add(List<Book> list, List<Book> toBeAdded) {
        log.warn("adding a new book to cache");
        list.addAll(toBeAdded);
        return list;
    }

}
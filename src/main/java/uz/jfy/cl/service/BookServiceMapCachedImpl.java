package uz.jfy.cl.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import uz.jfy.cl.dao.BookDao;
import uz.jfy.cl.domain.Book;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequiredArgsConstructor
@Repository("mapCachedBookService")
public class BookServiceMapCachedImpl implements BookService {

    private final Map<String, List<Book>> cache = new ConcurrentHashMap<>();

    private final BookDao dao;

    @Override
    public List<Book> findAll() {
        if (cache.isEmpty()) {
            cache.put(CACHE_NAME, dao.findAll());
        }
        return cache.get(CACHE_NAME);
    }

    @Override
    public List<Book> add(List<Book> list, List<Book> toBeAdded) {
        if (!cache.isEmpty() && cache.containsKey(CACHE_NAME)) {
            cache.get(CACHE_NAME).addAll(toBeAdded);
        } else {
            cache.put(CACHE_NAME, toBeAdded);
        }
        return list;
    }
}
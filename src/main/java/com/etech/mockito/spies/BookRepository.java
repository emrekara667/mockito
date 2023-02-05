package com.etech.mockito.spies;

import com.etech.mockito.stubbing.Book;

import java.util.List;

public interface BookRepository {

    List<com.etech.mockito.stubbing.Book> findNewBooks(int days);

    com.etech.mockito.stubbing.Book findBookByBookId(String bookId);

    void save(Book book);
}

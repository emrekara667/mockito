package com.etech.mockito.behaviour.verification;

import java.util.List;

public interface  BookRepository {
    void save(Book book);

    Book findBookById(String bookId);
}

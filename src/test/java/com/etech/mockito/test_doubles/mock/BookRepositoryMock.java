package com.etech.mockito.test_doubles.mock;


import org.junit.jupiter.api.Assertions;

import java.util.Collection;

public class BookRepositoryMock implements BookRepository {

    int saveCalled = 0;
    Book lastAddedBook = null;

    @Override
    public void save(Book book) {
        saveCalled++;
        lastAddedBook = book;
    }

    public void verify(Book book, int times){
        Assertions.assertEquals(times, saveCalled);
        Assertions.assertEquals(book, lastAddedBook);
    }
}

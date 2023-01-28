package com.etech.mockito.test_doubles.mock;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class MockTest {

    @Test
    public void demoMock() {
        BookRepositoryMock bookRepositoryMock = new BookRepositoryMock();
        BookService bookService = new BookService(bookRepositoryMock);

        Book book1 = new Book("1234", "Mockito in action", 500, LocalDate.now());
        Book book2 = new Book("1235", "JUnit 5 in action", 400, LocalDate.now());

        bookService.addBook(book1);  // return
        bookService.addBook(book2);  // save will be called

        bookRepositoryMock.verify(book2, 1);
    }
}

package com.etech.mockito.test_doubles.spy;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class SpyTest {

    @Test
    public void demoSpy() {
        BookRepositorySpy bookRepositorySpy = new BookRepositorySpy();
        BookService bookService = new BookService(bookRepositorySpy);

        Book book1 = new Book("1234", "Mockito in action", 300, LocalDate.now());
        Book book2 = new Book("1235", "JUnit 5 in action", 400, LocalDate.now());

        bookService.addBook(book1);
        bookService.addBook(book2);

        assertEquals(2, bookRepositorySpy.timesCalled());
        assertTrue(bookRepositorySpy.calledWith(book2));
    }

    @Test
    public void demoSpyWithMockito() {
        BookRepository bookRepositorySpy = spy(BookRepository.class);
        BookService bookService = new BookService(bookRepositorySpy);

        Book book1 = new Book("1234", "Mockito in action", 500, LocalDate.now());
        Book book2 = new Book("1235", "JUnit 5 in action", 400, LocalDate.now());

        bookService.addBook(book1);  // return
        bookService.addBook(book2);  // save will be called


        verify(bookRepositorySpy, times(1)).save(book2);
        verify(bookRepositorySpy, times(0)).save(book1);
    }
}

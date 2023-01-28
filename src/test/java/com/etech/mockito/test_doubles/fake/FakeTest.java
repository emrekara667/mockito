package com.etech.mockito.test_doubles.fake;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class FakeTest {


    @Test
    public void testFake() {
        FakeBookRepository bookRepository = new FakeBookRepository();
        BookService bookService = new BookService(bookRepository);

        bookService.addBook(new Book("1234", "Mockito in action", 250, LocalDate.now()));
        bookService.addBook(new Book("1235", "JUnit in action", 200, LocalDate.now()));

        Assertions.assertEquals(2, bookService.findNumberOfBooks());
    }


}

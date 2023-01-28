package com.etech.mockito.test_doubles.dummy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class DummyTest {

    @Test
    public void demoDummy(){
        FakeBookRepository fakeBookRepository = new FakeBookRepository();
        DummyEmailService dummyEmailService = new DummyEmailService();
        BookService bookService = new BookService(fakeBookRepository, dummyEmailService);


        bookService.addBook(new Book("1234", "Mockito in action", 500, LocalDate.now()));
        bookService.addBook(new Book("1235", "JUnit 5 in action", 450, LocalDate.now()));

        Assertions.assertEquals(2 , bookService.findNumberOfBooks());
    }
}

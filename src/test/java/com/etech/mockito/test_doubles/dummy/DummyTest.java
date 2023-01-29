package com.etech.mockito.test_doubles.dummy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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


    @Test
    public void demoDummyWithMockito(){
        BookRepository bookRepository = mock(BookRepository.class);
        EmailService emailService = mock(EmailService.class);
        BookService bookService = new BookService(bookRepository, emailService);

       Book book1 = new Book("1234", "Mockito in action", 250, LocalDate.now());
       Book book2 = new Book("1235", "Junit in action", 200, LocalDate.now());


        Collection<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        when(bookRepository.findAll()).thenReturn(books);

        Assertions.assertEquals(2, bookService.findNumberOfBooks());
    }
}

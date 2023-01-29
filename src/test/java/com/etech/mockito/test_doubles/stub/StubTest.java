package com.etech.mockito.test_doubles.stub;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StubTest {


    @Test
    public void demoStub(){

        BookRepository bookRepository = new BookRepositoryStub();
        BookService bookService = new BookService(bookRepository);

        List<Book> withAppliedDiscount = bookService.getNewBooksWithAppliedDiscount(10, 7);

        Assertions.assertEquals(2, withAppliedDiscount.size());
        Assertions.assertEquals(450, withAppliedDiscount.get(0).getPrice());
        Assertions.assertEquals(225, withAppliedDiscount.get(1).getPrice());
    }

    @Test
    public void demoStubWithMockito(){

        BookRepository bookRepository = Mockito.mock(BookRepository.class);
        BookService bookService = new BookService(bookRepository);

        Book book1 = new Book("1234", "Mockito in action", 500, LocalDate.now());
        Book book2 = new Book("1235", "Junit in action", 400, LocalDate.now());
        List<Book> newBooks = new ArrayList<>();
        newBooks.add(book1);
        newBooks.add(book2);

        Mockito.when(bookRepository.findNewBooks(7)).thenReturn(newBooks);

        List<Book> withAppliedDiscount = bookService.getNewBooksWithAppliedDiscount(10, 7);

        Assertions.assertEquals(2, withAppliedDiscount.size());
        Assertions.assertEquals(450, withAppliedDiscount.get(0).getPrice());
        Assertions.assertEquals(360, withAppliedDiscount.get(1).getPrice());
    }
}

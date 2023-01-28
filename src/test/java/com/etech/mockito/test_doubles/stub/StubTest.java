package com.etech.mockito.test_doubles.stub;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}

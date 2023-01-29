package com.etech.mockito.annotations.support;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AnnotationsTest {

    @InjectMocks // Class under test
    private BookService bookService;
    @Mock  // External dependencies
    private BookRepository bookRepository;

    @Test
    public void demoCreateMockUsingAnnotations() {
        Book book1 = new Book("1234", "Mockito in action", 500, LocalDate.now());
        Book book2 = new Book("1235", "Junit in action", 400, LocalDate.now());
        List<Book> newBooks = new ArrayList<>();
        newBooks.add(book1);
        newBooks.add(book2);

        when(bookRepository.findNewBooks(7)).thenReturn(newBooks);

        List<Book> withAppliedDiscount = bookService.getNewBooksWithAppliedDiscount(10, 7);

        Assertions.assertEquals(2, withAppliedDiscount.size());
        Assertions.assertEquals(450, withAppliedDiscount.get(0).getPrice());
        Assertions.assertEquals(360, withAppliedDiscount.get(1).getPrice());
    }
}

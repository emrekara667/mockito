package com.etech.mockito.behaviour.verification;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void testAddBook(){
        Book book1 = new Book("1234", "Mockito in action", 600, LocalDate.now());

        bookService.addBook(book1);

        verify(bookRepository).save(book1);
    }

    @Test
    public void testSaveBookWithBookRequest() {
        // Setup
        BookRequest bookRequest = new BookRequest("Mockito in action", 500, LocalDate.now());
        Book book = new Book(null, "Mockito in action", 500, LocalDate.now());
        //doNothing().when(bookRepository).save(book);

        // Execute
        bookService.addBook(bookRequest); // ==
        verify(bookRepository, times(0)).save(book);
    }
}

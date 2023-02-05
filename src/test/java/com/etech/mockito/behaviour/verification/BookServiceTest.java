package com.etech.mockito.behaviour.verification;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void testAddBook() {
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
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void testSaveBookWithBookRequest2() {
        // Setup
        BookRequest bookRequest = new BookRequest("Mockito in action", 600, LocalDate.now());
        Book book = new Book(null, "Mockito in action", 600, LocalDate.now());
        //doNothing().when(bookRepository).save(book);

        // Execute
        bookService.addBook(bookRequest); // ==
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void testSaveBookWithBookRequest3() {
        // Setup
        BookRequest bookRequest = new BookRequest("Mockito in action", 500, LocalDate.now());
        Book book = new Book(null, "Mockito in action", 500, LocalDate.now());
        //doNothing().when(bookRepository).save(book);

        // Execute
        bookService.addBook(bookRequest); // ==
        verify(bookRepository, never()).save(book);
    }

    @Test
    public void testUpdatePrice() {
        bookService.updatePrice(null, 600);
        verifyNoInteractions(bookRepository);
    }

    @Test
    public void testUpdatePrice2() {
        Book book = new Book("1234", "Mockito in action", 500, LocalDate.now());
        when(bookRepository.findBookById("1234")).thenReturn(book);
        bookService.updatePrice("1234", 500);
        verify(bookRepository).findBookById("1234");
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void testUpdatePrice3() {
        Book book = new Book("1234", "Mockito in action", 500, LocalDate.now());
        when(bookRepository.findBookById("1234")).thenReturn(book);
        bookService.updatePrice("1234", 500);

        InOrder inOrder = inOrder(bookRepository);
        inOrder.verify(bookRepository).findBookById("1234");
        inOrder.verify(bookRepository).save(book);
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void testSaveBookWithBookRequest4() {
        // Setup
        BookRequest bookRequest = new BookRequest("Mockito in action", 600, LocalDate.now());
        Book book = new Book(null, "Mockito in action", 600, LocalDate.now());
        //doNothing().when(bookRepository).save(book);

        // Execute
        bookService.addBook(bookRequest); // ==
        bookService.addBook(bookRequest); // ==
        bookService.addBook(bookRequest); // ==
        //verify(bookRepository, atLeast(2)).save(book);
        //verify(bookRepository, times(2)).save(book);
        //verify(bookRepository, atMost(3)).save(book);
        //verify(bookRepository,never()).save(book);
        //verify(bookRepository, atMostOnce()).save(book);
        verify(bookRepository, atLeastOnce()).save(book);
    }
}

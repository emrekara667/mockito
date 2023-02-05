package com.etech.mockito.exception_handling;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void testTotalPriceOfBook() throws SQLException {
        when(bookRepository.findAllBooks()).thenThrow(SQLException.class);
        assertThrows(DatabaseReadException.class, () -> bookService.getTotalPriceOfBooks());
    }

    @Test
    public void testTotalPriceOfBook2() throws SQLException {
        when(bookRepository.findAllBooks()).thenThrow(new SQLException("Database not available"));
        assertThrows(DatabaseReadException.class, () -> bookService.getTotalPriceOfBooks());
    }

    @Test
    public void testAddBook() throws SQLException {
        Book book1 = new Book("1234", "Mockito in action", 600, LocalDate.now());
        doThrow(SQLException.class).when(bookRepository).save(book1);
        assertThrows(DatabaseWriteException.class, () -> bookService.addBook(book1));
    }
}

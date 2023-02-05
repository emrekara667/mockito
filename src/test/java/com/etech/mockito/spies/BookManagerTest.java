package com.etech.mockito.spies;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookManagerTest {

    @InjectMocks
    private BookManager bookManager;

    @Spy
    private BookService bookService;

    @Test
    public void testMockitoSpy() {
        // We need to mock findBook because it is communicating to database or not implement.
        // We need to call getAppliedDiscount to calculate the discounted price

        Book book = new Book("1234", "Mockito in action", 500, LocalDate.now());

        doReturn(book).when(bookService).findBook("1234");
        when(bookService.findBook("1234")).thenReturn(book);
        int actualDiscount = bookManager.applyDiscountOnBook("1234", 10);

        Assertions.assertEquals(450, actualDiscount);

    }

}

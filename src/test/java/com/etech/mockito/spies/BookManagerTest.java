package com.etech.mockito.spies;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

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
        // Eger doReturn yerine thenReturn yaparsan istedıgın degeri doner ve metodun da
        // içinden gecer bu durumda hata fırlatır eger doReturn yaparsan
        // degeri dondurur metodun ıcıne girmez. Spy olan sınıflarda ıcıne gırilmesini
        // istemedıgımız fonksıyonlar ıcın doReturn u kullanabiliriz.
        // Eger yukarıdabookservice de spy olmasa ıkı durumdada calısırdı cunku mocklanmıs olucaktı.
        // when(bookService.findBook("1234")).thenReturn(book);
        int actualDiscount = bookManager.applyDiscountOnBook("1234", 10);

        Assertions.assertEquals(450, actualDiscount);

    }

}

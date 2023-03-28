package com.etech.mockito.stubbing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;


    @Test
    public void testCalculateTotalCostOfBooks() {
        List<String> bookIds = new ArrayList<>();
        bookIds.add("1234");
        bookIds.add("1234");

        Book book1 = new Book("1234", "Mockito in action", 500, LocalDate.now());
        Book book2 = new Book("1235", "Junit in action", 400, LocalDate.now());

        when(bookRepository.findBookByBookId("1234"))
                .thenReturn(book1)
                .thenReturn(book1);
        // when(bookRepository.findBookByBookId("1235")).thenReturn(book2);
        /* doReturn(book1).when(bookRepository.findBookByBookId("1234"));
         doReturn(book2).when(bookRepository.findBookByBookId("1235"));
*/
        int actualCost = bookService.calculateTotalCost(bookIds);

        assertEquals(1000, actualCost, "Cost is not as expected");
    }

    @Test
    public void testSaveBook() {
        Book book1 = new Book(null, "Mockito in action", 500, LocalDate.now());
        doNothing().when(bookRepository).save(book1);

        bookService.addBook(book1); // ==
    }

    @Test
    public void testSaveBookWithBookRequest() {
        // Setup
        BookRequest bookRequest = new BookRequest("Mockito in action", 500, LocalDate.now());
        Book book = new Book(null, "Mockito in action", 500, LocalDate.now());
        //doNothing().when(bookRepository).save(book);

        // Execute
        bookService.addBook(bookRequest); // ==
    }

    @Test
    void saveTwoBook() {

        when(bookRepository.persist(any())).thenReturn(null).then(invocationOnMock -> {
            Book argument = invocationOnMock.getArgument(0);
            argument.setTitle("invocation");
            return argument;
        });

        Book response = bookService.saveTwoBook();

        ArgumentCaptor<Book> captor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository, times(2)).persist(captor.capture());
        List<Book> allValues = captor.getAllValues();
        assertEquals("book first", allValues.get(0).getTitle(),"1");
        assertEquals("invocation", allValues.get(1).getTitle(),"2");
        assertEquals("invocation", response.getTitle(),"3");


    }
}

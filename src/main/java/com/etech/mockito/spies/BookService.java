package com.etech.mockito.spies;

import com.etech.mockito.stubbing.BookRepository;

public class BookService {


    public Book findBook(String bookId) {
        // Code to bring book from database
        throw new RuntimeException("Method not implemented");
        //return null; // "Method not implemented"
    }

    public int getAppliedDiscount(Book book, int discountRate) {
        int price = book.getPrice();
        return price - (price * discountRate / 100);
    }
}

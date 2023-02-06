package com.etech.mockito.spies;

import com.etech.mockito.stubbing.BookRepository;

public class BookService {


    public Book findBook(String bookId) {
        // Code to bring book from database
        //throw new RuntimeException("Method not implemented");
        if(true)
            throw new RuntimeException("Method not implemented...");
        System.err.println("Book service findBook method una girdi.");
        return null;
        //return null; // "Method not implemented"
    }

    public int getAppliedDiscount(Book book, int discountRate) {
        System.err.println("Book service getAppliedDiscount method una girdi.");
        int price = book.getPrice();
        return price - (price * discountRate / 100);
    }
}

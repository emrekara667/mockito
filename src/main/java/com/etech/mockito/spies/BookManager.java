package com.etech.mockito.spies;

public class BookManager {

    private BookService bookService;

    public BookManager(BookService bookService) {
        this.bookService = bookService;
    }

    public int applyDiscountOnBook(String bookId, int discountRate) {
        Book book = bookService.findBook(bookId); // We need to mock
        return bookService.getAppliedDiscount(book, discountRate); // we need to actually call
    }
}

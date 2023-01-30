package com.etech.mockito.stubbing;

import java.util.List;

public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<Book> getNewBooksWithAppliedDiscount(int discountRate, int days) {
        List<Book> newBooks = bookRepository.findNewBooks(days);
        // 500 apply 10% -> 10 % 500 -> 50 -> 500 - 50 -> 450

        for (Book temp : newBooks) {
            int price = temp.getPrice();
            int newPrice = price - (discountRate * price / 100);
            temp.setPrice(newPrice);
        }


        return newBooks;
    }

    public int calculateTotalCost(List<String> bookIds) {
        int total = 0;
        for (String bookId : bookIds) {
            Book book = bookRepository.findBookByBookId(bookId);
            total += book.getPrice();
        }
        return total;
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public void addBook(BookRequest bookRequest) {
        if(500 >= bookRequest.getPrice()) return;
        Book book = new Book(null,
                bookRequest.getTitle(),
                bookRequest.getPrice(),
                bookRequest.getPublishedDate());
        bookRepository.save(book);
    }
}

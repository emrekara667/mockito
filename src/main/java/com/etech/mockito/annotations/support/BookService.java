package com.etech.mockito.annotations.support;

import java.util.List;

public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<Book>  getNewBooksWithAppliedDiscount(int discountRate, int days){
        List<Book> newBooks = bookRepository.findNewBooks(days);
        // 500 apply 10% -> 10 % 500 -> 50 -> 500 - 50 -> 450

        for(Book temp: newBooks){
            int price = temp.getPrice();
            int newPrice = price -  (discountRate * price / 100);
            temp.setPrice(newPrice);
        }


        return newBooks;
    }

}

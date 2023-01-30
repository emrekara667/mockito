package com.etech.mockito.behaviour.verification;

public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(Book book) {
        if (book.getPrice() <= 500)
            return;
        bookRepository.save(book);
    }

    public void addBook(BookRequest bookRequest) {
        if (500 >= bookRequest.getPrice()) return;
        Book book = new Book(null,
                bookRequest.getTitle(),
                bookRequest.getPrice(),
                bookRequest.getPublishedDate());
        bookRepository.save(book);
    }

    public void updatePrice(String bookId, int updatedPrice) {
        if (bookId == null) return;
        Book book = bookRepository.findBookById(bookId);
       // if (book.getPrice() == updatedPrice) return;
        book.setPrice(updatedPrice);
        bookRepository.save(book);
    }
}

package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public void addNewBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new IllegalStateException("Book with id " + bookId + " does not exist");
        }
        bookRepository.deleteById(bookId);
    }

    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalStateException("Book with id " + bookId + " does not exist"));
    }

    public void updateBook(Long bookId, Book book) {
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalStateException("Book with id " + bookId + " does not exist"));


        if (book.getTitle() != null && !book.getTitle().isEmpty()) {
            existingBook.setTitle(book.getTitle());
        }
        if (book.getAuthor() != null && !book.getAuthor().isEmpty()) {
            existingBook.setAuthor(book.getAuthor());
        }
        if (book.getPublishedDate() != null) {
            existingBook.setPublishedDate(book.getPublishedDate());
        }
        if (book.getAgeRating() > 0) {
            existingBook.setAgeRating(book.getAgeRating());
        }


        bookRepository.save(existingBook);
    }


}


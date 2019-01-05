package com.example.demo.service;

import com.example.demo.domain.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void createBook(Book book) {
        bookRepository.save(book);
    }

    public void updateBook(Book book, Integer id) {
        Book lclBook = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        lclBook.setAuthor(book.getAuthor());
        lclBook.setEditor(book.getEditor());
        lclBook.setGenre(book.getGenre());
        lclBook.setTitle(book.getTitle());
        lclBook.setBorrowings(book.getBorrowings());
        bookRepository.save(lclBook);
    }

    public void deleteBook(int id) {
        bookRepository.delete(bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found")));
    }

    public Book loadBook(int id) {
        return bookRepository.findById(id).get();
    }

    public List<Book> loadBooks() {
        return bookRepository.findAll();
    }


}

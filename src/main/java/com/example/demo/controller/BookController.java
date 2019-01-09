package com.example.demo.controller;

import com.example.demo.domain.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> findAllBooks() {
        return bookService.loadBooks();
    }

    @GetMapping("/report/{id}")
    public void report(@PathVariable int id) throws IOException {
        bookService.reportFiles(id);
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable Integer id) {
        return bookService.loadBook(id);
    }

    @GetMapping("/genre/{g}")
    public List<Book> findByGenre(@PathVariable String g) {
        return bookService.loadBookByGenre(g);
    }

    @GetMapping("/title/{t}")
    public List<Book> findByTitle(@PathVariable String t) { return bookService.loadBookByTitle(t); }

    @GetMapping("/author/{a}")
    public List<Book> findByAuthor(@PathVariable String a) {
        return bookService.loadBookByAuthor(a);
    }

//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public void addBook(@RequestBody Book book) {
        bookService.createBook(book);
    }

    @PutMapping("/{id}")
    public void updateBook(@Valid @RequestBody Book book, @PathVariable Integer id) {
        bookService.updateBook(book, id);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
    }

}
package com.example.demo.service;

import com.example.demo.domain.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

    public void sendEmail(){

    }

    public void reportFiles(int id) throws IOException {
        Book book = bookRepository.findById(id).orElseThrow( () -> new RuntimeException("nop"));

        FileWriter fileWriter = new FileWriter("book_" + book.getId() + "_report.txt");
        fileWriter.write("Book " + book.getId());
        fileWriter.write(System.lineSeparator());
        fileWriter.write("Titlul cartii: " + book.getTitle());
        fileWriter.write(System.lineSeparator());
        fileWriter.write( "autorul cartii: " + book.getAuthor());
        fileWriter.write(System.lineSeparator());
        fileWriter.write("genul cartii: " + book.getGenre());
        fileWriter.write(System.lineSeparator());
        fileWriter.write(System.lineSeparator());

        book.getBorrowings().forEach( borrowing ->
                {
                    try {

                        fileWriter.write("Return status: " + borrowing.isReturned());
                        fileWriter.write(System.lineSeparator());
                        fileWriter.write("Borrowed by: {" + borrowing.getClient().toString());
                        fileWriter.write(System.lineSeparator());
                        fileWriter.write("Start Date: " + borrowing.getStartDate());
                        fileWriter.write(System.lineSeparator());
                        fileWriter.write("End Date: " + borrowing.getEndDate());
                        fileWriter.write(System.lineSeparator());
                        fileWriter.write(System.lineSeparator());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );

        fileWriter.close();

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

    public List<Book> loadBookByTitle(String title){
        return bookRepository.findBookByTitle(title);
    }

    public List<Book> loadBookByGenre(String genre){
        return bookRepository.findBookByGenre(genre);
    }

    public List<Book> loadBookByAuthor(String author){
        return bookRepository.findBookByAuthor(author);
    }


    public List<Book> loadBooks() {
        return bookRepository.findAll();
    }


}

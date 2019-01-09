package com.example.demo.service;

import com.example.demo.controller.BookController;
import com.example.demo.domain.Book;
import com.example.demo.domain.Borrowing;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BorrowingRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class BorrowingService {

    private final BorrowingRepository borrowingRepository;

    private final MailService mailService;

    public BorrowingService(BorrowingRepository borrowingRepository) {
        this.borrowingRepository = borrowingRepository;
        mailService = new MailService("user", "pass");
    }


    public void createBorrowing(Borrowing borrowing){
        borrowingRepository.save(borrowing);
    }

    public void returnBook(int id){
        Borrowing lclBorrowing = borrowingRepository.findById(id).orElseThrow(() -> new RuntimeException("Borrowing not found"));
        lclBorrowing.setReturned(1);
        borrowingRepository.save(lclBorrowing);
    }



    public Borrowing loadBorrowing(int id) {
        return borrowingRepository.findById(id).get();
    }

    public List<Borrowing> loadBorrowings() {
        return borrowingRepository.findAll();
    }

    public void sendEmail(){
        List<Borrowing> borrowings = loadBorrowings();

        borrowings.forEach(borrowing -> {
            if(borrowing.getEndDate().compareTo(new Date(System.currentTimeMillis())) <= 0){
                mailService.sendMail(borrowing.getClient().getEmail(), "Your borrowing is due", borrowing.getBook().getTitle());
            }
        });

    }
}

package com.example.demo.controller;

import com.example.demo.domain.Borrowing;
import com.example.demo.service.BorrowingService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/borrowings")
public class BorrowingController {
    private final BorrowingService borrowingService;

    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @GetMapping
    public List<Borrowing> findAllBooks() {
        return borrowingService.loadBorrowings();
    }

    @GetMapping("/{id}")
    public Borrowing findById(@PathVariable Integer id) {
        return borrowingService.loadBorrowing(id);
    }

//    @PostMapping
    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public void addBorrowing(@RequestBody Borrowing borrowing) {
        borrowing.setId(0);
        borrowingService.createBorrowing(borrowing);
    }

    @PutMapping("/{id}")
    public void returnBook(@Valid @PathVariable Integer id){
        borrowingService.returnBook(id);
    }

    @PostMapping("/report/{id}")
    public void getReport(@Valid @PathVariable Integer id){
      // borrowingService.reportFiles();
    }

    @GetMapping("/email")
    public void sendEmail(){
       // List<Borrowing> borrowings = borrowingService.loadBorrowings();
        borrowingService.sendEmail();
    }

}

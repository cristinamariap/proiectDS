package com.example.demo.service;

import com.example.demo.domain.Borrowing;
import com.example.demo.repository.BorrowingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowingService {

    private final BorrowingRepository borrowingRepository;

    public BorrowingService(BorrowingRepository borrowingRepository) {
        this.borrowingRepository = borrowingRepository;
    }

    public void createBorrowing(Borrowing borrowing){
        borrowingRepository.save(borrowing);
    }

    public void returnBook(int id){
        Borrowing lclBorrowing = borrowingRepository.findById(id).orElseThrow(() -> new RuntimeException("Borrowing not found"));
        lclBorrowing.setReturned(1);
        borrowingRepository.save(lclBorrowing);
    }

    public void reportFiles(){

//        JFileChooser chooser = new JFileChooser(  );
//        chooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
//        chooser.showSaveDialog( null );
//        System.out.println( chooser.getSelectedFile() );

    }

    public Borrowing loadBorrowing(int id) {
        return borrowingRepository.findById(id).get();
    }

    public List<Borrowing> loadBorrowings() {
        return borrowingRepository.findAll();
    }
}

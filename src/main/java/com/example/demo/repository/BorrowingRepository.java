package com.example.demo.repository;

import com.example.demo.domain.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Integer> {

//    Book findBookByAuthor(String author);

}

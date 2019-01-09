package com.example.demo.repository;

import com.example.demo.domain.FavList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavListRepository extends JpaRepository<FavList,Integer> {

    //Client findClientByUsername(String username);
    List<FavList> getFavListByClientId(int clientId);

    Optional<FavList> findByClientIdAndBookId(Integer clientId, Integer bookId);
}

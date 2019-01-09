package com.example.demo.service;

import com.example.demo.domain.FavList;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.FavListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavListService {

    private final FavListRepository favListRepository;

    @Autowired
    public FavListService(FavListRepository favListRepository) {
        this.favListRepository = favListRepository;
    }

    public List<FavList> getFavList(int clientId){
        return favListRepository.getFavListByClientId(clientId);
    }

    public void addToFavList(FavList favList){
       // FavList favList = new FavList(clientId, bookId);
        favListRepository.save(favList);
    }

    public void deleteFromFavList(FavList favList){
        FavList favListToBeDeleted = favListRepository.findByClientIdAndBookId(favList.getClientId(), favList.getBookId())
                .orElseThrow(() -> new RuntimeException("No such client has that book favorited."));
        favListRepository.delete(favListToBeDeleted);
    }
}
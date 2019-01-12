package com.example.demo.controller;

import com.example.demo.domain.*;
import com.example.demo.service.ClientService;
import com.example.demo.service.FavListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;
    private final FavListService favListService;

    @Autowired
    public ClientController(ClientService clientService, FavListService favListService) {
        this.clientService = clientService;
        this.favListService = favListService;
    }

    @GetMapping
    public List<Client> findAllClients() {
        return clientService.loadClient();
    }

    @GetMapping("/{id}")
    public Client findById(@PathVariable Integer id) {
        return clientService.readClient(id);
    }

    //    @PostMapping
    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public void addClient(@RequestBody Client client) {
        //client.setId(0);
        clientService.addClient(client);
    }

    @PutMapping("/{id}")
    public void updateClient(@Valid @RequestBody Client client, @PathVariable Integer id) {

        clientService.updateClient(client, id);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Integer id) {
        clientService.deleteClient(id);
    }

    @GetMapping("/favList/{id}")
    public List<FavList> getFavList(@Valid @PathVariable Integer id) {
        return favListService.getFavList(id);
    }

    @PostMapping("/favList")
    public void addToFavList(@RequestBody AddFavDTO addFavDTO) {
        FavList fList = new FavList(addFavDTO.getClientId(), addFavDTO.getBookId());
        favListService.addToFavList(fList);
    }

    @DeleteMapping("/favList")
    public void deleteFromFavList(@RequestBody AddFavDTO deleteFavDTO){
        FavList fList = new FavList(deleteFavDTO.getClientId(), deleteFavDTO.getBookId());
        favListService.deleteFromFavList(fList);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
        if(clientService.login(loginDTO)) {
            return ok().body(loginDTO.getEmail());
        }
        return badRequest().build();
    }
}

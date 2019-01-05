package com.example.demo.controller;

import com.example.demo.domain.Client;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> findAllClients() {
        return clientService.loadClient();
    }

    @GetMapping("/{id}")
    public Client findById(@PathVariable Integer id) {
        return clientService.readClient(id);
    }

    @PostMapping
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

}

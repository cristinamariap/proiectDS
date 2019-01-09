package com.example.demo.service;

import com.example.demo.domain.Client;
import com.example.demo.domain.LoginDTO;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void updateClient(Client client, Integer id){
        Client lclClient = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
        lclClient.setAddress(client.getAddress());
        lclClient.setPassword(client.getPassword());
        lclClient.setBorrowings(client.getBorrowings());
        lclClient.setEmail(client.getEmail());
        lclClient.setName(client.getName());
        lclClient.setPhone(client.getPhone());
        lclClient.setUsername(client.getUsername());
        clientRepository.save(lclClient);
    }

    public void deleteClient(int id){
        clientRepository.delete(clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found")));
    }

    public Client readClient(int id){
        return clientRepository.findById(id).get();
    }

    public List<Client> loadClient(){
        return clientRepository.findAll();
    }

    public Client getClient(String username){ return clientRepository.findClientByUsername(username);}

    public void addClient(Client client) {
        clientRepository.save(client);
    }

    public boolean login(LoginDTO loginDTO) {
        Client client = clientRepository.findByEmail(loginDTO.getEmail())
            .orElseThrow( () -> new RuntimeException("Username not found"));

        if(client.getPassword().equals(loginDTO.getPassword())) {
            return true;
        }
        return false;
    }

}

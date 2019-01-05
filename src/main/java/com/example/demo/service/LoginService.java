package com.example.demo.service;


import com.example.demo.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private String adminUsername = "cristina";
    private String adminPassword = " ";

    private final ClientRepository clientRepository;

    public LoginService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public boolean isLogin(String user, String password, String option  ){

        if (option.equals("Admin")){
            return adminUsername.equals(user) && adminPassword.equals(password);
        } else if(option.equals("Client")){
            return clientRepository.findAll().stream().anyMatch(client -> client.getUsername().equals(user) && client.getPassword().equals(password));
        }
        return false;
    }



}

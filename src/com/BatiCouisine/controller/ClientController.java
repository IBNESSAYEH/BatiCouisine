package com.BatiCouisine.controller;

import com.BatiCouisine.entities.Client;
import com.BatiCouisine.service.ClientService;

public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    public void store(Client client) {
        clientService.store(client);
    }

    public void findById(int id) {
        clientService.findById(id);
    }

    public void update(int id, Client client) {
        clientService.update(id, client);
    }

    public void destroy(int id) {
        clientService.destroy(id);
    }

    public void findAll() {
        clientService.findAll();
    }
}

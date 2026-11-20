package com.booking.controller;

import com.booking.entity.Client;
import com.booking.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientRestController {

    @Autowired private ClientService clientService;

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Integer id) {
        Client client = clientService.getClientById(id);
        return client != null ? ResponseEntity.ok(client) : ResponseEntity.notFound().build();
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        client.setId(null);
        Client saved = clientService.saveClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Integer id, @RequestBody Client client) {
        Client existing = clientService.getClientById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        existing.setName(client.getName());
        existing.setPhone(client.getPhone());
        existing.setEmail(client.getEmail());
        clientService.saveClient(existing);
        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {
        if (clientService.getClientById(id) == null) return ResponseEntity.notFound().build();
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}

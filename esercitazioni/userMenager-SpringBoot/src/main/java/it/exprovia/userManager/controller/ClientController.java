package it.exprovia.userManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.exprovia.userManager.model.dto.ClientDTO;
import it.exprovia.userManager.service.impl.ClientServiceImpl;

@RestController
public class ClientController {

    @Autowired
    private ClientServiceImpl service;

    //GET /clients
    @GetMapping("/clients")
    public ResponseEntity<List<ClientDTO>> getUsers(){
        
        List<ClientDTO> clients = service.getClients();
        
        if (clients != null && !clients.isEmpty()) {
            return ResponseEntity.ok().body(clients);
        } else {
            return ResponseEntity.noContent().build();
        }

    }

    //GET /client/{id}
    @GetMapping("/client/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable Long id) {
        
        ClientDTO client = service.getClient(id);
        
        if (client != null) {
            return ResponseEntity.ok().body(client);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //POST /client
    @PostMapping("/client")
    public ResponseEntity<ClientDTO> createUser(@RequestBody ClientDTO clientDTO) {
        if (clientDTO == null || clientDTO.name() == null || clientDTO.email() == null || clientDTO.pwd() == null) {
            return ResponseEntity.badRequest().build();
        }

        ClientDTO created = service.createClient(clientDTO);
        
        if ( created != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //PUT /client/{id}
    @PutMapping("/client/{id}")
    public ResponseEntity<ClientDTO> updateUser(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        if (clientDTO == null || clientDTO.name() == null || clientDTO.email() == null || clientDTO.pwd() == null) {
            return ResponseEntity.badRequest().build();
        }

        ClientDTO updateDTO = service.updateClient(id, clientDTO);
        
        if (updateDTO != null) {
            return ResponseEntity.ok().body(updateDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //DELETE /client/{id}
    @DeleteMapping("/client/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id) {
        
        boolean deleted = service.deleteClient(id);
        
        if (deleted) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //POST /client/
    @PostMapping("/client/login")
    public ResponseEntity<String> login(@RequestBody ClientDTO clientDTO)
    {
        if (clientDTO == null || clientDTO.name() == null || clientDTO.pwd() == null) {
            return ResponseEntity.badRequest().build();
        }

        boolean response = service.login(clientDTO.name(), clientDTO.pwd());

        if(response == false){
            return ResponseEntity.status(401).body("Cliente Non Trovato");
        }

        return ResponseEntity.ok("Cliente Trovato, Login Effettuato");

    }

    
}

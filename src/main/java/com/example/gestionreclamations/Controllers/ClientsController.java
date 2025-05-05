package com.example.gestionreclamations.Controllers;

import com.example.gestionreclamations.model.Client;
import com.example.gestionreclamations.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientsController {

    @Autowired
    private ClientService service;

    @GetMapping
    public List<Client> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable("id") Long id) {
        Client client = service.getById(id);
        if (client == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Si client non trouvé
        }
        return ResponseEntity.ok(client); // 200 Si client trouvé
    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody Client c) {
        Client createdClient = service.save(c);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient); // 201 si créé avec succès
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build(); // 204 Si suppression réussie
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Si client non trouvé
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable("id") Long id, @RequestBody Client c) {
        Client updatedClient = service.update(id, c);
        if (updatedClient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Si client non trouvé
        }
        return ResponseEntity.ok(updatedClient); // 200 Si client mis à jour avec succès
    }
}
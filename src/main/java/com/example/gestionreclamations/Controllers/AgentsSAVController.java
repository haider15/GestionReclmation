package com.example.gestionreclamations.Controllers;

import com.example.gestionreclamations.model.AgentSAV;
import com.example.gestionreclamations.Services.AgentSAVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agents")
public class AgentsSAVController {

    @Autowired
    private AgentSAVService service;

    @GetMapping
    public List<AgentSAV> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgentSAV> getById(@PathVariable("id") Long id) {
        AgentSAV agent = service.getById(id);
        if (agent == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(agent);
    }
    @PostMapping
    public AgentSAV save(@RequestBody AgentSAV a) {
        return service.save(a);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    
    
    @PutMapping("/{id}")
    public ResponseEntity<AgentSAV> update(@PathVariable("id") Long id, @RequestBody AgentSAV a) {
        AgentSAV updatedAgent = service.update(id, a);
        if (updatedAgent == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Si l'agent n'est pas trouvé
        }
        return ResponseEntity.ok(updatedAgent); // Si l'agent a été mis à jour avec succès
    }
    
    @PostMapping("/login")
    public ResponseEntity<AgentSAV> login(@RequestBody AgentSAV loginRequest) {
        AgentSAV agent = service.login(loginRequest.getNom(), loginRequest.getMotDePasse());
        if (agent == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(agent);
    }



}

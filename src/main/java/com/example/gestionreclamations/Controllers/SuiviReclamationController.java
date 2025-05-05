package com.example.gestionreclamations.Controllers;

import com.example.gestionreclamations.model.SuiviReclamation;
import com.example.gestionreclamations.Services.SuiviReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suivis")
public class SuiviReclamationController {

    @Autowired
    private SuiviReclamationService service;

    @GetMapping
    public List<SuiviReclamation> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuiviReclamation> getById(@PathVariable("id") Long id) {
        SuiviReclamation suivi = service.getById(id);
        if (suivi == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Si suivi non trouvé
        }
        return ResponseEntity.ok(suivi); // 200 Si suivi trouvé
    }

    @PostMapping
    public ResponseEntity<SuiviReclamation> save(@RequestBody SuiviReclamation suivi) {
        SuiviReclamation createdSuivi = service.save(suivi);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSuivi); // 201 Si créé avec succès
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build(); // 204 Si suppression réussie
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Si suivi non trouvé
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuiviReclamation> update(@PathVariable("id") Long id, @RequestBody SuiviReclamation suivi) {
        SuiviReclamation updatedSuivi = service.update(id, suivi);
        if (updatedSuivi == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Si suivi non trouvé
        }
        return ResponseEntity.ok(updatedSuivi); // 200 Si suivi mis à jour avec succès
    }

    // Méthode pour ajouter un suivi à une réclamation
    @PostMapping("/{reclamationId}/suivi")
    public ResponseEntity<SuiviReclamation> ajouterSuivi(@PathVariable("reclamationId") Long reclamationId,
                                                         @RequestParam("agentId") Long agentId,
                                                         @RequestParam("message") String message,
                                                         @RequestParam("action") String action) {
        SuiviReclamation suivi = service.ajouterSuivi(reclamationId, agentId, message, action);
        if (suivi == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si réclamation ou agent non trouvé
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(suivi);  // 201 si suivi ajouté avec succès
    }
}

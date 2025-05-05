
package com.example.gestionreclamations.Controllers;

import com.example.gestionreclamations.model.Reclamation;
import com.example.gestionreclamations.Services.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;




@RestController
@RequestMapping("/api/reclamations")
public class ReclamationController {

    @Autowired
    private ReclamationService service;

    @GetMapping
    public List<Reclamation> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reclamation> getById(@PathVariable("id") Long id) {
        Reclamation reclamation = service.getById(id);
        if (reclamation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Si réclamation non trouvée
        }
        return ResponseEntity.ok(reclamation); // 200 Si réclamation trouvée
    }

    @PostMapping
    public ResponseEntity<Reclamation> save(@RequestBody Reclamation r) {
        Reclamation createdReclamation = service.save(r);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReclamation); // 201 Si créé avec succès
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build(); // 204 Si suppression réussie
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Si réclamation non trouvée
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reclamation> update(@PathVariable("id") Long id, @RequestBody Reclamation r) {
        Reclamation updatedReclamation = service.update(id, r);
        if (updatedReclamation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Si réclamation non trouvée
        }
        return ResponseEntity.ok(updatedReclamation); // 200 Si réclamation mise à jour avec succès
    }
    
    
    @PutMapping("/{reclamationId}/affecter-agent/{agentId}")
    public ResponseEntity<Reclamation> affecterAgent(@PathVariable("reclamationId") Long reclamationId, 
                                                     @PathVariable("agentId") Long agentId) {
        Reclamation updatedReclamation = service.affecterAgent(reclamationId, agentId);
        if (updatedReclamation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Si réclamation ou agent non trouvé
        }
        return ResponseEntity.ok(updatedReclamation); // 200 Si affectation réussie
    }
    
    @GetMapping("/rapport-satisfaction")
    public ResponseEntity<InputStreamResource> generateSatisfactionReport() {
        // Appeler le service pour obtenir le rapport PDF
        ByteArrayInputStream bis = service.generateSatisfactionReportPdf();

        // Ajouter les headers nécessaires pour l'envoi du PDF
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=rapport_satisfaction.pdf");

        // Retourner la réponse avec le PDF en tant que flux de données
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
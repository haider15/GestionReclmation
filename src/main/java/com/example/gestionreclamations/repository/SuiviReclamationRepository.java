package com.example.gestionreclamations.repository;
import com.example.gestionreclamations.model.SuiviReclamation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SuiviReclamationRepository extends JpaRepository<SuiviReclamation, Long> {

    // Méthode pour récupérer tous les suivis d'une réclamation
    List<SuiviReclamation> findByReclamationId(Long reclamationId);
}
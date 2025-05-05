package com.example.gestionreclamations.Services;
import com.example.gestionreclamations.model.AgentSAV;
import com.example.gestionreclamations.model.Reclamation;
import com.example.gestionreclamations.model.SuiviReclamation;
import com.example.gestionreclamations.repository.AgentSAVRepository;
import com.example.gestionreclamations.repository.ReclamationRepository;
import com.example.gestionreclamations.repository.SuiviReclamationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SuiviReclamationService {

    @Autowired
    private ReclamationRepository reclamationRepository;
  
    @Autowired
    private AgentSAVRepository agentSAVRepository;
  
    @Autowired
    private SuiviReclamationRepository repository;

    public List<SuiviReclamation> getAll() {
        return repository.findAll(); // Récupère tous les suivis de réclamation
    }

    public SuiviReclamation getById(Long id) {
        return repository.findById(id).orElse(null); // Récupère un suivi par son ID
    }

    public SuiviReclamation save(SuiviReclamation suivi) {
        return repository.save(suivi); // Sauvegarde un suivi de réclamation
    }

    public void delete(Long id) {
        repository.deleteById(id); // Supprime un suivi de réclamation par son ID
    }

    public SuiviReclamation update(Long id, SuiviReclamation suivi) {
        SuiviReclamation existingSuivi = repository.findById(id).orElse(null);
        if (existingSuivi == null) {
            return null; // Si le suivi n'existe pas, retourne null
        }

        // Met à jour les informations du suivi
        existingSuivi.setMessage(suivi.getMessage());
        existingSuivi.setAction(suivi.getAction());
        existingSuivi.setDate(suivi.getDate());
        existingSuivi.setReclamation(suivi.getReclamation());
        existingSuivi.setEmploye(suivi.getEmploye());

        // Sauvegarde le suivi mis à jour
        return repository.save(existingSuivi);
    }

    // Méthode pour ajouter un suivi à une réclamation
    public SuiviReclamation ajouterSuivi(Long reclamationId, Long agentId, String message, String action) {
        Reclamation reclamation = reclamationRepository.findById(reclamationId).orElse(null);
        AgentSAV agent = agentSAVRepository.findById(agentId).orElse(null);

        if (reclamation != null && agent != null) {
            SuiviReclamation suivi = new SuiviReclamation();
            suivi.setReclamation(reclamation);  // Lier la réclamation au suivi
            suivi.setEmploye(agent);  // Lier l'agent au suivi
            suivi.setMessage(message);  // Ajouter le message
            suivi.setAction(action);  // Ajouter l'action
            suivi.setDate(LocalDate.now());  // Date actuelle du suivi

            return repository.save(suivi);  // Sauvegarder le suivi
        }
        return null;  // Si la réclamation ou l'agent n'est pas trouvé
    }

    public List<SuiviReclamation> getSuivisByReclamation(Long reclamationId) {
        return repository.findByReclamationId(reclamationId);  // Récupérer tous les suivis d'une réclamation
    }
}
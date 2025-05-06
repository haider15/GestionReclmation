package com.example.gestionreclamations.Services;

import com.example.gestionreclamations.model.AgentSAV;
import com.example.gestionreclamations.repository.AgentSAVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentSAVService {
    @Autowired private AgentSAVRepository repository;

    public List<AgentSAV> getAll() { return repository.findAll(); }
    public AgentSAV getById(Long id) { return repository.findById(id).orElse(null); }
    public AgentSAV save(AgentSAV a) { return repository.save(a); }
    public void delete(Long id) { repository.deleteById(id); }
    
    
    public AgentSAV update(Long id, AgentSAV a) {
        AgentSAV existingAgent = repository.findById(id).orElse(null);
        if (existingAgent == null) {
            return null;  // Si l'agent n'est pas trouvé, vous pouvez soit renvoyer null, soit lancer une exception
        }

        // Met à jour les champs de l'agent existant
        existingAgent.setNom(a.getNom());
        existingAgent.setCompetence(a.getCompetence());

        // Sauvegarde l'agent mis à jour dans la base de données
        return repository.save(existingAgent);
    }
    
    public AgentSAV login(String nom, String motDePasse) {
        return repository.findByNomAndMotDePasse(nom, motDePasse).orElse(null);
    }

}
package com.example.gestionreclamations.Services;
import com.example.gestionreclamations.model.Client;
import com.example.gestionreclamations.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    public List<Client> getAll() {
        return repository.findAll();
    }

    public Client getById(Long id) {
        return repository.findById(id).orElse(null); // Retourne null si le client n'est pas trouvé
    }

    public Client save(Client c) {
        return repository.save(c); // Sauvegarde un nouveau client
    }

    public void delete(Long id) {
        repository.deleteById(id); // Supprime un client par son ID
    }

    public Client update(Long id, Client c) {
        Client existingClient = repository.findById(id).orElse(null);
        if (existingClient == null) {
            return null; // Si le client n'existe pas, retourne null
        }

        // Met à jour les champs de l'existant avec ceux du client passé en paramètre
        existingClient.setNom(c.getNom());
        existingClient.setEmail(c.getEmail());
        existingClient.setTelephone(c.getTelephone());

        // Sauvegarde du client mis à jour
        return repository.save(existingClient);
    }
}
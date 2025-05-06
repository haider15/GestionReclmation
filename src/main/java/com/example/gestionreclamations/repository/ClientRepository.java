package com.example.gestionreclamations.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestionreclamations.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	Optional<Client> findByNomAndMotDePasse(String nom, String motDePasse);
}

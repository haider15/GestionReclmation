package com.example.gestionreclamations.repository;
import java.util.Optional;

import com.example.gestionreclamations.model.AgentSAV;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentSAVRepository extends JpaRepository<AgentSAV, Long> {
	
	Optional<AgentSAV> findByNomAndMotDePasse(String nom, String motDePasse);
}

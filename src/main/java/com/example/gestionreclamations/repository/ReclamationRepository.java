package com.example.gestionreclamations.repository;

import com.example.gestionreclamations.model.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {}

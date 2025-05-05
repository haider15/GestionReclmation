package com.example.gestionreclamations.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestionreclamations.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}

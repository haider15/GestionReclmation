package com.example.gestionreclamations.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String email;
    private String telephone;
    private String motDePasse;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getMotDePasse() {
	    return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
	    this.motDePasse = motDePasse;
	}
	@OneToMany(mappedBy = "client",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reclamation> reclamations = new ArrayList<>();
	
}
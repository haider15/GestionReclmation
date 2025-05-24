# Backend - Gestion des Réclamations

## Description du projet

Cette API REST backend, développée avec **Spring Boot** et **Java 21**, permet la gestion complète d’un système de réclamations clients, incluant :

- CRUD pour les entités : **Suivi de Réclamation**, **Agent SAV**, **Client**, **Réclamation**.  
- Affectation d’agents SAV aux réclamations.  
- Génération de rapports PDF de satisfaction.  
- Persistance des données avec **MySQL**.  
- Conteneurisation via **Docker** et orchestration avec **Docker Compose**.

---

## Technologies utilisées

- **Java 21**  
- **Spring Boot**  
- **MySQL**  
- **Hibernate (JPA)**  
- **Bibliothèque PDF (ex : iText)**  
- **Docker**  
- **Docker Compose**

---

## Prérequis

- Java 21  
- Docker & Docker Compose  
- Maven (si exécution sans Docker)

---

## Installation et exécution

### 1. Cloner le dépôt

```bash
git clone https://github.com/ton-utilisateur/gestion-reclamations-backend.git
cd gestion-reclamations-backend

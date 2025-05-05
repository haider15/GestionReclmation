package com.example.gestionreclamations.Services;

import com.example.gestionreclamations.model.AgentSAV;
import com.example.gestionreclamations.model.Reclamation;
import com.example.gestionreclamations.repository.AgentSAVRepository;
import com.example.gestionreclamations.repository.ReclamationRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ReclamationService {
    @Autowired
    private ReclamationRepository repository;

    @Autowired
    private AgentSAVRepository agentSAVRepository;

    public List<Reclamation> getAll() {
        return repository.findAll();
    }

    public Reclamation getById(Long id) {
        return repository.findById(id).orElse(null); // Retourne null si la réclamation n'est pas trouvée
    }

    public Reclamation save(Reclamation r) {
        return repository.save(r); // Sauvegarde la réclamation
    }

    public void delete(Long id) {
        repository.deleteById(id); // Supprime la réclamation par son ID
    }

    public Reclamation update(Long id, Reclamation r) {
        Reclamation existingReclamation = repository.findById(id).orElse(null);
        if (existingReclamation == null) {
            return null; // Si la réclamation n'existe pas, retourne null
        }

        // Met à jour les informations de la réclamation existante
        existingReclamation.setClient(r.getClient());
        existingReclamation.setStatut(r.getStatut());
        existingReclamation.setDescription(r.getDescription());
        existingReclamation.setDate(r.getDate());
        existingReclamation.setNote(r.getNote());
        existingReclamation.setProduit(r.getProduit());

        // Sauvegarde de la réclamation mise à jour
        return repository.save(existingReclamation);
    }
    
    public Reclamation affecterAgent(Long reclamationId, Long agentId) {
        // Rechercher la réclamation par ID
        Reclamation reclamation = repository.findById(reclamationId).orElse(null);
        if (reclamation == null) {
            return null; // Si la réclamation n'existe pas
        }

        // Rechercher l'agent par ID
        AgentSAV agent = agentSAVRepository.findById(agentId).orElse(null);
        if (agent == null) {
            return null; // Si l'agent n'existe pas
        }

        // Affecter l'agent à la réclamation
        // Vous pouvez créer une relation bidirectionnelle entre AgentSAV et Reclamation si nécessaire
        // Par exemple : reclamation.setAgentSAV(agent);
        
        // Sauvegarder la réclamation mise à jour
        return repository.save(reclamation);
    }
    
    
    //////////////////////////////
    public ByteArrayInputStream generateSatisfactionReportPdf() {
        List<Reclamation> reclamations = repository.findAll();  // Récupère toutes les réclamations
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font fontBody = FontFactory.getFont(FontFactory.HELVETICA, 12);

            document.add(new Paragraph("Rapport de Satisfaction", fontTitle));
            document.add(new Paragraph(" ", fontBody));

            // Calculer la moyenne des notes de satisfaction
            double moyenne = reclamations.stream()
                    .mapToInt(Reclamation::getNote)  // Récupère les notes
                    .average()  // Calcule la moyenne
                    .orElse(0.0);  // Si aucune réclamation, moyenne = 0

            // Ajouter la moyenne au rapport
            document.add(new Paragraph("Moyenne des notes : " + String.format("%.2f", moyenne), fontBody));
            document.add(new Paragraph("Nombre total de réclamations : " + reclamations.size(), fontBody));
            document.add(new Paragraph("-------------------------------------------------------------"));

            // Ajouter chaque réclamation et sa note dans le rapport
            for (Reclamation r : reclamations) {
                document.add(new Paragraph("ID : " + r.getId(), fontBody));
                document.add(new Paragraph("Description : " + r.getDescription(), fontBody));
                document.add(new Paragraph("Statut : " + r.getStatut(), fontBody));
                document.add(new Paragraph("Note : " + r.getNote(), fontBody));  // Afficher la note de chaque réclamation
                document.add(new Paragraph("-------------------------------------------------------------"));
            }

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
    
    
    
   
}
package com.ensah.core.services.impl;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensah.core.bo.Surveillance;
import com.ensah.core.bo.Salle;
import com.ensah.core.bo.Enseignant;
import com.ensah.core.bo.Examen;
import com.ensah.core.bo.CadreAdministrateur;
import com.ensah.core.dao.IPersonneRepository;
import com.ensah.core.dao.ISalleRepository;
import com.ensah.core.dao.ISurveillanceRepository;
import com.ensah.core.services.IPersonneService;
import com.ensah.core.services.ISalleService;
import com.ensah.core.services.ISurveillanceService;

@Service
@Transactional
public class SurveillanceServiceImpl implements ISurveillanceService {

    @Autowired
    private ISurveillanceRepository surveillanceDao;
    
    @Autowired
    private ISalleRepository salleDao;
    @Autowired
    private IPersonneService personneService;
    
    @Autowired
    private ISalleService salleService;
    
    @Autowired
    private ISurveillanceRepository surveillanceRepository;


   /** @Override
    public void addSurveillance(Surveillance surveillance) {
        surveillanceDao.save(surveillance);
    }*/
    
    @Override
    public void addSurveillance(Surveillance surveillance) {
        Long enseignantId = surveillance.getProf().getIdPersonne();
        
        // Vérifier si une surveillance avec le même enseignant existe déjà
        List<Surveillance> existingSurveillances = surveillanceDao.findByProfIdPersonne(enseignantId);
        
        // Si des surveillances existent déjà, mettre à jour la première surveillance trouvée
        if (!existingSurveillances.isEmpty()) {
            Surveillance existingSurveillance = existingSurveillances.get(0); // Prendre la première surveillance trouvée par exemple
            existingSurveillance.setDate(surveillance.getDate()); // Mettre à jour la date par exemple
            surveillanceDao.save(existingSurveillance); // Enregistrer la surveillance mise à jour
        } else {
            // Si aucune surveillance n'existe, insérer la nouvelle surveillance dans la table Surveillance
            surveillanceDao.save(surveillance);
        }
    }
    
    @Override
    public List<Surveillance> getSurveillanceByEnseignantId(Long enseignantId) {
        return surveillanceRepository.findByProfIdPersonne(enseignantId);
    }




    @Override
    public void deleteSurveillance(Long id) {
        surveillanceDao.deleteById(id);
    }
    
    @Override
    public Surveillance getSurveillanceBySalleId(Long salleId) {
        // Récupérer la salle à partir de l'identifiant
        Salle salle = salleDao.findById(salleId).orElse(null);
        
        if (salle != null) {
            // Définir la date de début et de fin pour la recherche (par exemple, aujourd'hui)
            LocalDateTime dateDebut = LocalDateTime.now();
            LocalDateTime dateFin = dateDebut.plusDays(1); // Recherche sur une journée
            
            // Rechercher les surveillances par salle et plage de dates
            List<Surveillance> surveillances = surveillanceDao.findBySalleAndDateBetween(salle, dateDebut, dateFin);
            
            // Si des surveillances sont trouvées, retourner la première surveillance
            if (!surveillances.isEmpty()) {
                return surveillances.get(0); // Vous pouvez retourner la première surveillance ou appliquer une logique personnalisée ici
            }
        }
        
        return null; // Retourner null si aucune surveillance n'est associée à la salle donnée
    }
    
    
    /**@Override
    public Surveillance getSurveillanceByEnseignantId(Long enseignantId) {
        // Recherchez la surveillance associée à l'enseignant ayant l'ID spécifié
        // Je vais supposer que vous avez une méthode dans votre repository pour cela
        List<Surveillance> surveillances = surveillanceDao.findByProfIdPersonne(enseignantId);

        // Vérifiez si des surveillances ont été trouvées
        if (!surveillances.isEmpty()) {
            // Retournez la première surveillance trouvée
            return surveillances.get(0);
        } else {
            // Si aucune surveillance n'est trouvée, retournez null
            return null;
        }
    }*/
    
    
    @Override
    public List<Surveillance> getSurveillanceByEnseignantIdAndDate(Long enseignantId, LocalDateTime localDateTime) {
        // Convertir LocalDateTime en Date
        Date date = (Date) Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        
        // Appeler la méthode du repository avec la date convertie
        return surveillanceDao.findByProfIdPersonneAndDate(enseignantId, date);
    }

    
    
    
   
    
    @Override
    public void deleteUnassignedTeachers(Long examenId, List<Long> enseignantIds) {
        // Supprimer les enseignants non associés à l'examen
        List<Surveillance> surveillances = surveillanceDao.findByExamenId(examenId);
        for (Surveillance surveillance : surveillances) {
            Long enseignantId = surveillance.getProf().getIdPersonne(); 
            if (!enseignantIds.contains(enseignantId)) {
                surveillanceDao.delete(surveillance);
            }
        }
    }



    
    
    
    

    
    
    @Override
    public void assignSurveillance(Examen examen, List<Long> enseignantIds, List<Long> salleIds) {
        // Implement logic to assign surveillance
        for (Long enseignantId : enseignantIds) {
            Enseignant enseignant = (Enseignant) personneService.getPersonneById(enseignantId);
            if (enseignant != null) {
                enseignant.setDisponible(false); // Mark enseignant as unavailable
                personneService.updatePersonne(enseignant); // Update in the database
            }
        }

        for (Long salleId : salleIds) {
            Salle salle = salleService.getSalleById(salleId);
            if (salle != null) {
                salle.setDisponible(false); // Mark salle as unavailable
                salleService.updateSalle(salle); // Update in the database
            }
        }
    }
    
    
    /**@Override
    public void updateSurveillance(Surveillance surveillance) {
        // Implémentez la logique pour mettre à jour la surveillance
        surveillanceDao.save(surveillance);
    }*/
    @Override
    public void updateSurveillance(Examen examen, List<Long> enseignantIds, List<Long> salleIds) {
        // Implement logic to update surveillance
        for (Long enseignantId : enseignantIds) {
            Enseignant enseignant = (Enseignant) personneService.getPersonneById(enseignantId);
            if (enseignant != null) {
                enseignant.setDisponible(true); // Mark enseignant as available
                personneService.updatePersonne(enseignant); // Update in the database
            }
        }

        for (Long salleId : salleIds) {
            Salle salle = salleService.getSalleById(salleId);
            if (salle != null) {
                salle.setDisponible(true); // Mark salle as available
                salleService.updateSalle(salle); // Update in the database
            }
        }
    }
    
    @Override
    public void updateSurveillance(Surveillance surveillance) {
        Surveillance existingSurveillance = surveillanceDao.findById(surveillance.getId()).orElseThrow(() -> new UnsupportedOperationException("Surveillance not found"));
        existingSurveillance.setDate(surveillance.getDate());
        existingSurveillance.setProf(surveillance.getProf());
        existingSurveillance.setSalle(surveillance.getSalle());
        surveillanceDao.save(existingSurveillance);
    }



    @Override
    public List<Surveillance> getAllSurveillances() {
        return surveillanceDao.findAll();
    }

    @Override
    public Surveillance getSurveillanceById(Long id) {
        return surveillanceDao.findById(id).orElse(null);
    }

    @Override
    public List<Surveillance> findBySalleAndDateBetween(Salle salle, LocalDateTime dateDebut, LocalDateTime dateFin) {
        return surveillanceDao.findBySalleAndDateBetween(salle, dateDebut, dateFin);
    }
    
    @Override
    public List<Surveillance> findByProfAndDateBetween(Enseignant prof, LocalDateTime dateDebut, LocalDateTime dateFin) {
        return surveillanceDao.findByProfAndDateBetween(prof, dateDebut, dateFin);
    }

    @Override
    public List<Surveillance> findByAdminAndDateBetween(CadreAdministrateur admin, LocalDateTime dateDebut, LocalDateTime dateFin) {
        return surveillanceDao.findByAdminAndDateBetween(admin, dateDebut, dateFin);
    }
    
    
    

}

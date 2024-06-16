package com.ensah.core.services;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import com.ensah.core.bo.Surveillance;
import com.ensah.core.bo.Salle;
import com.ensah.core.bo.Enseignant;
import com.ensah.core.bo.Examen;
import com.ensah.core.bo.CadreAdministrateur;

public interface ISurveillanceService {
    void addSurveillance(Surveillance surveillance);
    void deleteSurveillance(Long id);
    List<Surveillance> getAllSurveillances();
    Surveillance getSurveillanceById(Long id);
    List<Surveillance> findBySalleAndDateBetween(Salle salle, LocalDateTime dateDebut, LocalDateTime dateFin);
    List<Surveillance> findByProfAndDateBetween(Enseignant prof, LocalDateTime dateDebut, LocalDateTime dateFin);
    List<Surveillance> findByAdminAndDateBetween(CadreAdministrateur admin, LocalDateTime dateDebut, LocalDateTime dateFin);
    Surveillance getSurveillanceBySalleId(Long salleId); // Ajout de la méthode
    
    void assignSurveillance(Examen examen, List<Long> enseignantIds, List<Long> salleIds);
    void updateSurveillance(Examen examen, List<Long> enseignantIds, List<Long> salleIds);
    
    List<Surveillance>  getSurveillanceByEnseignantId(Long enseignantId);
    
    public void deleteUnassignedTeachers(Long examenId, List<Long> enseignantIds);
    
    List<Surveillance> getSurveillanceByEnseignantIdAndDate(Long enseignantId, LocalDateTime localDateTime);
    
    void updateSurveillance(Surveillance surveillance);
    
    
   
    
    
}



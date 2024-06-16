package com.ensah.core.dao;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensah.core.bo.Surveillance;
import com.ensah.core.bo.Salle;
import com.ensah.core.bo.Enseignant;
import com.ensah.core.bo.CadreAdministrateur;

@Repository
public interface ISurveillanceRepository extends JpaRepository<Surveillance, Long> {
    List<Surveillance> findBySalleAndDateBetween(Salle salle, LocalDateTime dateDebut, LocalDateTime dateFin);
    List<Surveillance> findByProfAndDateBetween(Enseignant prof, LocalDateTime dateDebut, LocalDateTime dateFin);
    List<Surveillance> findByAdminAndDateBetween(CadreAdministrateur admin, LocalDateTime dateDebut, LocalDateTime dateFin);
    List<Surveillance> findByExamenId(Long examenId); 
    
   
    
    List<Surveillance> findByProfIdPersonne(Long idPersonne);
    List<Surveillance> findByProfIdPersonneAndDate(Long enseignantId, Date date);
    
   
}


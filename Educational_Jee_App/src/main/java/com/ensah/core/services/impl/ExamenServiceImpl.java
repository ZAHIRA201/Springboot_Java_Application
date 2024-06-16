package com.ensah.core.services.impl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensah.core.bo.Examen;
import com.ensah.core.bo.Salle;
import com.ensah.core.bo.Surveillance;
import com.ensah.core.bo.Enseignant; 
import com.ensah.core.bo.CadreAdministrateur;
import com.ensah.core.dao.IExamenRepository;
import com.ensah.core.dao.ISalleRepository;
import com.ensah.core.dao.ISurveillanceRepository;
import com.ensah.core.services.IExamenService;
import com.ensah.core.services.IPersonneService;
import com.ensah.core.services.ISurveillanceService;

@Service
@Transactional
public class ExamenServiceImpl implements IExamenService {

    @Autowired
    private IExamenRepository examenRepository;

    @Autowired
    private ISalleRepository salleRepository;

    @Autowired
    private ISurveillanceService surveillanceService;

    @Autowired
    private IPersonneService personneService;
    
    @Autowired
    private ISurveillanceRepository surveillanceDao;

    @Override
    public boolean creerExamen(Examen examen, List<Long> salleIds, List<Long> enseignantIds, Long adminId) {
        LocalDateTime dateDebut = LocalDateTime.of(examen.getDateExam().toLocalDate(), examen.getHeureDebut());
        int duree = examen.getDureePrevue();

        if (!areResourcesAvailable(salleIds, enseignantIds, adminId, dateDebut, duree)) {
            return false;
        }

        examenRepository.save(examen);

        assignSurveillancesToExam(examen, salleIds, enseignantIds, adminId);

        return true;
    }

    private boolean areResourcesAvailable(List<Long> salleIds, List<Long> enseignantIds, Long adminId, LocalDateTime dateDebut, int duree) {
        for (Long salleId : salleIds) {
            Salle salle = salleRepository.findById(salleId).orElse(null);
            if (salle == null || !isSalleDisponible(salle, dateDebut, duree)) {
                return false;
            }
        }

        for (Long enseignantId : enseignantIds) {
            Enseignant enseignant = (Enseignant) personneService.getPersonneById(enseignantId);
            if (enseignant == null || !isEnseignantDisponible(enseignant, dateDebut, duree)) {
                return false;
            }
        }

        if (adminId != null) {
            CadreAdministrateur admin = (CadreAdministrateur) personneService.getPersonneById(adminId);
            if (admin == null || !isAdminDisponible(admin, dateDebut, duree)) {
                return false;
            }
        }

        return true;
    }
    
    
    @Override
    public boolean isSalleAvailable(Long salleId, Examen examen) {
        List<Examen> examens = examenRepository.findBySalleId(salleId);
        for (Examen existingExamen : examens) {
            if (!existingExamen.getId().equals(examen.getId()) &&
                existingExamen.getDate().equals(examen.getDate()) &&
                existingExamen.getHeureDebut().equals(examen.getHeureDebut())) {
                return false; // Salle is not available
            }
        }
        return true; // Salle is available
    }

    private boolean isSalleDisponible(Salle salle, LocalDateTime dateDebut, int duree) {
        LocalDateTime dateFin = dateDebut.plusMinutes(duree);
        List<Surveillance> surveillances = surveillanceService.findBySalleAndDateBetween(salle, dateDebut, dateFin);
        return surveillances.isEmpty();
    }

    private boolean isEnseignantDisponible(Enseignant enseignant, LocalDateTime dateDebut, int duree) {
        LocalDateTime dateFin = dateDebut.plusMinutes(duree);
        List<Surveillance> surveillances = surveillanceService.findByProfAndDateBetween(enseignant, dateDebut, dateFin);
        return surveillances.isEmpty();
    }

    private boolean isAdminDisponible(CadreAdministrateur admin, LocalDateTime dateDebut, int duree) {
        LocalDateTime dateFin = dateDebut.plusMinutes(duree);
        List<Surveillance> surveillances = surveillanceService.findByAdminAndDateBetween(admin, dateDebut, dateFin);
        return surveillances.isEmpty();
    }

    private void assignSurveillancesToExam(Examen examen, List<Long> salleIds, List<Long> enseignantIds, Long adminId) {
        CadreAdministrateur admin = null;
        if (adminId != null) {
            admin = (CadreAdministrateur) personneService.getPersonneById(adminId);
        }

        for (Long salleId : salleIds) {
            Salle salle = salleRepository.findById(salleId).orElse(null);
            for (Long enseignantId : enseignantIds) {
                Enseignant enseignant = (Enseignant) personneService.getPersonneById(enseignantId);
                Surveillance surveillance = new Surveillance();
                surveillance.setExamen(examen);
                surveillance.setSalle(salle);
                surveillance.setProf(enseignant);
                surveillance.setAdmin(admin);
                surveillanceService.addSurveillance(surveillance);
            }
        }
    }
    
    @Override
    public void removeSurveillanceFromExamen(Long surveillanceId) {
        // Implémentation de la suppression de la surveillance de l'examen
        // Supprimer la surveillance associée à l'examen
        Surveillance surveillance = surveillanceDao.findById(surveillanceId).orElse(null);
        if (surveillance != null) {
            Examen examen = surveillance.getExamen();
            if (examen != null) {
                examen.getSurveillances().remove(surveillance);
                surveillance.setExamen(null);
                surveillanceDao.save(surveillance);
            }
        }
    }

    @Override
    public void addExamen(Examen examen) {
        examenRepository.save(examen);
    }

    @Override
    public void deleteExamen(Long id) {
        examenRepository.deleteById(id);
    }

    @Override
    public List<Examen> getAllExamens() {
        return examenRepository.findAll();
    }

    @Override
    public Examen getExamenById(Long id) {
        return examenRepository.findById(id).orElse(null);
    }

    @Override
    public List<Examen> getExamenByDate(java.sql.Date dateExam) {
        return examenRepository.findByDateExam(dateExam);
    }

    @Override
    public List<Examen> getExamenByLocalTime(LocalTime heureDebut) {
        return examenRepository.findByHeureDebut(heureDebut);
    }
    
    @Override
    public void examenTermine(Long examenId) {
        Examen examen = getExamenById(examenId);
        LocalDateTime dateFinExamen = LocalDateTime.of(examen.getDateExam().toLocalDate(), examen.getHeureDebut())
                .plusMinutes(examen.getDureePrevue());
        LocalDateTime now = LocalDateTime.now();

        if (now.isAfter(dateFinExamen)) {
            libererRessources(examen);
        }
    }

    private void libererRessources(Examen examen) {
        if (examen.getSurveillances() != null) {
            for (Surveillance surveillance : examen.getSurveillances()) {
                updateResourceAvailability(surveillance);
            }
        }
    }

    private void updateResourceAvailability(Surveillance surveillance) {
        Salle salle = surveillance.getSalle();
        salle.setDisponible(true);
        salleRepository.save(salle);

        Enseignant enseignant = surveillance.getProf();
        enseignant.setDisponible(true);
        personneService.updatePersonne(enseignant);

        CadreAdministrateur admin = surveillance.getAdmin();
        if (admin != null) {
            admin.setDisponible(true);
            personneService.updatePersonne(admin);
        }
    }
}
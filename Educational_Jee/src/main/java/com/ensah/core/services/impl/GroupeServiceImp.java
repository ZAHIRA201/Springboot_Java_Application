package com.ensah.core.services.impl;

import com.ensah.core.bo.*;
import com.ensah.core.dao.*;
import com.ensah.core.services.IGroupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.*;

@Service
@Transactional
public class GroupeServiceImp implements IGroupeService {

    @Autowired
    private GroupeRepository groupeRepository;

    @Autowired
    private IPersonneRepository personneRepository;

    @Autowired
    private FiliereRepository filiereRepository;
    
    @Autowired
    private ICompteRepository compteRepository;
    

    @Autowired
    private DepartementRepository departementRepository;

    @Override
    public void addGroupe(Groupe groupe) {
        groupeRepository.save(groupe);
    }
    
    /**public void addGroupe(Groupe groupe) {
        if (isNomGroupeUnique(groupe.getNomGroupe())) {
            groupeRepository.save(groupe);
        } else {
            throw new NonUniqueNomGroupeException("Le nom du groupe doit être unique.");
        }
    }*/

    /**public void updateGroupe(Groupe groupe) {
        if (isNomGroupeUnique(groupe.getNomGroupe())) {
            groupeRepository.save(groupe);
        } else {
            throw new NonUniqueNomGroupeException("Le nom du groupe doit être unique.");
        }
    }

    private boolean isNomGroupeUnique(String nomGroupe) {
        Groupe existingGroupe = groupeRepository.findByNomGroupe(nomGroupe);
        return existingGroupe == null;
    }*/

    @Override
    public void updateGroupe(Groupe groupe) {
        groupeRepository.save(groupe);
    }

    @Override
    public void deleteGroupe(Long id) {
        Optional<Groupe> optionalGroupe = groupeRepository.findById(id);
        if (optionalGroupe.isPresent()) {
            groupeRepository.delete(optionalGroupe.get());
        } else {
            throw new EntityNotFoundException("Le groupe avec l'ID " + id + " n'existe pas.");
        }
    }

    @Override
    
    public Groupe getGroupeById(Long id) {
        return groupeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Le groupe avec l'ID " + id + " n'existe pas."));
    }

    @Override
    
    public List<Groupe> getAllGroupes() {
        return groupeRepository.findAll();
    }

    /**@Override
    public void addPersonneToGroupe(Long groupeId, Personne personne) {
        Groupe groupe = getGroupeById(groupeId);
        if (groupe != null && personne instanceof Enseignant) {
            groupe.getEnseignant().add((Enseignant) personne);
            groupeRepository.save(groupe); 
        } else {
            throw new IllegalArgumentException("Le groupe avec l'ID " + groupeId + " n'existe pas ou la personne n'est pas un enseignant.");
        }
    }*/
    
    
    @Override
    public void save(Groupe groupe) {
    	groupeRepository.save(groupe);
    }
    
    @Override
    public void addPersonneToGroupe(Long groupeId, Personne personne) {
        Groupe groupe = getGroupeById(groupeId);
        if (groupe != null && personne instanceof Enseignant) { 
            Enseignant enseignant = (Enseignant) personne; 
            if (enseignant.getGroupeEnseignants() != null) {
                throw new IllegalArgumentException("Cet enseignant est déjà dans un groupe.");
            }
            groupe.getEnseignant().add(enseignant); 
            enseignant.setGroupeEnseignants(groupe);
            groupeRepository.save(groupe); 
        } else {
            throw new IllegalArgumentException("Le groupe avec l'ID " + groupeId + " n'existe pas ou l'enseignant est invalide.");
        }
    }
    
    
    /**@Override
    public void addPersonneToGroupe(Long groupeId, String loginEnseignant) {
        Groupe groupe = getGroupeById(groupeId);
        Compte compteEnseignant = compteRepository.getCompteByLogin(loginEnseignant);

        if (groupe != null && compteEnseignant != null && compteEnseignant.getProprietaire() instanceof Enseignant) {
            Enseignant enseignant = (Enseignant) compteEnseignant.getProprietaire();
            if (enseignant.getGroupeEnseignants() != null) {
                throw new IllegalArgumentException("Cet enseignant est déjà dans un groupe.");
            }
            groupe.getEnseignant().add(enseignant);
            enseignant.setGroupeEnseignants(groupe);
            groupeRepository.save(groupe);
        } else {
            throw new IllegalArgumentException("Le groupe avec l'ID " + groupeId + " n'existe pas ou l'enseignant avec le login " + loginEnseignant + " est invalide.");
        }
    }*/

    

    @Override
    public void removePersonneFromGroupe(Long groupeId, Long personneId) {
        Groupe groupe = getGroupeById(groupeId);
        Optional<Personne> optionalPersonne = personneRepository.findById(personneId);
        if (groupe != null && optionalPersonne.isPresent() && optionalPersonne.get() instanceof Enseignant) {
            groupe.getEnseignant().remove(optionalPersonne.get());
            groupeRepository.save(groupe); // Save the updated group
        } else {
            throw new IllegalArgumentException("Le groupe avec l'ID " + groupeId + " n'existe pas ou la personne avec l'ID " + personneId + " n'est pas un enseignant.");
        }
    }

    @Override
    public void createGroupesByFiliere() {
        List<Filiere> filieres = filiereRepository.findAll();
        for (Filiere filiere : filieres) {
            Groupe groupe = new Groupe();
            groupe.setNomGroupe("Groupe Filiere " + filiere.getNom());
            groupe.setEnseignant(new ArrayList<>(filiere.getEnseignants()));
            groupeRepository.save(groupe);
        }
    }

    @Override
    public void createGroupesByDepartement() {
        List<Departement> departements = departementRepository.findAll();
        for (Departement departement : departements) {
            Groupe groupe = new Groupe();
            groupe.setNomGroupe("Groupe Departement " + departement.getNom());
            groupe.setEnseignant(new ArrayList<>(departement.getEnseignants()));
            groupeRepository.save(groupe);
        }
    }

    @Override
    public void createGroupesAleatoire(int nombreGroupes) {
        List<Personne> personnes = personneRepository.findAll();
        List<Enseignant> enseignants = new ArrayList<>();
        for (Personne personne : personnes) {
            if (personne instanceof Enseignant) {
                enseignants.add((Enseignant) personne);
            }
        }

        Collections.shuffle(enseignants);

        int tailleGroupe = enseignants.size() / nombreGroupes;
        for (int i = 0; i < nombreGroupes; i++) {
            Groupe groupe = new Groupe();
            groupe.setNomGroupe("Groupe Aleatoire " + (i + 1));
            groupe.setEnseignant(new ArrayList<>(enseignants.subList(i * tailleGroupe, Math.min((i + 1) * tailleGroupe, enseignants.size()))));
            groupeRepository.save(groupe);
        }
    }
}

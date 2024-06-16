package com.ensah.core.services;
import java.util.List;
import com.ensah.core.bo.Groupe;
import com.ensah.core.bo.Personne;

//import java.util.List;

public interface IGroupeService {

    void addGroupe(Groupe groupe);
   
    void updateGroupe(Groupe groupe);

    void deleteGroupe(Long id);
    void save(Groupe groupe);

    Groupe getGroupeById(Long id);

    List<Groupe> getAllGroupes();

    void addPersonneToGroupe(Long groupeId, Personne personne);

    void removePersonneFromGroupe(Long groupeId, Long personneId);

    void createGroupesByFiliere();

    void createGroupesByDepartement();

    void createGroupesAleatoire(int nombreGroupes);
    
   

	
}
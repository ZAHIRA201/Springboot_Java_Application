package com.ensah.core.services;

import java.util.List;

import com.ensah.core.bo.Enseignant;
import com.ensah.core.bo.Groupe;
import com.ensah.core.bo.Personne;
import com.ensah.core.bo.CadreAdministrateur;
import com.ensah.core.utils.ExcelExporter;

public interface IPersonneService {

	public void addPersonne(Personne pPerson);

	public void updatePersonne(Personne pPerson);

	public List<Personne> getAllPersonnes();

	public void deletePersonne(Long id);

	public Personne getPersonneById(Long id);
	
	public Personne getPersonneByCin(String cin);
	
	List<CadreAdministrateur> getAllAdmins();
	
	public ExcelExporter preparePersonneExport(List<Personne> persons);
	
	List<Enseignant> getAllEnseignants();
	
    List<Groupe> getAllGroupes();
    
    void addGroupe(Groupe groupe);
    
    public boolean isEnseignantAvailable(Long enseignantId);
    
    public void updateEnseignantAvailability(Long enseignantId, boolean available);
    
    
    
    
   
	
	

}

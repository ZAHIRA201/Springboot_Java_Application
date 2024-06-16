package com.ensah.core.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensah.core.bo.CadreAdministrateur;
import com.ensah.core.bo.Enseignant;
import com.ensah.core.bo.Groupe;
import com.ensah.core.bo.Personne;
import com.ensah.core.dao.GroupeRepository;
import com.ensah.core.dao.IPersonneRepository;
import com.ensah.core.services.IPersonneService;
import com.ensah.core.utils.ExcelExporter;

@Service
@Transactional
public class PersonneServiceImpl implements IPersonneService {

	@Autowired
	private IPersonneRepository personDao;
	@Autowired
    private GroupeRepository groupeRepository;
	
	

	public List<Personne> getAllPersonnes() {

		return personDao.findAll();
	}

	public void deletePersonne(Long id) {
		personDao.deleteById(id);

	}
	 
	
	
	
	
	
	@Override
    public List<CadreAdministrateur> getAllAdmins() {
        return personDao.findAll().stream()
            .filter(personne -> personne instanceof CadreAdministrateur)
            .map(personne -> (CadreAdministrateur) personne)
            .collect(Collectors.toList());
    }
	

	public Personne getPersonneById(Long id) {
		return personDao.findById(id).get();

	}

	public void addPersonne(Personne pPerson) {
		personDao.save(pPerson);

	}

	public void updatePersonne(Personne pPerson) {
		personDao.save(pPerson);
	

	}
	
	@Override
	public boolean isEnseignantAvailable(Long enseignantId) {
	    // Vérifiez la disponibilité de l'enseignant
	    Optional<Personne> personneOptional = personDao.findById(enseignantId);
	    if (personneOptional.isPresent()) {
	        Personne personne = personneOptional.get();
	        if (personne instanceof Enseignant) {
	            Enseignant enseignant = (Enseignant) personne;
	            return enseignant.isDisponible();
	        }
	    }
	    return false;
	}

	@Override
	public void updateEnseignantAvailability(Long enseignantId, boolean available) {
	    // Mettez à jour la disponibilité de l'enseignant
	    Optional<Personne> personneOptional = personDao.findById(enseignantId);
	    personneOptional.ifPresent(personne -> {
	        if (personne instanceof Enseignant) {
	            Enseignant enseignant = (Enseignant) personne;
	            enseignant.setDisponible(available);
	            personDao.save(enseignant);
	        }
	    });
	}

	
	@Override
    public List<Enseignant> getAllEnseignants() {
        return personDao.findAll().stream()
            .filter(personne -> personne instanceof Enseignant)
            .map(personne -> (Enseignant) personne)
            .collect(Collectors.toList());
    }
	
	@Override
    public List<Groupe> getAllGroupes() {
        return groupeRepository.findAll();
    }

    @Override
    public void addGroupe(Groupe groupe) {
        groupeRepository.save(groupe);
    }
    public Personne getPersonneByCin(String cin) {

		return personDao.getPersonneByCin(cin);

	}
    

	public ExcelExporter preparePersonneExport(List<Personne> persons) {
		String[] columnNames = new String[] { "Nom", "Prénom", "CIN", "Email", "Télé" };
		String[][] data = new String[persons.size()][5];

		int i = 0;
		for (Personne u : persons) {
			data[i][0] = u.getNom();
			data[i][1] = u.getPrenom();
			data[i][2] = u.getCin();
			data[i][3] = u.getEmail();
			data[i][4] = u.getTelephone();
			i++;
		}

		return new ExcelExporter(columnNames, data, "persons");

	}

	

}

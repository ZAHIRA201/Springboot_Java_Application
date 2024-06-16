package com.ensah.core.bo;

import jakarta.persistence.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@PrimaryKeyJoinColumn(name = "idEnseighant")
public class Enseignant extends Personne {
	
	
	
	
	private String specialite;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Departement departement;
	    
	@ManyToOne(cascade = CascadeType.ALL)
	private Filiere filiere;
	
	@ManyToOne(cascade = CascadeType.ALL)
    private Groupe groupeEnseignants;
	
    @OneToOne(mappedBy = "prof" , cascade = CascadeType.ALL )
    private Surveillance surveillance ;
    
   
    @OneToMany(mappedBy = "coordonnateur", cascade = CascadeType.ALL)
    private List<ElementPedag> elementsPedagogiques = new ArrayList<>();
    
    
    private boolean disponible;

    // Constructeur par défaut
    public Enseignant() {
        // Initialisation par défaut
        this.disponible = true; // Par exemple, un nouvel enseignant est initialement disponible
    }

    // Autres constructeurs

    // Getters et setters pour la disponibilité
    public boolean isDisponible() {
        return disponible;
    }
    
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    
   
    
    
    

	    
	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	public Groupe getGroupeEnseignants() {
		return groupeEnseignants;
	}

	public void setGroupeEnseignants(Groupe groupeEnseignants) {
		this.groupeEnseignants = groupeEnseignants;
	}

	public Surveillance getSurveillance() {
		return surveillance;
	}

	public void setSurveillance(Surveillance surveillance) {
		this.surveillance = surveillance;
	}

	public List<ElementPedag> getElementsPedagogiques() {
		return elementsPedagogiques;
	}

	public void setElementsPedagogiques(List<ElementPedag> elementsPedagogiques) {
		this.elementsPedagogiques = elementsPedagogiques;
	}

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

}

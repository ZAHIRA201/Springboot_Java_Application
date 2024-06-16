package com.ensah.core.bo;

import jakarta.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;

import jakarta.validation.constraints.NotBlank;

import jakarta.persistence.Entity;
@Entity
public class Salle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_salle;

    @NotBlank(message = "Ce champ est requis")
    private String nom;

    private int capacite;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "groupe_surveillants_id")
    private List<Surveillance> groupeSurveillants= new ArrayList<>() ;
    
    
    
    private boolean disponible;

    // Constructeur par défaut
    public Salle() {
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

    public Long getId_salle() {
        return id_salle;
    }

    public void setId_salle(Long id_salle) {
        this.id_salle = id_salle;
    }


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	

	public List<Surveillance> getGroupeSurveillants() {
		return groupeSurveillants;
	}

	public void setGroupeSurveillants(List<Surveillance> groupeSurveillants) {
		this.groupeSurveillants = groupeSurveillants;
	}



}

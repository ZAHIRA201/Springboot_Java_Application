package com.ensah.core.bo;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;

import jakarta.persistence.Entity;

@Entity
public class Groupe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Ce champ est requis")
    
    @Column(unique = true, nullable = false)
    private String nomGroupe;
    
    
    private String typeGroupe; 
    
    private String description;

    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "groupe_enseignant_id", unique = true)
    private List<Enseignant> enseignant = new ArrayList<>();
    
    @Transient
    private List<Long> enseignantIds;


	public List<Long> getEnseignantIds() {
		return enseignantIds;
	}


	public void setEnseignantIds(List<Long> enseignantIds) {
		this.enseignantIds = enseignantIds;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNomGroupe() {
		return nomGroupe;
	}


	public void setNomGroupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}


	public String getTypeGroupe() {
		return typeGroupe;
	}


	public void setTypeGroupe(String typeGroupe) {
		this.typeGroupe = typeGroupe;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public List<Enseignant> getEnseignant() {
		return enseignant;
	}


	public void setEnseignant(List<Enseignant> enseignant) {
		this.enseignant = enseignant;
	}
    
    
    


	
    
}


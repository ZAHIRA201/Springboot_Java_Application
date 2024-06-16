package com.ensah.core.bo;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Niveau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nom;
    
    
    
    @OneToMany(mappedBy = "niveau", cascade = CascadeType.ALL)
    private List<ElementPedag> elementsPedagogiques;



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public List<ElementPedag> getElementsPedagogiques() {
		return elementsPedagogiques;
	}



	public void setElementsPedagogiques(List<ElementPedag> elementsPedagogiques) {
		this.elementsPedagogiques = elementsPedagogiques;
	}
    
    
}

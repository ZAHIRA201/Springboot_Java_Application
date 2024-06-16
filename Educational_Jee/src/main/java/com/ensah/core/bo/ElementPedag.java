package com.ensah.core.bo;


import java.util.*;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;



@Entity
@Table(name = "ElementPedagogique")
public class ElementPedag {

    

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idElementPedagogique")
    private Long idElement;

    @Column(name = "titre")
    private String titre;
    
    @Column(name = "typeElement")
    private String typeElement;

    @Column(name = "niveau")
    private String niveau;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCoordonnateur")
    private Enseignant coordonnateur;
    
   

    // Constructeurs
    public ElementPedag() {
    }

    public ElementPedag(String titre, String niveau, Enseignant coordonnateur, String type) {
        this.titre = titre;
        this.niveau = niveau;
        this.coordonnateur = coordonnateur;
        this.typeElement = type;
    }

    

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public Enseignant getEnseignant() {
        return coordonnateur;
    }

    public void setEnseignant(Enseignant coordonnateur) {
        this.coordonnateur = coordonnateur;
    }

    public String getTypeElement() {
        return typeElement;
    }

    public void setTypeElement(String type) {
        this.typeElement = type;
    }
    public void setIdElement(Long idElement) {
		this.idElement = idElement;
	}

	public Long getIdElement() {
		return idElement;
	}
}



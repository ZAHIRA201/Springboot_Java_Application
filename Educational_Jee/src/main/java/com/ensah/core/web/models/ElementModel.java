package com.ensah.core.web.models;

import jakarta.validation.constraints.NotBlank;

public class ElementModel {

    private Long idElement;
    
    
    @NotBlank(message = "This field is required")
    private String titre;
    
    private String niveau;
    private String typeElement;
    
    private Long idPersonne;
    private String nom;
    
    

	public String getSpecialite() {
		return nom;
	}

	public void setSpecialite(String specialite) {
		this.nom = specialite;
	}

	public ElementModel() {
    	
    }

    public Long getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(Long idPersonne) {
		this.idPersonne = idPersonne;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Long getIdElement() {
        return idElement;
    }

    public void setIdElement(Long idElement) {
        this.idElement = idElement;
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

    

    public String getTypeElement() {
        return typeElement;
    }

    public void setTypeElement(String typeElement) {
        this.typeElement = typeElement;
    }

    @Override
    public String toString() {
        return "ElementModel [idElement=" + idElement + ", titre=" + titre + 
                ", niveau=" + niveau + ", typeElement=" + typeElement + "]";
    }
}

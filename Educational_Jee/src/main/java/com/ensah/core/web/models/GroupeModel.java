package com.ensah.core.web.models;

import java.util.List;

public class GroupeModel {
    public static final int TYPE_FILIERE = 1;
    public static final int TYPE_DEPARTEMENT = 2;
    public static final int TYPE_ALEATOIRE = 3;

    private Long idGroupe;
    private String nomGroupe;
    private String typeGroupe; 
    private String description; 
    private List<Long> enseignantIds;
    private String nouvelEnseignantId;
    private String nouvelEnseignantLogin;
    private Long enseignantId; 
    
    
    
    
    

   

    public String getNouvelEnseignantLogin() {
		return nouvelEnseignantLogin;
	}

	public void setNouvelEnseignantLogin(String nouvelEnseignantLogin) {
		this.nouvelEnseignantLogin = nouvelEnseignantLogin;
	}

	public Long getEnseignantId() {
        return enseignantId;
    }

    public void setEnseignantId(Long enseignantId) {
        this.enseignantId = enseignantId;
    }
    

    public Long getIdGroupe() {
		return idGroupe;
	}

	public void setIdGroupe(Long idGroupe) {
		this.idGroupe = idGroupe;
	}

	public List<Long> getEnseignantIds() {
		return enseignantIds;
	}

	public void setEnseignantIds(List<Long> enseignantIds) {
		this.enseignantIds = enseignantIds;
	}

	public static int getTypeFiliere() {
		return TYPE_FILIERE;
	}

	public static int getTypeDepartement() {
		return TYPE_DEPARTEMENT;
	}

	public static int getTypeAleatoire() {
		return TYPE_ALEATOIRE;
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

   

    public String getNouvelEnseignantId() {
        return nouvelEnseignantId;
    }

    public void setNouvelEnseignantId(String nouvelEnseignantLogin) {
        this.nouvelEnseignantId = nouvelEnseignantLogin;
    }

    @Override
	public String toString() {
		return "GroupeModel [idGroupe=" + idGroupe + ", nomGroupe=" + nomGroupe + ", typeGroupe=" + typeGroupe
				+ ", description=" + description + ", enseignantIds=" + enseignantIds + ", nouvelEnseignantLogin="
				+ nouvelEnseignantId + "]";
	}
    
}

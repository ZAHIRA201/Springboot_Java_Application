package com.ensah.core.bo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name="idCardreAdmin")
public class CadreAdministrateur extends Personne {

   private String grade;
   
   
   
   @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
   private List<Surveillance> surveillance = new ArrayList<>();
   
   
   
   private boolean disponible;

   // Constructeur par défaut
   public CadreAdministrateur() {
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

public String getGrade() {
	return grade;
}

public void setGrade(String grade) {
	this.grade = grade;
}

   
   
}
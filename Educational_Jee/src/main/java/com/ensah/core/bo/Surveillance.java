package com.ensah.core.bo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Surveillance {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "date")
    private LocalDateTime date;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Enseignant prof;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private CadreAdministrateur admin;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Salle salle;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Examen examen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Enseignant getProf() {
        return prof;
    }

    public void setProf(Enseignant prof) {
        this.prof = prof;
    }

    public CadreAdministrateur getAdmin() {
        return admin;
    }

    public void setAdmin(CadreAdministrateur admin) {
        this.admin = admin;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }
}

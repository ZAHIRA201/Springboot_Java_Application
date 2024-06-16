package com.ensah.core.bo;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.Year;

@Entity
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    @NotNull(message = "Ce champ est requis")
    private Date dateExam;

    
    @NotNull(message = "L'heure de début est requise")
    @DateTimeFormat(pattern = "HH:mm") 
    private LocalTime heureDebut;
    
    @NotNull(message = "Ce champ est requis")
    private String semestre;

    
    @NotNull(message = "Ce champ est requis")
    private String session;

    
    @NotNull(message = "Ce champ est requis")
    private String typeExamen;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "examen_id")
    private List<Surveillance> surveillances;

    public Date getDateExam() {
		return dateExam;
	}


	public void setDateExam(Date dateExam) {
		this.dateExam = dateExam;
	}


	public String getRapport() {
		return rapport;
	}


	public void setRapport(String rapport) {
		this.rapport = rapport;
	}

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "element_pedag_id")
    private ElementPedag elementPedag;

    

    private String epreuve;
    
    
    
    @Transient
    private MultipartFile pvFile;

   
    private String pv;
    
    
    @Transient
    private MultipartFile rapportPdf; 

    private String rapport;

    private int dureePrevue = 90; 
    
    
    @Column(name = "admin_id") 
    private Long adminId;
    
   
    
    

    
    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

   

    public Examen() {
    	
    
    	    surveillances = new ArrayList<>();
    	
    }
    
    public List<Long> getSalleIds() {
        List<Long> salleIds = new ArrayList<>();
        if (surveillances != null) {
            for (Surveillance surveillance : surveillances) {
                if (surveillance != null && surveillance.getSalle() != null) {
                    salleIds.add(surveillance.getSalle().getId_salle());
                }
            }
        }
        return salleIds;
    }
    
    
    
    public List<Long> getEnseignantIds() {
        List<Long> enseignantIds = new ArrayList<>();
        if (surveillances != null) {
            for (Surveillance surveillance : surveillances) {
                if (surveillance != null && surveillance.getProf() != null) {
                    enseignantIds.add(surveillance.getProf().getIdPersonne());
                }
            }
        }
        return enseignantIds;
    }
    
    
    public List<Long> getAdminIds() {
        List<Long> adminIds = new ArrayList<>();
        if (surveillances != null) {
            for (Surveillance surveillance : surveillances) {
                if (surveillance != null && surveillance.getAdmin() != null) {
                    adminIds.add(surveillance.getAdmin().getIdPersonne());
                }
            }
        }
        return adminIds;
    }



 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return dateExam;
    }

    public void setDate(Date date) {
        this.dateExam = date;
    }

   
    public LocalTime getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(LocalTime heureDebut) {
		this.heureDebut = heureDebut;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getTypeExamen() {
		return typeExamen;
	}

	public void setTypeExamen(String typeExamen) {
		this.typeExamen = typeExamen;
	}

	public List<Surveillance> getSurveillances() {
        return surveillances;
    }

    public void setSurveillances(List<Surveillance> surveillances) {
        this.surveillances = surveillances;
    }

    public ElementPedag getElementPedag() {
        return elementPedag;
    }

    public void setElementPedag(ElementPedag elementPedag) {
        this.elementPedag = elementPedag;
    }

    

    public String getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(String epreuve) {
        this.epreuve = epreuve;
    }

    public String getPv() {
        return pv;
    }

    public void setPv(String pv) {
        this.pv = pv;
    }

    public String getRapportTextuel() {
        return rapport;
    }

    public void setRapportTextuel(String rapportTextuel) {
        this.rapport = rapportTextuel;
    }

    public int getDureePrevue() {
        return dureePrevue;
    }

    public void setDureePrevue(int dureePrevue) {
        this.dureePrevue = dureePrevue;
    }

  
}
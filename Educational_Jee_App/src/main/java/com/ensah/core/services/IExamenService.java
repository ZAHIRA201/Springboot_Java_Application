package com.ensah.core.services;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.ensah.core.bo.Examen;
import com.ensah.core.utils.ExcelExporter;

public interface IExamenService {
	
	
	
	void addExamen(Examen examen);
	
	boolean creerExamen(Examen examen, List<Long> salleIds, List<Long> enseignantIds, Long adminId);
	
	
    void deleteExamen(Long id);
    
	List<Examen> getAllExamens();
 
    Examen getExamenById(Long id);
     
    List<Examen> getExamenByDate (Date dateExam);
    
    List<Examen> getExamenByLocalTime (LocalTime heureDebut);
    
    //ExcelExporter prepareExamenExport(List<Examen> examens);
    
    void examenTermine(Long examenId);
    
  
    boolean isSalleAvailable(Long salleId, Examen examen);
    
    void removeSurveillanceFromExamen(Long surveillanceId);

    
}

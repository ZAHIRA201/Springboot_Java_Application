package com.ensah.core.services;

import java.util.List;

import com.ensah.core.bo.Salle;
import com.ensah.core.utils.ExcelExporter;

public interface ISalleService {

    public void addSalle(Salle salle);

    //public void updateSalle(Salle salle);

    public List<Salle> getAllSalles();

    public void deleteSalle(Long id);

    public Salle getSalleById(Long id);
    
    //public ExcelExporter prepareSalleExport(List<Salle> salles);
    
    void updateSalle(Salle salle);
    
    
    
   
}



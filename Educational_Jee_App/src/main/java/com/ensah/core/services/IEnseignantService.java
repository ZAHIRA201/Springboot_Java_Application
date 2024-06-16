package com.ensah.core.services;

import com.ensah.core.bo.Enseignant;

import java.util.List;

public interface IEnseignantService {
    void addEnseignant(Enseignant enseignant);

    void updateEnseignant(Enseignant enseignant);

    void deleteEnseignant(Long id);

    List<Enseignant> getAllEnseignants();

    Enseignant getEnseignantById(Long id);
}

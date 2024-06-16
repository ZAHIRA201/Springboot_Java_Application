package com.ensah.core.services.impl;

import com.ensah.core.bo.Enseignant;
import com.ensah.core.dao.EnseignantRepository;
import com.ensah.core.services.IEnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnseignantServiceImpl implements IEnseignantService {

    @Autowired
    private EnseignantRepository enseignantRepository;

    @Override
    public void addEnseignant(Enseignant enseignant) {
        enseignantRepository.save(enseignant);
    }

    @Override
    public void updateEnseignant(Enseignant enseignant) {
        enseignantRepository.save(enseignant);
    }

    @Override
    public void deleteEnseignant(Long id) {
        enseignantRepository.deleteById(id);
    }

    @Override
    public List<Enseignant> getAllEnseignants() {
        return enseignantRepository.findAll();
    }

    @Override
    public Enseignant getEnseignantById(Long id) {
        return enseignantRepository.findById(id).get();
    }
}

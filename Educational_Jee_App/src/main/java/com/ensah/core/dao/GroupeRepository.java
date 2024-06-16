package com.ensah.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensah.core.bo.Groupe;

public interface GroupeRepository extends JpaRepository<Groupe, Long> {
	
	Groupe findByNomGroupe(String nomGroupe);

}

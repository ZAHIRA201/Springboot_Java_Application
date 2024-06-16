package com.ensah.core.dao;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ensah.core.bo.Examen;

public interface IExamenRepository extends JpaRepository<Examen, Long> {
	
	List<Examen> findByDateExam(Date dateExam);
	List<Examen> findByHeureDebut(LocalTime heureDebut);
	
	@Query("SELECT e FROM Examen e JOIN e.surveillances s WHERE s.salle.id = :salleId")
    List<Examen> findBySalleId(@Param("salleId") Long salleId);

}
package com.ensah.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ensah.core.bo.ElementPedag;

@Repository
public interface ElementPedagogiqueRepository extends JpaRepository<ElementPedag, Long> {
	
}


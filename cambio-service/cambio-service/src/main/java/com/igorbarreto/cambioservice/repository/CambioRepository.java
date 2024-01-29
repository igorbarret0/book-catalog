package com.igorbarreto.cambioservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.igorbarreto.cambioservice.model.Cambio;

@Repository
public interface CambioRepository extends JpaRepository<Cambio, Long> {

    Cambio findByFromAndTo(String from, String to);
    
}

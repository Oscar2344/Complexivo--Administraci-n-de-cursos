package com.example.Ejercicio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Ejercicio.model.Aulas;



@Repository
public interface AulaRepository extends JpaRepository<Aulas, Long> {
     List<Aulas> findByNumeroDeAulaContainingIgnoreCase(String numeroDeAula);
}
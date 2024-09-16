package com.example.Ejercicio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Ejercicio.model.Matricula;


@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
     List<Matricula> findByEstadoContainingIgnoreCase(String  estado);

     void deleteByCursoId(Long cursoId);
    
}
package com.example.Ejercicio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Ejercicio.model.Curso;


@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
     
     List<Curso> findByNombreCursoContainingIgnoreCase(String nombreCurso);
     void deleteById(Long id);

}
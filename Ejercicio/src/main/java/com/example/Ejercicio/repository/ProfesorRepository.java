package com.example.Ejercicio.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.Ejercicio.model.Profesor;




@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
        List<Profesor> findByNombreContainingIgnoreCase(String nombre);
}

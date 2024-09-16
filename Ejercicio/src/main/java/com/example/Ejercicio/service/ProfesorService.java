package com.example.Ejercicio.service;

import java.util.List;
import com.example.Ejercicio.model.Profesor;

public interface ProfesorService {
    List<Profesor> getAllProfesores();
    Profesor getProfesorById(Long id);
    Profesor createProfesor(Profesor profesor);
    Profesor updateProfesor(Long id, Profesor profesorDetails);
    void deleteProfesor(Long id);
    List<Profesor> searchProfesoresByNombre(String nombre);
}

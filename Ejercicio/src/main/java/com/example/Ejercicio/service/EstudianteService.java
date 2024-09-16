package com.example.Ejercicio.service;



import java.util.List;

import com.example.Ejercicio.model.Estudiante;

public interface EstudianteService {
    List<Estudiante> getAllEstudiantes();
    Estudiante getEstudianteById(Long id);
    Estudiante createEstudiante(Estudiante estudiante);
    Estudiante updateEstudiante(Long id, Estudiante estudianteDetails);
    void deleteEstudiante(Long id);
    List<Estudiante> searchEstudiantesByNombre(String nombre);
    List<Estudiante> obtenerTodos();
}
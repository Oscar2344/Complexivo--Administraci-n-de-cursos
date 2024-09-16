package com.example.Ejercicio.service;

import java.util.List;
import com.example.Ejercicio.model.Curso;

public interface CursoService {
    List<Curso> getAllCursos();
    Curso getCursoById(Long id);
    Curso createCurso(Curso curso);
    Curso updateCurso(Long id, Curso cursoDetails);
    void deleteCurso(Long id);
    List<Curso> searchCursosByNombre(String nombreCurso);
    List<Curso> obtenerTodos();
    void deleteMatriculasByCursoId(Long cursoId);

}

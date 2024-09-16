package com.example.Ejercicio.service;



import java.util.List;

import com.example.Ejercicio.model.Matricula;

public interface MatriculaService {
    List<Matricula> getAllMatriculas();
    Matricula getMatriculaById(Long id);
    Matricula createMatricula(Matricula matricula);
    Matricula updateMatricula(Long id, Matricula matriculaDetails);
    void deleteByCursoId(Long cursoId);
      List<Matricula> searchMatriculasByEstado(String estado);
}

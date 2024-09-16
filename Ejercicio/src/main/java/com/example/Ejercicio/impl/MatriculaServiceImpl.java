package com.example.Ejercicio.impl;

import com.example.Ejercicio.model.Matricula;
import com.example.Ejercicio.repository.MatriculaRepository;
import com.example.Ejercicio.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatriculaServiceImpl implements MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Override
    public List<Matricula> getAllMatriculas() {
        return matriculaRepository.findAll();
    }

    @Override
    public Matricula getMatriculaById(Long id) {
        return matriculaRepository.findById(id).orElseThrow();
    }

    @Override
    public Matricula createMatricula(Matricula matricula) {
        return matriculaRepository.save(matricula);
    }

    @Override
    public Matricula updateMatricula(Long id, Matricula matriculaDetails) {
        Matricula matricula = matriculaRepository.findById(id).orElseThrow();
         matricula.setEstudiante(matriculaDetails.getEstudiante());
    matricula.setCurso(matriculaDetails.getCurso());
    matricula.setFechaInscripcion(matriculaDetails.getFechaInscripcion());
    matricula.setEstado(matriculaDetails.getEstado());
    matricula.setCalificacion(matriculaDetails.getCalificacion());
    matricula.setFechaCompletado(matriculaDetails.getFechaCompletado());
        return matriculaRepository.save(matricula);
    }

    @Override
    public void deleteByCursoId(Long cursoId) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteByCursoId'");
    }

    @Override
    public List<Matricula> searchMatriculasByEstado(String estado) {
        throw new UnsupportedOperationException("Unimplemented method 'searchMatriculasByEstado'");
    }
}
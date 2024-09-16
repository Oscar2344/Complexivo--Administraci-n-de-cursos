package com.example.Ejercicio.impl;



import com.example.Ejercicio.model.Curso;

import com.example.Ejercicio.repository.CursoRepository;
import com.example.Ejercicio.repository.MatriculaRepository;
import com.example.Ejercicio.service.CursoService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private MatriculaRepository matriculaRepository; 
    @Override
    public List<Curso> getAllCursos() {
        return cursoRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteMatriculasByCursoId(Long cursoId) {
        matriculaRepository.deleteByCursoId(cursoId); 
    }
    @Override
    public Curso getCursoById(Long id) {
        return cursoRepository.findById(id).orElseThrow();
    }

    @Override
    public Curso createCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    public Curso updateCurso(Long id, Curso cursoDetails) {
        Curso curso = cursoRepository.findById(id).orElseThrow();
        curso.setNombreCurso(cursoDetails.getNombreCurso());
    curso.setDescripcion(cursoDetails.getDescripcion());
    curso.setNivel(cursoDetails.getNivel());
    curso.setModalidad(cursoDetails.getModalidad());
        return cursoRepository.save(curso);
    }

    @Override
    public void deleteCurso(Long id) {
        Curso curso = cursoRepository.findById(id).orElseThrow();
        cursoRepository.delete(curso);
    }

  @Override
    public List<Curso> searchCursosByNombre(String nombreCurso) {
        return cursoRepository.findByNombreCursoContainingIgnoreCase(nombreCurso);
    }

@Override
public List<Curso> obtenerTodos() {
    throw new UnsupportedOperationException("Unimplemented method 'obtenerTodos'");
}



}

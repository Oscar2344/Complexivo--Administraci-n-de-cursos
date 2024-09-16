package com.example.Ejercicio.impl;

import com.example.Ejercicio.model.Profesor;
import com.example.Ejercicio.repository.ProfesorRepository;
import com.example.Ejercicio.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Override
    public List<Profesor> getAllProfesores() {
        return profesorRepository.findAll();
    }

    @Override
    public Profesor getProfesorById(Long id) {
        return profesorRepository.findById(id).orElseThrow();
    }

    @Override
    public Profesor createProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    @Override
    public Profesor updateProfesor(Long id, Profesor profesorDetails) {
        Profesor profesor = profesorRepository.findById(id).orElseThrow();
        profesor.setNombre(profesorDetails.getNombre());
        profesor.setApellido(profesorDetails.getApellido());
        profesor.setEmail(profesorDetails.getEmail());
        profesor.setEspecializacion(profesorDetails.getEspecializacion());
        return profesorRepository.save(profesor);
    }

    @Override
    public void deleteProfesor(Long id) {
        Profesor profesor = profesorRepository.findById(id).orElseThrow();
        profesorRepository.delete(profesor);
    }
        @Override
    public List<Profesor> searchProfesoresByNombre(String nombre) {
        return profesorRepository.findByNombreContainingIgnoreCase(nombre);
    }
}


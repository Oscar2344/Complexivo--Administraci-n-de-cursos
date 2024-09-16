package com.example.Ejercicio.impl;


import com.example.Ejercicio.model.Estudiante;
import com.example.Ejercicio.repository.EstudianteRepository;
import com.example.Ejercicio.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public List<Estudiante> getAllEstudiantes() {
        return estudianteRepository.findAll();
    }

    @Override
    public Estudiante getEstudianteById(Long id) {
        return estudianteRepository.findById(id).orElseThrow();
    }

    @Override
    public Estudiante createEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public Estudiante updateEstudiante(Long id, Estudiante estudianteDetails) {
        Estudiante estudiante = estudianteRepository.findById(id).orElseThrow();
        estudiante.setNombre(estudianteDetails.getNombre());
        estudiante.setApellido(estudianteDetails.getApellido());
        estudiante.setCedula(estudianteDetails.getCedula());
        estudiante.setCorreo(estudianteDetails.getCorreo());
        estudiante.setTelefono(estudianteDetails.getTelefono());
        return estudianteRepository.save(estudiante);
    }

    @Override
    public void deleteEstudiante(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id).orElseThrow();
        estudianteRepository.delete(estudiante);
    }
    @Override
    public List<Estudiante> searchEstudiantesByNombre(String nombre) {
        return estudianteRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public List<Estudiante> obtenerTodos() {
        
        throw new UnsupportedOperationException("Unimplemented method 'obtenerTodos'");
    }
}

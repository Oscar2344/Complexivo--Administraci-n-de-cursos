package com.example.Ejercicio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Ejercicio.model.Profesor;
import com.example.Ejercicio.repository.ProfesorRepository;
import com.example.Ejercicio.service.ProfesorService;

import java.util.List;

@Controller
@RequestMapping("/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorRepository profesorRepository;
    @Autowired
    private ProfesorService profesorService;

    @GetMapping
    public String getAllProfesores(@RequestParam(value = "nombre", required = false) String nombre, Model model) {
        List<Profesor> profesores;
    
        if (nombre == null || nombre.trim().isEmpty()) {
            profesores = profesorRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        } else {
            profesores = profesorService.searchProfesoresByNombre(nombre);
        }
        model.addAttribute("profesores", profesores);
        return "profesores/profesores";
    }
    
    @GetMapping("/nuevo")
    public String showCreateForm(Model model) {
        model.addAttribute("profesor", new Profesor());
        return "profesores/nuevo-profesor";
    }

    @PostMapping("/nuevo")
    public String createProfesor(@ModelAttribute Profesor profesor) {
        profesorRepository.save(profesor);
        return "redirect:/profesores";
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Profesor profesor = profesorRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Profesor no encontrado con ID: " + id));
        model.addAttribute("profesor", profesor);
        return "profesores/editar-profesor";
    }

    @PostMapping("/editar/{id}")
    public String updateProfesor(@PathVariable Long id, @ModelAttribute Profesor profesorDetails) {
        Profesor profesor = profesorRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Profesor no encontrado con ID: " + id));
        profesor.setNombre(profesorDetails.getNombre());
        profesor.setApellido(profesorDetails.getApellido());
        profesor.setEmail(profesorDetails.getEmail());
        profesor.setEspecializacion(profesorDetails.getEspecializacion());
    
        profesorRepository.save(profesor);
        return "redirect:/profesores";
    }
    
    @PostMapping("/eliminar/{id}")
    public String deleteProfesor(@PathVariable Long id) {
        Profesor profesor = profesorRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Profesor no encontrado con ID: " + id));
        profesorRepository.delete(profesor);
    
        return "redirect:/profesores";
    }
    
    @GetMapping("/{id}")
    public String getProfesorById(@PathVariable Long id, Model model) {
        Profesor profesor = profesorRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Profesor no encontrado con ID: " + id));
        model.addAttribute("profesor", profesor);
        return "profesores/detalle-profesor";
    }
}

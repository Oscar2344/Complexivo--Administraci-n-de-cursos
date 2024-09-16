package com.example.Ejercicio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Ejercicio.model.Curso;
import com.example.Ejercicio.model.Profesor;
import com.example.Ejercicio.repository.CursoRepository;
import com.example.Ejercicio.repository.ProfesorRepository;
import com.example.Ejercicio.service.CursoService;

import jakarta.transaction.Transactional;

import java.util.List;

@Controller
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private CursoService cursoService;
    @Autowired
private ProfesorRepository profesorRepository;
    // Obtener la lista de todos los cursos
    @GetMapping
    public String getAllCursos(@RequestParam(value = "nombreCurso", required = false) String nombreCurso, Model model) {
        List<Curso> cursos;
        
        if (nombreCurso == null || nombreCurso.trim().isEmpty()) {
            // Si no hay nombre de búsqueda, mostrar todos los cursos
            cursos = cursoRepository.findAll(Sort.by(Sort.Direction.ASC, "nombreCurso"));
        } else {
            // Si hay un nombre de búsqueda, filtrar por nombre del curso
            cursos = cursoService.searchCursosByNombre(nombreCurso);
        }
        
        model.addAttribute("cursos", cursos);
        return "cursos/cursos";  
    }

    // Mostrar formulario para crear un nuevo curso
    @GetMapping("/nuevo")
    public String showCreateForm(Model model) {
        model.addAttribute("curso", new Curso());
        model.addAttribute("profesores", profesorRepository.findAll());
        return "cursos/nuevo-curso";
    }

    // Guardar un nuevo curso
    @PostMapping("/nuevo")
    public String createCurso(@ModelAttribute Curso curso) {
        cursoRepository.save(curso);
        return "redirect:/cursos";
    }

    // Mostrar formulario para editar un curso existente
    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Curso curso = cursoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado con ID: " + id));
        model.addAttribute("curso", curso);
        model.addAttribute("profesores", profesorRepository.findAll());
        return "cursos/editar-curso";
    }

    // Actualizar un curso existente
  @PostMapping("/editar/{id}")
public String updateCurso(@PathVariable Long id, @ModelAttribute Curso cursoDetails) {
    Curso curso = cursoRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado con ID: " + id));
    
    // Actualizar los campos del curso
    curso.setNombreCurso(cursoDetails.getNombreCurso());
    curso.setDescripcion(cursoDetails.getDescripcion());
    curso.setNivel(cursoDetails.getNivel());
    curso.setModalidad(cursoDetails.getModalidad());
    
    // Asegurarse de que el profesor esté correctamente asignado
    if (cursoDetails.getProfesor() != null && cursoDetails.getProfesor().getId() != null) {
        Profesor profesor = profesorRepository.findById(cursoDetails.getProfesor().getId())
            .orElseThrow(() -> new IllegalArgumentException("Profesor no encontrado con ID: " + cursoDetails.getProfesor().getId()));
        curso.setProfesor(profesor);
    }
    
    cursoRepository.save(curso);
    return "redirect:/cursos";
}


    @Transactional
    @PostMapping("/eliminar/{id}")
    public String deleteCurso(@PathVariable Long id) {
        cursoService.deleteMatriculasByCursoId(id);
        
        Curso curso = cursoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado con ID: " + id));
        cursoRepository.delete(curso);
    
        return "redirect:/cursos";
    }
    
    
    
    // Obtener un curso por su ID (detalles o vista específica)
    @GetMapping("/{id}")
    public String getCursoById(@PathVariable Long id, Model model) {
        Curso curso = cursoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado con ID: " + id));
        model.addAttribute("curso", curso);
        return "cursos/detalle-curso"; 
    }
}

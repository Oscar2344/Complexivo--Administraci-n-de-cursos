package com.example.Ejercicio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Ejercicio.model.Matricula;
import com.example.Ejercicio.repository.MatriculaRepository;
import com.example.Ejercicio.repository.EstudianteRepository;
import com.example.Ejercicio.repository.CursoRepository;

import java.util.List;

@Controller
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CursoRepository cursoRepository;

    // Mostrar la lista de matrículas con opción de búsqueda por estado
    @GetMapping
    public String getAllMatriculas(@RequestParam(value = "estado", required = false) String estado, Model model) {
        List<Matricula> matriculas;

        if (estado == null || estado.trim().isEmpty()) {
            matriculas = matriculaRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        } else {
            matriculas = matriculaRepository.findByEstadoContainingIgnoreCase(estado);
        }

        model.addAttribute("matriculas", matriculas);
        model.addAttribute("estudiantes", estudianteRepository.findAll());
        model.addAttribute("cursos", cursoRepository.findAll());
        return "matriculas/matriculas";
    }

    // Mostrar formulario para crear una nueva matrícula
    @GetMapping("/nuevo")
    public String showCreateForm(Model model) {
        model.addAttribute("matricula", new Matricula());
        model.addAttribute("estudiantes", estudianteRepository.findAll());
        model.addAttribute("cursos", cursoRepository.findAll());
        return "matriculas/nuevo-matricula";
    }

    // Crear una nueva matrícula
    @PostMapping("/nuevo")
    public String createMatricula(@ModelAttribute Matricula matricula) {
        matriculaRepository.save(matricula);
        return "redirect:/matriculas";
    }

    // Mostrar formulario para editar una matrícula existente
    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Matricula matricula = matriculaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Matrícula no encontrada con ID: " + id));
        model.addAttribute("matricula", matricula);
        model.addAttribute("estudiantes", estudianteRepository.findAll());
        model.addAttribute("cursos", cursoRepository.findAll());
        return "matriculas/editar-matricula";
    }

    // Actualizar una matrícula existente
    @PostMapping("/editar/{id}")
    public String updateMatricula(@PathVariable Long id, @ModelAttribute Matricula matricula) {
        matricula.setId(id);
        matriculaRepository.save(matricula);
        return "redirect:/matriculas";
    }

    // Eliminar una matrícula
    @PostMapping("/eliminar/{id}")
    public String deleteMatricula(@PathVariable Long id) {
        matriculaRepository.deleteById(id);
        return "redirect:/matriculas";
    }
}

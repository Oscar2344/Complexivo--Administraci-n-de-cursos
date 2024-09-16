package com.example.Ejercicio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Ejercicio.model.Estudiante;
import com.example.Ejercicio.repository.EstudianteRepository;
import com.example.Ejercicio.service.EstudianteService;

import java.util.List;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public String getAllEstudiantes(@RequestParam(value = "nombre", required = false) String nombre, Model model) {
        List<Estudiante> estudiantes;
    
        if (nombre == null || nombre.trim().isEmpty()) {
            estudiantes = estudianteRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        } else {
            estudiantes = estudianteService.searchEstudiantesByNombre(nombre);
        }
        model.addAttribute("estudiantes", estudiantes);
        return "estudiantes/estudiantes";  
    }
    
    @GetMapping("/nuevo")
    public String showCreateForm(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "estudiantes/nuevo-estudiante";  
    }


    @PostMapping("/nuevo")
    public String createEstudiante(@ModelAttribute Estudiante estudiante) {
        estudianteRepository.save(estudiante);
        return "redirect:/estudiantes";
    }

    // Mostrar formulario para editar un estudiante existente
    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Estudiante estudiante = estudianteRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado con ID: " + id));
        model.addAttribute("estudiante", estudiante);
        return "estudiantes/editar-estudiante";  
    }

    // Actualizar un estudiante existente
    @PostMapping("/editar/{id}")
    public String updateEstudiante(@PathVariable Long id, @ModelAttribute Estudiante estudianteDetails) {
        Estudiante estudiante = estudianteRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado con ID: " + id));
        estudiante.setNombre(estudianteDetails.getNombre());
        estudiante.setApellido(estudianteDetails.getApellido());
        estudiante.setCedula(estudianteDetails.getCedula());
        estudiante.setCorreo(estudianteDetails.getCorreo());
        estudiante.setTelefono(estudianteDetails.getTelefono());
    
        estudianteRepository.save(estudiante);
        return "redirect:/estudiantes";
    }
    
    // Eliminar un estudiante existente (Usa POST para simular DELETE)
    @PostMapping("/eliminar/{id}")
    public String deleteEstudiante(@PathVariable Long id) {
        // Eliminar las referencias en la tabla `db_matricula` antes de eliminar el estudiante
        estudianteService.deleteEstudiante(id);
        
        return "redirect:/estudiantes";
    }
    
    
    // Obtener un estudiante por su ID (detalles o vista específica)
    @GetMapping("/{id}")
    public String getEstudianteById(@PathVariable Long id, Model model) {
        Estudiante estudiante = estudianteRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado con ID: " + id));
        model.addAttribute("estudiante", estudiante);
        return "estudiantes/detalle-estudiante"; 
    }
}

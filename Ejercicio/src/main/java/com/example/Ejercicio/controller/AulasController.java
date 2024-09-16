package com.example.Ejercicio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Ejercicio.model.Aulas;
import com.example.Ejercicio.repository.AulaRepository;
import com.example.Ejercicio.repository.CursoRepository;
import com.example.Ejercicio.service.AulaService;

import java.util.List;

@Controller
@RequestMapping("/aulas")
public class AulasController {

    @Autowired
    private AulaRepository aulasRepository;
    @Autowired
    private AulaService aulasService;
    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public String getAllAulas(@RequestParam(value = "numeroDeAula", required = false) String numeroDeAula, Model model) {
        List<Aulas> aulas;

        if (numeroDeAula == null) {
            aulas = aulasRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        } else {
            aulas = aulasService.searchAulasByNumero(numeroDeAula);
        }
        model.addAttribute("aulas", aulas);
        return "aulas/aulas";
    }

    @GetMapping("/nuevo")
    public String showCreateForm(Model model) {
        model.addAttribute("aula", new Aulas());
        model.addAttribute("cursos", cursoRepository.findAll()); // Fetching all Cursos
        return "aulas/nueva-aula";
    }

    @PostMapping("/nuevo")
    public String createAula(@ModelAttribute Aulas aula) {
        aulasRepository.save(aula);
        return "redirect:/aulas";
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Aulas aula = aulasRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Aula no encontrada con ID: " + id));
        model.addAttribute("aula", aula);
        model.addAttribute("cursos", cursoRepository.findAll()); // Fetching all Cursos
        return "aulas/editar-aula";
    }

    @PostMapping("/editar/{id}")
    public String updateAula(@PathVariable Long id, @ModelAttribute Aulas aulaDetails) {
        Aulas aula = aulasRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Aula no encontrada con ID: " + id));
        
        // Actualizar los detalles del aula
        aula.setNumeroDeAula(aulaDetails.getNumeroDeAula());
        aula.setCapacidad(aulaDetails.getCapacidad());
        aula.setTieneProyector(aulaDetails.isTieneProyector());
        aula.setEsAulaVirtual(aulaDetails.isEsAulaVirtual());
        aula.setCantidadDeSillas(aulaDetails.getCantidadDeSillas());
        aula.setHorarioDisponible(aulaDetails.getHorarioDisponible());
        
        // Aquí actualizamos el curso relacionado
        aula.setCurso(aulaDetails.getCurso());
    
        // Guardamos el aula actualizada
        aulasRepository.save(aula);
        
        return "redirect:/aulas";
    }
    

    @PostMapping("/eliminar/{id}")
    public String deleteAula(@PathVariable Long id) {
        Aulas aula = aulasRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Aula no encontrada con ID: " + id));
        aulasRepository.delete(aula);
        return "redirect:/aulas";
    }

    @GetMapping("/{id}")
    public String getAulaById(@PathVariable Long id, Model model) {
        Aulas aula = aulasRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Aula no encontrada con ID: " + id));
        model.addAttribute("aula", aula);
        return "aulas/detalle-aula";
    }
}

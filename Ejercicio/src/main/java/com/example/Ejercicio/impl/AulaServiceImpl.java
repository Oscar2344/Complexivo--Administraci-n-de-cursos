package com.example.Ejercicio.impl;





import com.example.Ejercicio.model.Aulas;
import com.example.Ejercicio.repository.AulaRepository;
import com.example.Ejercicio.service.AulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AulaServiceImpl implements AulaService {

    @Autowired
    private AulaRepository aulaRepository;

    @Override
    public List<Aulas> getAllAulas() {
        return aulaRepository.findAll();
    }

    @Override
    public Aulas getAulaById(Long id) {
        return aulaRepository.findById(id).orElseThrow();
    }

    @Override
    public Aulas createAula(Aulas aula) {
        return aulaRepository.save(aula);
    }

    @Override
public Aulas updateAula(Long id, Aulas aulaDetails) {
    Aulas aula = aulaRepository.findById(id).orElseThrow();

    aula.setNumeroDeAula(aulaDetails.getNumeroDeAula());
    aula.setCapacidad(aulaDetails.getCapacidad());
    aula.setTieneProyector(aulaDetails.isTieneProyector());
    aula.setEsAulaVirtual(aulaDetails.isEsAulaVirtual());
    aula.setCantidadDeSillas(aulaDetails.getCantidadDeSillas());
    aula.setHorarioDisponible(aulaDetails.getHorarioDisponible());
    aula.setCurso(aulaDetails.getCurso());

    return aulaRepository.save(aula);
}


    @Override
    public void deleteAula(Long id) {
        Aulas aula = aulaRepository.findById(id).orElseThrow();
        aulaRepository.delete(aula);
    }
     @Override
    public List<Aulas> searchAulasByNumero(String numeroDeAula) {
        return aulaRepository .findByNumeroDeAulaContainingIgnoreCase( numeroDeAula);
    }
}


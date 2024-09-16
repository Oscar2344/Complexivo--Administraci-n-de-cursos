package com.example.Ejercicio.service;




import java.util.List;

import com.example.Ejercicio.model.Aulas;


public interface AulaService {
    List<Aulas> getAllAulas();
    Aulas getAulaById(Long id);
    Aulas createAula(Aulas aula);
    Aulas updateAula(Long id, Aulas updatedAula);
    void deleteAula(Long id);
    List<Aulas> searchAulasByNumero(String numeroDeAula);
    
}

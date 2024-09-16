package com.example.Ejercicio.model;

import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "dbAulas")
public class Aulas implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String  numeroDeAula;
    private int capacidad;
    private boolean tieneProyector;
    private boolean esAulaVirtual;
    private int cantidadDeSillas;
    private String horarioDisponible;

    
    @ManyToOne
    @JoinColumn(name = "curso_id")  
    private Curso curso;
}
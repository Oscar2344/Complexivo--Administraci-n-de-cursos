package com.example.Ejercicio.model;

import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "dbMatricula")
public class Matricula implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
   

    private LocalDate fechaInscripcion;
    private String estado;
    private Integer calificacion;
    private LocalDate fechaCompletado;


 @ManyToOne
 @JoinColumn(name = "estudiante_id")  
 private Estudiante estudiante;

 @ManyToOne
 @JoinColumn(name = "curso_id")  
 private Curso curso;
}
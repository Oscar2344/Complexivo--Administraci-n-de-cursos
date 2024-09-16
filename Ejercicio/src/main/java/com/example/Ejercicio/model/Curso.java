package com.example.Ejercicio.model;

import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;


@Data
@Entity
@Table(name = "dbCursos")
public class Curso implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCurso;
    private String descripcion;
    private String nivel;
    private String modalidad;


    @ManyToOne
    @JoinColumn(name = "tutor_profesor_id")  
    private Profesor profesor;

    @OneToMany(mappedBy = "curso")  
    private List<Matricula> matriculas;

    
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Aulas> aulas;
}
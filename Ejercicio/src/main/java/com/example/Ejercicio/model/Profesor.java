package com.example.Ejercicio.model;

import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;


@Data
@Entity
@Table(name = "dbProfesor")
public class Profesor implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String email;
    private String especializacion;


   @OneToMany(mappedBy = "profesor")  
   private List<Curso> cursos;
}
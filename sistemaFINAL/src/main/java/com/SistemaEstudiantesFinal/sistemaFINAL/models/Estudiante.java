package com.SistemaEstudiantesFinal.sistemaFINAL.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "estudiantes")
@ToString @EqualsAndHashCode
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id_estudiante")
    private int idEstudiante;

    @Getter @Setter @Column(name = "nombre_completo")
    private String nombreCompleto;

    @Getter @Setter @Column(name = "correo")
    private String correo;

    @Getter @Setter @Column(name = "telefono")
    private String telefono;

    @Getter @Setter @Column(name = "id_idioma")
    private int idIdioma;



}

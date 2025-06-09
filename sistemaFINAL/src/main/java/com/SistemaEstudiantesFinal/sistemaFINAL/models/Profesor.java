package com.SistemaEstudiantesFinal.sistemaFINAL.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "profesores")
@ToString
@EqualsAndHashCode
public class Profesor {

    @Id
    @Getter @Setter @Column(name = "Id_Profesor")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int  id;

    @Getter @Setter @Column(name = "nombre")
    private String usuarioProfesor;

    @Getter @Setter @Column(name = "password")
    private String password;

}

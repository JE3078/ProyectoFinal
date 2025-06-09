package com.SistemaEstudiantesFinal.sistemaFINAL.dao;

import com.SistemaEstudiantesFinal.sistemaFINAL.models.Estudiante;

import java.util.List;

public interface EstudianteDao {

    List<Estudiante> getEstudiantes();

    Estudiante getEstudiante(int id);

    void eliminarEstudiante(int id);

    void agregarEstudiante(Estudiante estudiante);

    Estudiante updateEstudiante(Estudiante estudiante, int id);

    Estudiante updateParcial(Estudiante estudiante, int id);

}
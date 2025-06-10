package com.SistemaEstudiantesFinal.sistemaFINAL.dao;

import com.SistemaEstudiantesFinal.sistemaFINAL.models.Estudiante;

import java.util.List;

public interface EstudianteDao {

    abstract List<Estudiante> getEstudiantes();

    abstract Estudiante getEstudiante(int id);

    abstract void eliminarEstudiante(int id);

    abstract void agregarEstudiante(Estudiante estudiante);

    abstract Estudiante updateEstudiante(Estudiante estudiante, int id);

    abstract Estudiante updateParcial(Estudiante estudiante, int id);

}
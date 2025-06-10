package com.SistemaEstudiantesFinal.sistemaFINAL.service;

import com.SistemaEstudiantesFinal.sistemaFINAL.models.Estudiante;
import java.util.List;

public interface EstudianteService {
    abstract void agregarEstudiante(Estudiante estudiante);
    abstract Estudiante getEstudiante(int id);
    abstract List<Estudiante> getEstudiantes();
    abstract void eliminar(int id);
    abstract Estudiante updateEstudiante(int id, Estudiante estudiante);
    abstract Estudiante updateParcial(int id, Estudiante estudiante);
}

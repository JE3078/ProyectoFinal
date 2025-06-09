package com.SistemaEstudiantesFinal.sistemaFINAL.service;

import com.SistemaEstudiantesFinal.sistemaFINAL.models.Estudiante;
import java.util.List;

public interface EstudianteService {
    void agregarEstudiante(Estudiante estudiante);
    Estudiante getEstudiante(int id);
    List<Estudiante> getEstudiantes();
    void eliminar(int id);
    Estudiante updateEstudiante(int id, Estudiante estudiante);
    Estudiante updateParcial(int id, Estudiante estudiante);
}

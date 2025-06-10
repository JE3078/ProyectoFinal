package com.SistemaEstudiantesFinal.sistemaFINAL.controllers;

import com.SistemaEstudiantesFinal.sistemaFINAL.models.Estudiante;
import com.SistemaEstudiantesFinal.sistemaFINAL.service.EstudianteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    // Obtener todos los estudiantes
    @GetMapping
    public List<Estudiante> getEstudiantes() {
        return estudianteService.getEstudiantes();
    }

    // Obtener un estudiante por ID
    @GetMapping("/{id}")
    public Estudiante getEstudiante(@PathVariable int id) {
        return estudianteService.getEstudiante(id);
    }

    // Registrar nuevo estudiante
    @PostMapping("/store")
    public ResponseEntity<String> registrarEstudiante(@RequestBody Estudiante estudiante) {
        estudianteService.agregarEstudiante(estudiante);
        return ResponseEntity.ok("Estudiante registrado exitosamente");
    }

    // Actualización 
    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> editarEstudiante(@PathVariable int id, @RequestBody Estudiante estudiante) {
        Estudiante actualizado = estudianteService.updateEstudiante(id, estudiante);
        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }

    // Actualización parcial
    @PatchMapping("/{id}")
    public ResponseEntity<Estudiante> editarParcial(@PathVariable int id, @RequestBody Estudiante estudiante) {
        Estudiante actualizado = estudianteService.updateParcial(id, estudiante);
        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }

    // Eliminar estudiante
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        estudianteService.eliminar(id);
    }
}

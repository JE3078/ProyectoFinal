package com.SistemaEstudiantesFinal.sistemaFINAL.service;

import com.SistemaEstudiantesFinal.sistemaFINAL.dao.EstudianteDao;
import com.SistemaEstudiantesFinal.sistemaFINAL.models.Estudiante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EstudianteServiceImpTest {

    @Mock
    private EstudianteDao estudianteDao;

    @InjectMocks
    private EstudianteServiceImp estudianteService;

    @MockBean
    private List<Estudiante> listaEstudiantes;

    @MockBean
    private Estudiante estudiantePorId;

    //private int idEstudiante;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Estudiante e1 = new Estudiante();
        e1.setIdEstudiante(1);
        e1.setNombreCompleto("Jose Emilio Us Paau");
        e1.setCorreo("emilio@gmail.com");
        e1.setTelefono("1234567890");
        e1.setIdIdioma(1);

        Estudiante e2 = new Estudiante();
        e2.setIdEstudiante(2);
        e2.setNombreCompleto("Erick Estuardo Rubín García");
        e2.setCorreo("erick@yahoo.com");
        e2.setTelefono("1122334455");
        e2.setIdIdioma(2);

        Estudiante e3 = new Estudiante();
        e3.setIdEstudiante(3);
        e3.setNombreCompleto("Angel Adrian Andrade Bamaca");
        e3.setCorreo("aaab@gmail.com");
        e3.setTelefono("0987654321");
        e3.setIdIdioma(3);

        listaEstudiantes = new ArrayList<>();
        listaEstudiantes.add(e1);
        listaEstudiantes.add(e2);
        listaEstudiantes.add(e3);
    }

//    @Test
//    void agregarEstudiante() {
//        Estudiante nuevoEstudiante = new Estudiante();
//        nuevoEstudiante.setIdEstudiante(4);
//        nuevoEstudiante.setNombreCompleto("Dylan");
//        nuevoEstudiante.setCorreo("dolan@yahoo.com");
//        nuevoEstudiante.setTelefono("2233445566");
//        nuevoEstudiante.setIdIdioma(2);
//
//        listaEstudiantes.add(nuevoEstudiante);
//
//        verify(estudianteDao, times(1)).agregarEstudiante(nuevoEstudiante);
//    }

    @Test
    void getEstudiante() {
        Estudiante e1;
        when(estudianteDao.getEstudiante(1)).thenReturn(estudiantePorId);

        Estudiante estudiante1 = estudianteService.getEstudiante(1);

        assertEquals(estudiantePorId, estudiante1);
        verify(estudianteDao, times(1)).getEstudiante(1);

    }

    @Test
    public void testGetEstudiantes() {
        when(estudianteDao.getEstudiantes()).thenReturn(listaEstudiantes);

        List<Estudiante> resultado = estudianteService.getEstudiantes();

        assertEquals(3, resultado.size());
        verify(estudianteDao, times(1)).getEstudiantes();
    }

    @Test
    void eliminar() {
        int idE3 = 3;
        doNothing().when(estudianteDao).eliminarEstudiante(idE3);

        estudianteService.eliminar(idE3);
    }
//
//    @Test
//    void updateEstudiante() {
//
//
//    }
//
//    @Test
//    void updateParcial() {
//    }
}
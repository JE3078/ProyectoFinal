package com.SistemaEstudiantesFinal.sistemaFINAL.dao;

import com.SistemaEstudiantesFinal.sistemaFINAL.models.Estudiante;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EstudianteDaoImpTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private EstudianteDaoImp estudianteDao;

    private Estudiante estudiante;

    @BeforeEach
    void setUp() {
        estudiante = new Estudiante();
        estudiante.setIdEstudiante(5);
        estudiante.setNombreCompleto("Pedro Folgar Peña");
        estudiante.setCorreo("pedro@gmail.com");
        estudiante.setTelefono("1234567890");
        estudiante.setIdIdioma(1);
    }

    @Test
    void getEstudiantes() {
        // Crear lista de estudiantes simulada
        Estudiante e1 = new Estudiante();
        e1.setIdEstudiante(1);
        e1.setNombreCompleto("Emilio");
        e1.setTelefono("1234567890");
        e1.setCorreo("emilio@yahoo.es");
        e1.setIdIdioma(1);

        Estudiante e2 = new Estudiante();
        e2.setIdEstudiante(2);
        e2.setNombreCompleto("Erick");
        e2.setTelefono("1234567890");
        e2.setCorreo("pato@outlook.com");
        e2.setIdIdioma(3);

        List<Estudiante> estudiantesMock = List.of(e1, e2);

        // Simula el query sin tipado
        TypedQuery queryMock = mock(TypedQuery.class); // sin tipado genérico
        when(entityManager.createQuery("FROM Estudiante")).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(estudiantesMock);

        // Ejecutar el método
        List<Estudiante> resultado = estudianteDao.getEstudiantes();

        // Verificaciones
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Emilio", resultado.get(0).getNombreCompleto());

        verify(entityManager).createQuery("FROM Estudiante");
        verify(queryMock).getResultList();
    }


    @Test
    void testGetEstudiante() {}

    @Test
    void eliminarEstudiante() {}

    @Test
    void agregarEstudiante() {}

    @Test
    void updateEstudiante() {}

    @Test
    void updateParcial() {}
}

package com.SistemaEstudiantesFinal.sistemaFINAL.controllers;

import com.SistemaEstudiantesFinal.sistemaFINAL.controllers.EstudianteController;
import com.SistemaEstudiantesFinal.sistemaFINAL.models.Estudiante;
import com.SistemaEstudiantesFinal.sistemaFINAL.service.EstudianteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.SistemaEstudiantesFinal.sistemaFINAL.controllers.EstudianteController;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = EstudianteController.class)
class EstudianteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EstudianteService estudianteService;

    @Test
    void testGetEstudiantes() throws Exception {
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

        List<Estudiante> lista = List.of(e1, e2);
        when(estudianteService.getEstudiantes()).thenReturn(lista);

        mockMvc.perform(get("/api/estudiantes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombreCompleto", is("Emilio")))
                .andExpect(jsonPath("$[1].correo", is("pato@outlook.com")));

        }

}

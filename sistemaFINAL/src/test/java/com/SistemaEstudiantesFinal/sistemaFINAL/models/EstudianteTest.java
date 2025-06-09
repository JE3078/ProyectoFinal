package com.SistemaEstudiantesFinal.sistemaFINAL.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstudianteTest {

    @Test
    public void testToString() {
        Estudiante emilio = new Estudiante();
        emilio.setIdEstudiante(1);
        emilio.setNombreCompleto("Jose Emilio Us Paau");
        emilio.setCorreo("emilio@gmail.com");
        emilio.setTelefono("1234567890");
        emilio.setIdIdioma(1);


        String idEsperado = "1";
        String idObtenido = String.valueOf(emilio.getIdEstudiante());
        assertEquals(idEsperado, idObtenido);


        String nombreEsperado = "jose emilio us paau";
        String nombreObtenido = emilio.getNombreCompleto().toLowerCase();
        assertEquals(nombreEsperado, nombreObtenido);

        String correoEsperado = "emilio@gmail.com";
        String correoObtenido = emilio.getCorreo();
        assertEquals(correoEsperado, correoObtenido);

        String telefonoEsperado = "1234567890";
        String telefonoObtenido = emilio.getTelefono();
        assertEquals(telefonoEsperado, telefonoObtenido);

        String idIdiomaEsperado = "1";
        String idIdiomaObtenido = String.valueOf(emilio.getIdIdioma());
        assertEquals(idIdiomaEsperado, idIdiomaObtenido);
    }
}
package com.SistemaEstudiantesFinal.sistemaFINAL.service;

import com.SistemaEstudiantesFinal.sistemaFINAL.dao.EstudianteDao;
import com.SistemaEstudiantesFinal.sistemaFINAL.models.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class EstudianteServiceImp implements EstudianteService {

    @Autowired
    private EstudianteDao estudianteDao;

    private boolean correoValido(String correo) {
        return correo != null && correo.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    }

    private boolean telefonoValido(String telefono) {
        return telefono != null && telefono.matches("^\\d{10}$");
    }

    private boolean idiomaValido(Integer idIdioma) {
        return idIdioma != null && (idIdioma == 1 || idIdioma == 2 || idIdioma == 3);
    }

    @Override
    public void agregarEstudiante(Estudiante estudiante) {
        if (!correoValido(estudiante.getCorreo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo tiene un formato inválido.");
        }

        if (!telefonoValido(estudiante.getTelefono())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El numero de teléfono debe tener 10 dígitos.");
        }

        if (!idiomaValido(estudiante.getIdIdioma())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El idioma seleccionado no es valido.");
        }

        boolean existeCorreo = estudianteDao.getEstudiantes().stream()
                .anyMatch(e -> e.getCorreo().equalsIgnoreCase(estudiante.getCorreo()));

        if (existeCorreo) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El correo ingresado ya existe.");
        }

        estudianteDao.agregarEstudiante(estudiante);
    }

    @Override
    public Estudiante getEstudiante(int id) {
        return estudianteDao.getEstudiante(id);
    }

    @Override
    public List<Estudiante> getEstudiantes() {
        return estudianteDao.getEstudiantes();
    }

    @Override
    public void eliminar(int id) {
        estudianteDao.eliminarEstudiante(id);
    }

    @Override
    public Estudiante updateEstudiante(int id, Estudiante estudiante) {
        if (!correoValido(estudiante.getCorreo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo tiene un formato inválido.");
        }

        if (!telefonoValido(estudiante.getTelefono())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El número de teléfono debe tener 10 dígitos.");
        }

        if (!idiomaValido(estudiante.getIdIdioma())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El idioma seleccionado no es válido.");
        }

        boolean correoDuplicado = estudianteDao.getEstudiantes().stream()
                .anyMatch(e -> e.getCorreo().equalsIgnoreCase(estudiante.getCorreo()) && e.getIdEstudiante() != id);

        if (correoDuplicado) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El correo ya está en uso por otro estudiante.");
        }

        return estudianteDao.updateEstudiante(estudiante, id);
    }

    @Override
    public Estudiante updateParcial(int id, Estudiante estudiante) {
        if (estudiante.getCorreo() != null) {
            if (!correoValido(estudiante.getCorreo())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo tiene un formato inválido.");
            }

            boolean correoDuplicado = estudianteDao.getEstudiantes().stream()
                    .anyMatch(e -> e.getCorreo().equalsIgnoreCase(estudiante.getCorreo()) && e.getIdEstudiante() != id);

            if (correoDuplicado) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "El correo ya está en uso por otro estudiante.");
            }
        }

        if (estudiante.getTelefono() != null && !telefonoValido(estudiante.getTelefono())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El número de teléfono debe tener 10 dígitos.");
        }


        return estudianteDao.updateParcial(estudiante, id);
    }
}

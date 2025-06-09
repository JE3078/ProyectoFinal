package com.SistemaEstudiantesFinal.sistemaFINAL.dao;

import com.SistemaEstudiantesFinal.sistemaFINAL.models.Estudiante;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository//conexion con db
@Transactional//transact sql
public class EstudianteDaoImp implements EstudianteDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Estudiante> getEstudiantes() {
        String query = "FROM Estudiante";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Estudiante getEstudiante(int id) {
        String query = "FROM Estudiante e WHERE e.idEstudiante = :id";
        return entityManager.createQuery(query, Estudiante.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public void eliminarEstudiante(int id) {
        Estudiante estudiante = entityManager.find(Estudiante.class, id);
        entityManager.remove(estudiante);
    }

    @Override
    public void agregarEstudiante(Estudiante estudiante) {
        entityManager.persist(estudiante);
    }

    @Override
    public Estudiante updateEstudiante(Estudiante estudiante, int id) {
        Estudiante existeEstudiante = entityManager.find(Estudiante.class, id);

        if (existeEstudiante != null) {
            int verId = existeEstudiante.getIdEstudiante();
            existeEstudiante.setNombreCompleto(estudiante.getNombreCompleto());
            existeEstudiante.setCorreo(estudiante.getCorreo());
            existeEstudiante.setTelefono(estudiante.getTelefono());
            existeEstudiante.setIdIdioma(estudiante.getIdIdioma());

            entityManager.merge(existeEstudiante);
        }

        return existeEstudiante;
    }

    @Override
    public Estudiante updateParcial(Estudiante estudiante, int id) {
        Estudiante existeEstudiante = entityManager.find(Estudiante.class, id);

        if (existeEstudiante != null) {
            String nombre = existeEstudiante.getNombreCompleto();
            existeEstudiante.setCorreo(estudiante.getCorreo());
            existeEstudiante.setTelefono(estudiante.getTelefono());
            existeEstudiante.setIdIdioma(estudiante.getIdIdioma());

            entityManager.merge(existeEstudiante);
        }

        return existeEstudiante;
    }

}

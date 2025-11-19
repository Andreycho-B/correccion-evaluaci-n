package com.andrey.sistema_citas.repository;

import com.andrey.sistema_citas.entity.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para gestionar operaciones de base de datos relacionadas con profesionales.
 * Extiende JpaRepository para proporcionar operaciones CRUD estándar.
 */
@Repository
public interface ProfesionalRepository extends JpaRepository<Profesional, Integer> {

    /**
     * Busca profesionales por especialidad.
     * Útil para filtrar profesionales según el tipo de servicio requerido.
     */
    List<Profesional> findByEspecialidad(String especialidad);

    /**
     * Busca un profesional por el ID del usuario asociado.
     * Útil para verificar si un usuario ya tiene un perfil profesional.
     */
    Optional<Profesional> findByUsuarioId(Integer usuarioId);

    /**
     * Verifica si existe un profesional asociado a un usuario específico.
     * Útil para validaciones antes de crear nuevos perfiles profesionales.
     */
    boolean existsByUsuarioId(Integer usuarioId);
}

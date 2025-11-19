package com.andrey.sistema_citas.repository;

import com.andrey.sistema_citas.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para gestionar operaciones de base de datos relacionadas con usuarios.
 * Extiende JpaRepository para proporcionar operaciones CRUD estándar.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    /**
     * Busca un usuario por su dirección de correo electrónico.
     * Útil para autenticación y validación de unicidad.
     */
    Optional<Usuario> findByEmail(String email);

    /**
     * Verifica si existe un usuario con el email especificado.
     * Útil para validaciones antes de crear nuevos usuarios.
     */
    boolean existsByEmail(String email);
}

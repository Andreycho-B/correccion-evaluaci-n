package com.andrey.sistema_citas.repository;

import com.andrey.sistema_citas.entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para gestionar operaciones de base de datos relacionadas con servicios.
 * Extiende JpaRepository para proporcionar operaciones CRUD estándar.
 */
@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer> {

    /**
     * Busca servicios por nombre (búsqueda parcial, sin distinción de mayúsculas).
     * Útil para funcionalidades de búsqueda y filtrado.
     */
    List<Servicio> findByNombreContainingIgnoreCase(String nombre);

    /**
     * Busca servicios dentro de un rango de precios.
     * Útil para filtrar servicios según el presupuesto del cliente.
     */
    List<Servicio> findByPrecioBetween(Double precioMin, Double precioMax);
}

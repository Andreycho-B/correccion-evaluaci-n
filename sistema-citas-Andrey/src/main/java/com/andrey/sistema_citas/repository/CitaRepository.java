package com.andrey.sistema_citas.repository;

import com.andrey.sistema_citas.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repositorio para gestionar operaciones de base de datos relacionadas con citas.
 * Extiende JpaRepository para proporcionar operaciones CRUD estándar.
 */
@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {

    /**
     * Busca citas por usuario.
     * Útil para mostrar el historial de citas de un cliente específico.
     */
    List<Cita> findByUsuarioId(Integer usuarioId);

    /**
     * Busca citas por profesional.
     * Útil para mostrar la agenda de un profesional específico.
     */
    List<Cita> findByProfesionalId(Integer profesionalId);

    /**
     * Busca citas por servicio.
     * Útil para análisis de demanda de servicios específicos.
     */
    List<Cita> findByServicioId(Integer servicioId);

    /**
     * Busca citas por estado.
     * Útil para filtrar citas pendientes, confirmadas, canceladas, etc.
     */
    List<Cita> findByEstado(String estado);

    /**
     * Busca citas en un rango de fechas.
     * Útil para generar reportes y visualizar la agenda por períodos.
     */
    List<Cita> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);

    /**
     * Busca citas por usuario y estado.
     * Útil para mostrar citas activas o historial específico de un usuario.
     */
    List<Cita> findByUsuarioIdAndEstado(Integer usuarioId, String estado);
}

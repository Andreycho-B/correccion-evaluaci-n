package com.andrey.sistema_citas.util;

import com.andrey.sistema_citas.dto.*;
import com.andrey.sistema_citas.entity.*;

/**
 * Clase de utilidad para mapear entidades a DTOs y viceversa.
 * Centraliza la lógica de conversión para mantener consistencia en toda la aplicación.
 */
public class EntityMapper {

    /**
     * Convierte una entidad Usuario a UsuarioDTO.
     */
    public static UsuarioDTO toUsuarioDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getTelefono(),
                usuario.getFechaRegistro(),
                usuario.getRol()
        );
    }

    /**
     * Convierte un UsuarioDTO a entidad Usuario.
     * No incluye la contraseña, que debe manejarse por separado.
     */
    public static Usuario toUsuario(UsuarioDTO dto) {
        if (dto == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefono(dto.getTelefono());
        usuario.setFechaRegistro(dto.getFechaRegistro());
        usuario.setRol(dto.getRol());
        return usuario;
    }

    /**
     * Convierte una entidad Profesional a ProfesionalDTO.
     */
    public static ProfesionalDTO toProfesionalDTO(Profesional profesional) {
        if (profesional == null) {
            return null;
        }
        return new ProfesionalDTO(
                profesional.getId(),
                profesional.getEspecialidad(),
                profesional.getHorarioDisponible(),
                profesional.getUsuario() != null ? profesional.getUsuario().getId() : null,
                profesional.getUsuario() != null ? profesional.getUsuario().getNombre() : null
        );
    }

    /**
     * Convierte un ProfesionalDTO a entidad Profesional.
     * El usuario debe ser asignado por separado.
     */
    public static Profesional toProfesional(ProfesionalDTO dto) {
        if (dto == null) {
            return null;
        }
        Profesional profesional = new Profesional();
        profesional.setId(dto.getId());
        profesional.setEspecialidad(dto.getEspecialidad());
        profesional.setHorarioDisponible(dto.getHorarioDisponible());
        return profesional;
    }

    /**
     * Convierte una entidad Servicio a ServicioDTO.
     */
    public static ServicioDTO toServicioDTO(Servicio servicio) {
        if (servicio == null) {
            return null;
        }
        return new ServicioDTO(
                servicio.getId(),
                servicio.getNombre(),
                servicio.getDescripcion(),
                servicio.getDuracion(),
                servicio.getPrecio()
        );
    }

    /**
     * Convierte un ServicioDTO a entidad Servicio.
     */
    public static Servicio toServicio(ServicioDTO dto) {
        if (dto == null) {
            return null;
        }
        Servicio servicio = new Servicio();
        servicio.setId(dto.getId());
        servicio.setNombre(dto.getNombre());
        servicio.setDescripcion(dto.getDescripcion());
        servicio.setDuracion(dto.getDuracion());
        servicio.setPrecio(dto.getPrecio());
        return servicio;
    }

    /**
     * Convierte una entidad Cita a CitaDTO.
     */
    public static CitaDTO toCitaDTO(Cita cita) {
        if (cita == null) {
            return null;
        }
        return new CitaDTO(
                cita.getId(),
                cita.getFechaHora(),
                cita.getEstado(),
                cita.getUsuario() != null ? cita.getUsuario().getId() : null,
                cita.getUsuario() != null ? cita.getUsuario().getNombre() : null,
                cita.getServicio() != null ? cita.getServicio().getId() : null,
                cita.getServicio() != null ? cita.getServicio().getNombre() : null,
                cita.getProfesional() != null ? cita.getProfesional().getId() : null,
                cita.getProfesional() != null ? cita.getProfesional().getUsuario().getNombre() : null
        );
    }

    /**
     * Convierte un CitaDTO a entidad Cita.
     * Las relaciones deben ser asignadas por separado.
     */
    public static Cita toCita(CitaDTO dto) {
        if (dto == null) {
            return null;
        }
        Cita cita = new Cita();
        cita.setId(dto.getId());
        cita.setFechaHora(dto.getFechaHora());
        cita.setEstado(dto.getEstado());
        return cita;
    }
}

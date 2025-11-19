package com.andrey.sistema_citas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * DTO para transferir información de profesionales entre capas.
 * Incluye validaciones para garantizar la integridad de los datos.
 */
public class ProfesionalDTO {

    private Integer id;

    @NotBlank(message = "La especialidad es obligatoria")
    @Size(max = 255, message = "La especialidad no puede exceder 255 caracteres")
    private String especialidad;

    private LocalDateTime horarioDisponible;

    @NotNull(message = "El usuario asociado es obligatorio")
    private Integer usuarioId;

    private String usuarioNombre;

    public ProfesionalDTO() {
    }

    public ProfesionalDTO(Integer id, String especialidad, LocalDateTime horarioDisponible, Integer usuarioId, String usuarioNombre) {
        this.id = id;
        this.especialidad = especialidad;
        this.horarioDisponible = horarioDisponible;
        this.usuarioId = usuarioId;
        this.usuarioNombre = usuarioNombre;
    }

    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public LocalDateTime getHorarioDisponible() {
        return horarioDisponible;
    }

    public void setHorarioDisponible(LocalDateTime horarioDisponible) {
        this.horarioDisponible = horarioDisponible;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    @Override
    public String toString() {
        return "ProfesionalDTO{" +
                "id=" + id +
                ", especialidad='" + especialidad + '\'' +
                ", horarioDisponible=" + horarioDisponible +
                ", usuarioId=" + usuarioId +
                ", usuarioNombre='" + usuarioNombre + '\'' +
                '}';
    }
}

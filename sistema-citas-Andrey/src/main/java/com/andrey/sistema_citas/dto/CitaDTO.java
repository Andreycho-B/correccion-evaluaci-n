package com.andrey.sistema_citas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * DTO para transferir información de citas entre capas.
 * Incluye validaciones para garantizar la integridad de los datos.
 */
public class CitaDTO {

    private Integer id;

    @NotNull(message = "La fecha y hora de la cita son obligatorias")
    private LocalDateTime fechaHora;

    @NotBlank(message = "El estado de la cita es obligatorio")
    @Size(max = 255, message = "El estado no puede exceder 255 caracteres")
    private String estado;

    @NotNull(message = "El usuario es obligatorio")
    private Integer usuarioId;

    private String usuarioNombre;

    @NotNull(message = "El servicio es obligatorio")
    private Integer servicioId;

    private String servicioNombre;

    @NotNull(message = "El profesional es obligatorio")
    private Integer profesionalId;

    private String profesionalNombre;

    public CitaDTO() {
    }

    public CitaDTO(Integer id, LocalDateTime fechaHora, String estado, Integer usuarioId, String usuarioNombre,
                   Integer servicioId, String servicioNombre, Integer profesionalId, String profesionalNombre) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.usuarioId = usuarioId;
        this.usuarioNombre = usuarioNombre;
        this.servicioId = servicioId;
        this.servicioNombre = servicioNombre;
        this.profesionalId = profesionalId;
        this.profesionalNombre = profesionalNombre;
    }

    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public Integer getServicioId() {
        return servicioId;
    }

    public void setServicioId(Integer servicioId) {
        this.servicioId = servicioId;
    }

    public String getServicioNombre() {
        return servicioNombre;
    }

    public void setServicioNombre(String servicioNombre) {
        this.servicioNombre = servicioNombre;
    }

    public Integer getProfesionalId() {
        return profesionalId;
    }

    public void setProfesionalId(Integer profesionalId) {
        this.profesionalId = profesionalId;
    }

    public String getProfesionalNombre() {
        return profesionalNombre;
    }

    public void setProfesionalNombre(String profesionalNombre) {
        this.profesionalNombre = profesionalNombre;
    }

    @Override
    public String toString() {
        return "CitaDTO{" +
                "id=" + id +
                ", fechaHora=" + fechaHora +
                ", estado='" + estado + '\'' +
                ", usuarioId=" + usuarioId +
                ", usuarioNombre='" + usuarioNombre + '\'' +
                ", servicioId=" + servicioId +
                ", servicioNombre='" + servicioNombre + '\'' +
                ", profesionalId=" + profesionalId +
                ", profesionalNombre='" + profesionalNombre + '\'' +
                '}';
    }
}

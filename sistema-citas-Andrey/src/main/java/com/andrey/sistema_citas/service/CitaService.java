package com.andrey.sistema_citas.service;

import com.andrey.sistema_citas.dto.CitaDTO;
import com.andrey.sistema_citas.entity.Cita;
import com.andrey.sistema_citas.entity.Profesional;
import com.andrey.sistema_citas.entity.Servicio;
import com.andrey.sistema_citas.entity.Usuario;
import com.andrey.sistema_citas.exception.ResourceNotFoundException;
import com.andrey.sistema_citas.repository.CitaRepository;
import com.andrey.sistema_citas.repository.ProfesionalRepository;
import com.andrey.sistema_citas.repository.ServicioRepository;
import com.andrey.sistema_citas.repository.UsuarioRepository;
import com.andrey.sistema_citas.util.EntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio que gestiona la lógica de negocio relacionada con citas.
 * Implementa operaciones CRUD y validaciones de negocio.
 */
@Service
@Transactional
public class CitaService {

    private static final Logger logger = LoggerFactory.getLogger(CitaService.class);

    private final CitaRepository citaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ServicioRepository servicioRepository;
    private final ProfesionalRepository profesionalRepository;

    public CitaService(CitaRepository citaRepository, UsuarioRepository usuarioRepository,
                       ServicioRepository servicioRepository, ProfesionalRepository profesionalRepository) {
        this.citaRepository = citaRepository;
        this.usuarioRepository = usuarioRepository;
        this.servicioRepository = servicioRepository;
        this.profesionalRepository = profesionalRepository;
    }

    /**
     * Obtiene todas las citas del sistema.
     */
    @Transactional(readOnly = true)
    public List<CitaDTO> obtenerTodas() {
        logger.debug("Obteniendo todas las citas");
        return citaRepository.findAll().stream()
                .map(EntityMapper::toCitaDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene una cita por su ID.
     */
    @Transactional(readOnly = true)
    public CitaDTO obtenerPorId(Integer id) {
        logger.debug("Obteniendo cita con ID: {}", id);
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada con ID: " + id));
        return EntityMapper.toCitaDTO(cita);
    }

    /**
     * Obtiene citas por usuario.
     */
    @Transactional(readOnly = true)
    public List<CitaDTO> obtenerPorUsuario(Integer usuarioId) {
        logger.debug("Obteniendo citas del usuario con ID: {}", usuarioId);
        return citaRepository.findByUsuarioId(usuarioId).stream()
                .map(EntityMapper::toCitaDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene citas por profesional.
     */
    @Transactional(readOnly = true)
    public List<CitaDTO> obtenerPorProfesional(Integer profesionalId) {
        logger.debug("Obteniendo citas del profesional con ID: {}", profesionalId);
        return citaRepository.findByProfesionalId(profesionalId).stream()
                .map(EntityMapper::toCitaDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene citas por estado.
     */
    @Transactional(readOnly = true)
    public List<CitaDTO> obtenerPorEstado(String estado) {
        logger.debug("Obteniendo citas con estado: {}", estado);
        return citaRepository.findByEstado(estado).stream()
                .map(EntityMapper::toCitaDTO)
                .collect(Collectors.toList());
    }

    /**
     * Crea una nueva cita en el sistema.
     */
    public CitaDTO crear(CitaDTO citaDTO) {
        logger.debug("Creando nueva cita para usuario ID: {}", citaDTO.getUsuarioId());

        Usuario usuario = usuarioRepository.findById(citaDTO.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + citaDTO.getUsuarioId()));

        Servicio servicio = servicioRepository.findById(citaDTO.getServicioId())
                .orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado con ID: " + citaDTO.getServicioId()));

        Profesional profesional = profesionalRepository.findById(citaDTO.getProfesionalId())
                .orElseThrow(() -> new ResourceNotFoundException("Profesional no encontrado con ID: " + citaDTO.getProfesionalId()));

        Cita cita = EntityMapper.toCita(citaDTO);
        cita.setUsuario(usuario);
        cita.setServicio(servicio);
        cita.setProfesional(profesional);

        if (cita.getEstado() == null || cita.getEstado().isEmpty()) {
            cita.setEstado("PENDIENTE");
        }

        Cita citaGuardada = citaRepository.save(cita);
        logger.info("Cita creada exitosamente con ID: {}", citaGuardada.getId());

        return EntityMapper.toCitaDTO(citaGuardada);
    }

    /**
     * Actualiza la información de una cita existente.
     */
    public CitaDTO actualizar(Integer id, CitaDTO citaDTO) {
        logger.debug("Actualizando cita con ID: {}", id);

        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada con ID: " + id));

        cita.setFechaHora(citaDTO.getFechaHora());
        cita.setEstado(citaDTO.getEstado());

        if (citaDTO.getUsuarioId() != null && !cita.getUsuario().getId().equals(citaDTO.getUsuarioId())) {
            Usuario usuario = usuarioRepository.findById(citaDTO.getUsuarioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + citaDTO.getUsuarioId()));
            cita.setUsuario(usuario);
        }

        if (citaDTO.getServicioId() != null && !cita.getServicio().getId().equals(citaDTO.getServicioId())) {
            Servicio servicio = servicioRepository.findById(citaDTO.getServicioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado con ID: " + citaDTO.getServicioId()));
            cita.setServicio(servicio);
        }

        if (citaDTO.getProfesionalId() != null && !cita.getProfesional().getId().equals(citaDTO.getProfesionalId())) {
            Profesional profesional = profesionalRepository.findById(citaDTO.getProfesionalId())
                    .orElseThrow(() -> new ResourceNotFoundException("Profesional no encontrado con ID: " + citaDTO.getProfesionalId()));
            cita.setProfesional(profesional);
        }

        Cita citaActualizada = citaRepository.save(cita);
        logger.info("Cita actualizada exitosamente con ID: {}", citaActualizada.getId());

        return EntityMapper.toCitaDTO(citaActualizada);
    }

    /**
     * Elimina una cita del sistema.
     */
    public void eliminar(Integer id) {
        logger.debug("Eliminando cita con ID: {}", id);

        if (!citaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cita no encontrada con ID: " + id);
        }

        citaRepository.deleteById(id);
        logger.info("Cita eliminada exitosamente con ID: {}", id);
    }
}

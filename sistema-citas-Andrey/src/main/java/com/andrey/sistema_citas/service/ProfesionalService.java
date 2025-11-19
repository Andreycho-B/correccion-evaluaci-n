package com.andrey.sistema_citas.service;

import com.andrey.sistema_citas.dto.ProfesionalDTO;
import com.andrey.sistema_citas.entity.Profesional;
import com.andrey.sistema_citas.entity.Usuario;
import com.andrey.sistema_citas.exception.ResourceNotFoundException;
import com.andrey.sistema_citas.exception.DuplicateResourceException;
import com.andrey.sistema_citas.repository.ProfesionalRepository;
import com.andrey.sistema_citas.repository.UsuarioRepository;
import com.andrey.sistema_citas.util.EntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio que gestiona la lógica de negocio relacionada con profesionales.
 * Implementa operaciones CRUD y validaciones de negocio.
 */
@Service
@Transactional
public class ProfesionalService {

    private static final Logger logger = LoggerFactory.getLogger(ProfesionalService.class);

    private final ProfesionalRepository profesionalRepository;
    private final UsuarioRepository usuarioRepository;

    public ProfesionalService(ProfesionalRepository profesionalRepository, UsuarioRepository usuarioRepository) {
        this.profesionalRepository = profesionalRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Obtiene todos los profesionales del sistema.
     */
    @Transactional(readOnly = true)
    public List<ProfesionalDTO> obtenerTodos() {
        logger.debug("Obteniendo todos los profesionales");
        return profesionalRepository.findAll().stream()
                .map(EntityMapper::toProfesionalDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene un profesional por su ID.
     */
    @Transactional(readOnly = true)
    public ProfesionalDTO obtenerPorId(Integer id) {
        logger.debug("Obteniendo profesional con ID: {}", id);
        Profesional profesional = profesionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profesional no encontrado con ID: " + id));
        return EntityMapper.toProfesionalDTO(profesional);
    }

    /**
     * Obtiene profesionales por especialidad.
     */
    @Transactional(readOnly = true)
    public List<ProfesionalDTO> obtenerPorEspecialidad(String especialidad) {
        logger.debug("Obteniendo profesionales con especialidad: {}", especialidad);
        return profesionalRepository.findByEspecialidad(especialidad).stream()
                .map(EntityMapper::toProfesionalDTO)
                .collect(Collectors.toList());
    }

    /**
     * Crea un nuevo profesional en el sistema.
     */
    public ProfesionalDTO crear(ProfesionalDTO profesionalDTO) {
        logger.debug("Creando nuevo profesional para usuario ID: {}", profesionalDTO.getUsuarioId());

        Usuario usuario = usuarioRepository.findById(profesionalDTO.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + profesionalDTO.getUsuarioId()));

        // Permitir múltiples profesionales por usuario

        Profesional profesional = EntityMapper.toProfesional(profesionalDTO);
        profesional.setUsuario(usuario);

        Profesional profesionalGuardado = profesionalRepository.save(profesional);
        logger.info("Profesional creado exitosamente con ID: {}", profesionalGuardado.getId());

        return EntityMapper.toProfesionalDTO(profesionalGuardado);
    }

    /**
     * Actualiza la información de un profesional existente.
     */
    public ProfesionalDTO actualizar(Integer id, ProfesionalDTO profesionalDTO) {
        logger.debug("Actualizando profesional con ID: {}", id);

        Profesional profesional = profesionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profesional no encontrado con ID: " + id));

        profesional.setEspecialidad(profesionalDTO.getEspecialidad());
        profesional.setHorarioDisponible(profesionalDTO.getHorarioDisponible());

        if (profesionalDTO.getUsuarioId() != null && !profesional.getUsuario().getId().equals(profesionalDTO.getUsuarioId())) {
            Usuario nuevoUsuario = usuarioRepository.findById(profesionalDTO.getUsuarioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + profesionalDTO.getUsuarioId()));
            
            // Permitir múltiples profesionales por usuario
            
            profesional.setUsuario(nuevoUsuario);
        }

        Profesional profesionalActualizado = profesionalRepository.save(profesional);
        logger.info("Profesional actualizado exitosamente con ID: {}", profesionalActualizado.getId());

        return EntityMapper.toProfesionalDTO(profesionalActualizado);
    }

    /**
     * Elimina un profesional del sistema.
     */
    public void eliminar(Integer id) {
        logger.debug("Eliminando profesional con ID: {}", id);

        if (!profesionalRepository.existsById(id)) {
            throw new ResourceNotFoundException("Profesional no encontrado con ID: " + id);
        }

        profesionalRepository.deleteById(id);
        logger.info("Profesional eliminado exitosamente con ID: {}", id);
    }
}

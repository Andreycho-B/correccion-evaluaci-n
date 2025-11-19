package com.andrey.sistema_citas.service;

import com.andrey.sistema_citas.dto.ServicioDTO;
import com.andrey.sistema_citas.entity.Servicio;
import com.andrey.sistema_citas.exception.ResourceNotFoundException;
import com.andrey.sistema_citas.repository.ServicioRepository;
import com.andrey.sistema_citas.util.EntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio que gestiona la lógica de negocio relacionada con servicios.
 * Implementa operaciones CRUD y validaciones de negocio.
 */
@Service
@Transactional
public class ServicioService {

    private static final Logger logger = LoggerFactory.getLogger(ServicioService.class);

    private final ServicioRepository servicioRepository;

    public ServicioService(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    /**
     * Obtiene todos los servicios del sistema.
     */
    @Transactional(readOnly = true)
    public List<ServicioDTO> obtenerTodos() {
        logger.debug("Obteniendo todos los servicios");
        return servicioRepository.findAll().stream()
                .map(EntityMapper::toServicioDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene un servicio por su ID.
     */
    @Transactional(readOnly = true)
    public ServicioDTO obtenerPorId(Integer id) {
        logger.debug("Obteniendo servicio con ID: {}", id);
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado con ID: " + id));
        return EntityMapper.toServicioDTO(servicio);
    }

    /**
     * Busca servicios por nombre.
     */
    @Transactional(readOnly = true)
    public List<ServicioDTO> buscarPorNombre(String nombre) {
        logger.debug("Buscando servicios con nombre: {}", nombre);
        return servicioRepository.findByNombreContainingIgnoreCase(nombre).stream()
                .map(EntityMapper::toServicioDTO)
                .collect(Collectors.toList());
    }

    /**
     * Crea un nuevo servicio en el sistema.
     */
    public ServicioDTO crear(ServicioDTO servicioDTO) {
        logger.debug("Creando nuevo servicio: {}", servicioDTO.getNombre());

        Servicio servicio = EntityMapper.toServicio(servicioDTO);
        Servicio servicioGuardado = servicioRepository.save(servicio);

        logger.info("Servicio creado exitosamente con ID: {}", servicioGuardado.getId());
        return EntityMapper.toServicioDTO(servicioGuardado);
    }

    /**
     * Actualiza la información de un servicio existente.
     */
    public ServicioDTO actualizar(Integer id, ServicioDTO servicioDTO) {
        logger.debug("Actualizando servicio con ID: {}", id);

        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado con ID: " + id));

        servicio.setNombre(servicioDTO.getNombre());
        servicio.setDescripcion(servicioDTO.getDescripcion());
        servicio.setDuracion(servicioDTO.getDuracion());
        servicio.setPrecio(servicioDTO.getPrecio());

        Servicio servicioActualizado = servicioRepository.save(servicio);
        logger.info("Servicio actualizado exitosamente con ID: {}", servicioActualizado.getId());

        return EntityMapper.toServicioDTO(servicioActualizado);
    }

    /**
     * Elimina un servicio del sistema.
     */
    public void eliminar(Integer id) {
        logger.debug("Eliminando servicio con ID: {}", id);

        if (!servicioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Servicio no encontrado con ID: " + id);
        }

        servicioRepository.deleteById(id);
        logger.info("Servicio eliminado exitosamente con ID: {}", id);
    }
}

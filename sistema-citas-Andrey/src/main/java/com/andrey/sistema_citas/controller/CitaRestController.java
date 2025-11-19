package com.andrey.sistema_citas.controller;

import com.andrey.sistema_citas.dto.CitaDTO;
import com.andrey.sistema_citas.service.CitaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar operaciones relacionadas con citas.
 * Proporciona endpoints para operaciones CRUD siguiendo estándares REST.
 */
@RestController
@RequestMapping("/api/citas")
public class CitaRestController {

    private static final Logger logger = LoggerFactory.getLogger(CitaRestController.class);

    private final CitaService citaService;

    public CitaRestController(CitaService citaService) {
        this.citaService = citaService;
    }

    /**
     * Obtiene todas las citas del sistema.
     */
    @GetMapping
    public ResponseEntity<List<CitaDTO>> obtenerTodas() {
        logger.debug("GET /api/citas - Obteniendo todas las citas");
        List<CitaDTO> citas = citaService.obtenerTodas();
        return ResponseEntity.ok(citas);
    }

    /**
     * Obtiene una cita específica por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CitaDTO> obtenerPorId(@PathVariable Integer id) {
        logger.debug("GET /api/citas/{} - Obteniendo cita por ID", id);
        CitaDTO cita = citaService.obtenerPorId(id);
        return ResponseEntity.ok(cita);
    }

    /**
     * Obtiene citas por usuario.
     */
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<CitaDTO>> obtenerPorUsuario(@PathVariable Integer usuarioId) {
        logger.debug("GET /api/citas/usuario/{} - Obteniendo citas por usuario", usuarioId);
        List<CitaDTO> citas = citaService.obtenerPorUsuario(usuarioId);
        return ResponseEntity.ok(citas);
    }

    /**
     * Obtiene citas por profesional.
     */
    @GetMapping("/profesional/{profesionalId}")
    public ResponseEntity<List<CitaDTO>> obtenerPorProfesional(@PathVariable Integer profesionalId) {
        logger.debug("GET /api/citas/profesional/{} - Obteniendo citas por profesional", profesionalId);
        List<CitaDTO> citas = citaService.obtenerPorProfesional(profesionalId);
        return ResponseEntity.ok(citas);
    }

    /**
     * Obtiene citas por estado.
     */
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<CitaDTO>> obtenerPorEstado(@PathVariable String estado) {
        logger.debug("GET /api/citas/estado/{} - Obteniendo citas por estado", estado);
        List<CitaDTO> citas = citaService.obtenerPorEstado(estado);
        return ResponseEntity.ok(citas);
    }

    /**
     * Crea una nueva cita en el sistema.
     */
    @PostMapping
    public ResponseEntity<CitaDTO> crear(@Valid @RequestBody CitaDTO citaDTO) {
        logger.debug("POST /api/citas - Creando nueva cita");
        CitaDTO citaCreada = citaService.crear(citaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(citaCreada);
    }

    /**
     * Actualiza la información de una cita existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CitaDTO> actualizar(@PathVariable Integer id, 
                                               @Valid @RequestBody CitaDTO citaDTO) {
        logger.debug("PUT /api/citas/{} - Actualizando cita", id);
        CitaDTO citaActualizada = citaService.actualizar(id, citaDTO);
        return ResponseEntity.ok(citaActualizada);
    }

    /**
     * Elimina una cita del sistema.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        logger.debug("DELETE /api/citas/{} - Eliminando cita", id);
        citaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

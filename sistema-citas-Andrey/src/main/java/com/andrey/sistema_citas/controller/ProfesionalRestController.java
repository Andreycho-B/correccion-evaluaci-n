package com.andrey.sistema_citas.controller;

import com.andrey.sistema_citas.dto.ProfesionalDTO;
import com.andrey.sistema_citas.service.ProfesionalService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar operaciones relacionadas con profesionales.
 * Proporciona endpoints para operaciones CRUD siguiendo estándares REST.
 */
@RestController
@RequestMapping("/api/profesionales")
public class ProfesionalRestController {

    private static final Logger logger = LoggerFactory.getLogger(ProfesionalRestController.class);

    private final ProfesionalService profesionalService;

    public ProfesionalRestController(ProfesionalService profesionalService) {
        this.profesionalService = profesionalService;
    }

    /**
     * Obtiene todos los profesionales del sistema.
     */
    @GetMapping
    public ResponseEntity<List<ProfesionalDTO>> obtenerTodos() {
        logger.debug("GET /api/profesionales - Obteniendo todos los profesionales");
        List<ProfesionalDTO> profesionales = profesionalService.obtenerTodos();
        return ResponseEntity.ok(profesionales);
    }

    /**
     * Obtiene un profesional específico por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProfesionalDTO> obtenerPorId(@PathVariable Integer id) {
        logger.debug("GET /api/profesionales/{} - Obteniendo profesional por ID", id);
        ProfesionalDTO profesional = profesionalService.obtenerPorId(id);
        return ResponseEntity.ok(profesional);
    }

    /**
     * Obtiene profesionales por especialidad.
     */
    @GetMapping("/especialidad/{especialidad}")
    public ResponseEntity<List<ProfesionalDTO>> obtenerPorEspecialidad(@PathVariable String especialidad) {
        logger.debug("GET /api/profesionales/especialidad/{} - Obteniendo profesionales por especialidad", especialidad);
        List<ProfesionalDTO> profesionales = profesionalService.obtenerPorEspecialidad(especialidad);
        return ResponseEntity.ok(profesionales);
    }

    /**
     * Crea un nuevo profesional en el sistema.
     */
    @PostMapping
    public ResponseEntity<ProfesionalDTO> crear(@Valid @RequestBody ProfesionalDTO profesionalDTO) {
        logger.debug("POST /api/profesionales - Creando nuevo profesional");
        ProfesionalDTO profesionalCreado = profesionalService.crear(profesionalDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(profesionalCreado);
    }

    /**
     * Actualiza la información de un profesional existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProfesionalDTO> actualizar(@PathVariable Integer id, 
                                                      @Valid @RequestBody ProfesionalDTO profesionalDTO) {
        logger.debug("PUT /api/profesionales/{} - Actualizando profesional", id);
        ProfesionalDTO profesionalActualizado = profesionalService.actualizar(id, profesionalDTO);
        return ResponseEntity.ok(profesionalActualizado);
    }

    /**
     * Elimina un profesional del sistema.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        logger.debug("DELETE /api/profesionales/{} - Eliminando profesional", id);
        profesionalService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

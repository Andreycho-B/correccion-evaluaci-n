package com.andrey.sistema_citas.controller;

import com.andrey.sistema_citas.dto.ServicioDTO;
import com.andrey.sistema_citas.service.ServicioService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar operaciones relacionadas con servicios.
 * Proporciona endpoints para operaciones CRUD siguiendo estándares REST.
 */
@RestController
@RequestMapping("/api/servicios")
public class ServicioRestController {

    private static final Logger logger = LoggerFactory.getLogger(ServicioRestController.class);

    private final ServicioService servicioService;

    public ServicioRestController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    /**
     * Obtiene todos los servicios del sistema.
     */
    @GetMapping
    public ResponseEntity<List<ServicioDTO>> obtenerTodos() {
        logger.debug("GET /api/servicios - Obteniendo todos los servicios");
        List<ServicioDTO> servicios = servicioService.obtenerTodos();
        return ResponseEntity.ok(servicios);
    }

    /**
     * Obtiene un servicio específico por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ServicioDTO> obtenerPorId(@PathVariable Integer id) {
        logger.debug("GET /api/servicios/{} - Obteniendo servicio por ID", id);
        ServicioDTO servicio = servicioService.obtenerPorId(id);
        return ResponseEntity.ok(servicio);
    }

    /**
     * Busca servicios por nombre.
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<ServicioDTO>> buscarPorNombre(@RequestParam String nombre) {
        logger.debug("GET /api/servicios/buscar?nombre={} - Buscando servicios por nombre", nombre);
        List<ServicioDTO> servicios = servicioService.buscarPorNombre(nombre);
        return ResponseEntity.ok(servicios);
    }

    /**
     * Crea un nuevo servicio en el sistema.
     */
    @PostMapping
    public ResponseEntity<ServicioDTO> crear(@Valid @RequestBody ServicioDTO servicioDTO) {
        logger.debug("POST /api/servicios - Creando nuevo servicio");
        ServicioDTO servicioCreado = servicioService.crear(servicioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(servicioCreado);
    }

    /**
     * Actualiza la información de un servicio existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ServicioDTO> actualizar(@PathVariable Integer id, 
                                                   @Valid @RequestBody ServicioDTO servicioDTO) {
        logger.debug("PUT /api/servicios/{} - Actualizando servicio", id);
        ServicioDTO servicioActualizado = servicioService.actualizar(id, servicioDTO);
        return ResponseEntity.ok(servicioActualizado);
    }

    /**
     * Elimina un servicio del sistema.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        logger.debug("DELETE /api/servicios/{} - Eliminando servicio", id);
        servicioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

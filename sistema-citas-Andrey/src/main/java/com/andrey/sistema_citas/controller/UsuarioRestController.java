package com.andrey.sistema_citas.controller;

import com.andrey.sistema_citas.dto.UsuarioDTO;
import com.andrey.sistema_citas.dto.UsuarioRegistroDTO;
import com.andrey.sistema_citas.service.UsuarioService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar operaciones relacionadas con usuarios.
 * Proporciona endpoints para operaciones CRUD siguiendo estándares REST.
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioRestController.class);

    private final UsuarioService usuarioService;

    public UsuarioRestController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Obtiene todos los usuarios del sistema.
     */
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> obtenerTodos() {
        logger.debug("GET /api/usuarios - Obteniendo todos los usuarios");
        List<UsuarioDTO> usuarios = usuarioService.obtenerTodos();
        return ResponseEntity.ok(usuarios);
    }

    /**
     * Obtiene un usuario específico por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerPorId(@PathVariable Integer id) {
        logger.debug("GET /api/usuarios/{} - Obteniendo usuario por ID", id);
        UsuarioDTO usuario = usuarioService.obtenerPorId(id);
        return ResponseEntity.ok(usuario);
    }

    /**
     * Registra un nuevo usuario en el sistema.
     */
    @PostMapping
    public ResponseEntity<UsuarioDTO> registrar(@Valid @RequestBody UsuarioRegistroDTO registroDTO) {
        logger.debug("POST /api/usuarios - Registrando nuevo usuario");
        UsuarioDTO usuarioCreado = usuarioService.registrar(registroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
    }

    /**
     * Actualiza la información de un usuario existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizar(@PathVariable Integer id, 
                                                  @Valid @RequestBody UsuarioDTO usuarioDTO) {
        logger.debug("PUT /api/usuarios/{} - Actualizando usuario", id);
        UsuarioDTO usuarioActualizado = usuarioService.actualizar(id, usuarioDTO);
        return ResponseEntity.ok(usuarioActualizado);
    }

    /**
     * Elimina un usuario del sistema.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        logger.debug("DELETE /api/usuarios/{} - Eliminando usuario", id);
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

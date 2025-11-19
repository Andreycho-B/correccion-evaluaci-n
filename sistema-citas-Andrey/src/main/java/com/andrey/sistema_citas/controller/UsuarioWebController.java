package com.andrey.sistema_citas.controller;

import com.andrey.sistema_citas.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador web para gestionar las vistas de usuarios.
 * Maneja las operaciones de visualización y eliminación desde la interfaz web.
 */
@Controller
@RequestMapping("/usuarios")
public class UsuarioWebController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioWebController.class);

    private final UsuarioService usuarioService;

    public UsuarioWebController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Muestra la lista de todos los usuarios.
     */
    @GetMapping
    public String listar(Model model) {
        logger.debug("Mostrando lista de usuarios");
        model.addAttribute("usuarios", usuarioService.obtenerTodos());
        return "usuarios/lista";
    }

    /**
     * Elimina un usuario del sistema.
     */
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            logger.debug("Eliminando usuario ID: {}", id);
            usuarioService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Usuario eliminado exitosamente");
        } catch (Exception e) {
            logger.error("Error al eliminar usuario: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/usuarios";
    }
}

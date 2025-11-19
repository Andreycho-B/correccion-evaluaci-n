package com.andrey.sistema_citas.controller;

import com.andrey.sistema_citas.dto.UsuarioRegistroDTO;
import com.andrey.sistema_citas.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador para gestionar autenticación y registro de usuarios.
 * Maneja las vistas de login y registro.
 */
@Controller
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Muestra la página de login.
     */
    @GetMapping("/login")
    public String login() {
        logger.debug("Mostrando página de login");
        return "login";
    }

    /**
     * Muestra la página de registro.
     */
    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        logger.debug("Mostrando página de registro");
        model.addAttribute("usuario", new UsuarioRegistroDTO());
        return "registro";
    }

    /**
     * Procesa el registro de un nuevo usuario.
     */
    @PostMapping("/registro")
    public String registrar(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO,
                           RedirectAttributes redirectAttributes,
                           Model model) {
        try {
            logger.debug("Procesando registro de usuario: {}", registroDTO.getEmail());
            usuarioService.registrar(registroDTO);
            redirectAttributes.addFlashAttribute("success", "Usuario registrado exitosamente. Por favor inicia sesión.");
            return "redirect:/login";
        } catch (Exception e) {
            logger.error("Error al registrar usuario: {}", e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "registro";
        }
    }

    /**
     * Redirige la raíz al login.
     */
    @GetMapping("/")
    public String root() {
        return "redirect:/login";
    }
}

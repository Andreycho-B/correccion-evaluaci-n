package com.andrey.sistema_citas.controller;

import com.andrey.sistema_citas.dto.ProfesionalDTO;
import com.andrey.sistema_citas.service.ProfesionalService;
import com.andrey.sistema_citas.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador web para gestionar las vistas de profesionales.
 * Maneja las operaciones CRUD desde la interfaz web.
 */
@Controller
@RequestMapping("/profesionales")
public class ProfesionalWebController {

    private static final Logger logger = LoggerFactory.getLogger(ProfesionalWebController.class);

    private final ProfesionalService profesionalService;
    private final UsuarioService usuarioService;

    public ProfesionalWebController(ProfesionalService profesionalService, UsuarioService usuarioService) {
        this.profesionalService = profesionalService;
        this.usuarioService = usuarioService;
    }

    /**
     * Muestra la lista de todos los profesionales.
     */
    @GetMapping
    public String listar(Model model) {
        logger.debug("Mostrando lista de profesionales");
        model.addAttribute("profesionales", profesionalService.obtenerTodos());
        return "profesionales/lista";
    }

    /**
     * Muestra el formulario para crear un nuevo profesional.
     */
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        logger.debug("Mostrando formulario de nuevo profesional");
        model.addAttribute("profesional", new ProfesionalDTO());
        model.addAttribute("usuarios", usuarioService.obtenerTodos());
        return "profesionales/formulario";
    }

    /**
     * Procesa la creación de un nuevo profesional.
     */
    @PostMapping("/nuevo")
    public String crear(@ModelAttribute ProfesionalDTO profesionalDTO, RedirectAttributes redirectAttributes) {
        try {
            logger.debug("Creando nuevo profesional");
            profesionalService.crear(profesionalDTO);
            redirectAttributes.addFlashAttribute("mensaje", "Profesional creado exitosamente");
            return "redirect:/profesionales";
        } catch (Exception e) {
            logger.error("Error al crear profesional: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/profesionales/nuevo";
        }
    }

    /**
     * Muestra el formulario para editar un profesional existente.
     */
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        logger.debug("Mostrando formulario de edición para profesional ID: {}", id);
        model.addAttribute("profesional", profesionalService.obtenerPorId(id));
        model.addAttribute("usuarios", usuarioService.obtenerTodos());
        return "profesionales/formulario";
    }

    /**
     * Procesa la actualización de un profesional existente.
     */
    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable Integer id, @ModelAttribute ProfesionalDTO profesionalDTO,
                            RedirectAttributes redirectAttributes) {
        try {
            logger.debug("Actualizando profesional ID: {}", id);
            profesionalService.actualizar(id, profesionalDTO);
            redirectAttributes.addFlashAttribute("mensaje", "Profesional actualizado exitosamente");
            return "redirect:/profesionales";
        } catch (Exception e) {
            logger.error("Error al actualizar profesional: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/profesionales/editar/" + id;
        }
    }

    /**
     * Elimina un profesional del sistema.
     */
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            logger.debug("Eliminando profesional ID: {}", id);
            profesionalService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Profesional eliminado exitosamente");
        } catch (Exception e) {
            logger.error("Error al eliminar profesional: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/profesionales";
    }
}

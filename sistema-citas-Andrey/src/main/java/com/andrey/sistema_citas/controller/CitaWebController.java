package com.andrey.sistema_citas.controller;

import com.andrey.sistema_citas.dto.CitaDTO;
import com.andrey.sistema_citas.service.CitaService;
import com.andrey.sistema_citas.service.ProfesionalService;
import com.andrey.sistema_citas.service.ServicioService;
import com.andrey.sistema_citas.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador web para gestionar las vistas de citas.
 * Maneja las operaciones CRUD desde la interfaz web.
 */
@Controller
@RequestMapping("/citas")
public class CitaWebController {

    private static final Logger logger = LoggerFactory.getLogger(CitaWebController.class);

    private final CitaService citaService;
    private final UsuarioService usuarioService;
    private final ServicioService servicioService;
    private final ProfesionalService profesionalService;

    public CitaWebController(CitaService citaService, UsuarioService usuarioService,
                            ServicioService servicioService, ProfesionalService profesionalService) {
        this.citaService = citaService;
        this.usuarioService = usuarioService;
        this.servicioService = servicioService;
        this.profesionalService = profesionalService;
    }

    /**
     * Muestra la lista de todas las citas.
     */
    @GetMapping
    public String listar(Model model) {
        logger.debug("Mostrando lista de citas");
        model.addAttribute("citas", citaService.obtenerTodas());
        return "citas/lista";
    }

    /**
     * Muestra el formulario para crear una nueva cita.
     */
    @GetMapping("/nueva")
    public String mostrarFormularioNuevo(Model model) {
        logger.debug("Mostrando formulario de nueva cita");
        model.addAttribute("cita", new CitaDTO());
        model.addAttribute("usuarios", usuarioService.obtenerTodos());
        model.addAttribute("servicios", servicioService.obtenerTodos());
        model.addAttribute("profesionales", profesionalService.obtenerTodos());
        return "citas/formulario";
    }

    /**
     * Procesa la creación de una nueva cita.
     */
    @PostMapping("/nueva")
    public String crear(@ModelAttribute CitaDTO citaDTO, RedirectAttributes redirectAttributes) {
        try {
            logger.debug("Creando nueva cita");
            citaService.crear(citaDTO);
            redirectAttributes.addFlashAttribute("mensaje", "Cita creada exitosamente");
            return "redirect:/citas";
        } catch (Exception e) {
            logger.error("Error al crear cita: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/citas/nueva";
        }
    }

    /**
     * Muestra el formulario para editar una cita existente.
     */
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        logger.debug("Mostrando formulario de edición para cita ID: {}", id);
        model.addAttribute("cita", citaService.obtenerPorId(id));
        model.addAttribute("usuarios", usuarioService.obtenerTodos());
        model.addAttribute("servicios", servicioService.obtenerTodos());
        model.addAttribute("profesionales", profesionalService.obtenerTodos());
        return "citas/formulario";
    }

    /**
     * Procesa la actualización de una cita existente.
     */
    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable Integer id, @ModelAttribute CitaDTO citaDTO,
                            RedirectAttributes redirectAttributes) {
        try {
            logger.debug("Actualizando cita ID: {}", id);
            citaService.actualizar(id, citaDTO);
            redirectAttributes.addFlashAttribute("mensaje", "Cita actualizada exitosamente");
            return "redirect:/citas";
        } catch (Exception e) {
            logger.error("Error al actualizar cita: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/citas/editar/" + id;
        }
    }

    /**
     * Elimina una cita del sistema.
     */
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            logger.debug("Eliminando cita ID: {}", id);
            citaService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Cita eliminada exitosamente");
        } catch (Exception e) {
            logger.error("Error al eliminar cita: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/citas";
    }
}

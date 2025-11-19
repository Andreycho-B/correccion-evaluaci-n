package com.andrey.sistema_citas.controller;

import com.andrey.sistema_citas.dto.ServicioDTO;
import com.andrey.sistema_citas.service.ServicioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador web para gestionar las vistas de servicios.
 * Maneja las operaciones CRUD desde la interfaz web.
 */
@Controller
@RequestMapping("/servicios")
public class ServicioWebController {

    private static final Logger logger = LoggerFactory.getLogger(ServicioWebController.class);

    private final ServicioService servicioService;

    public ServicioWebController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    /**
     * Muestra la lista de todos los servicios.
     */
    @GetMapping
    public String listar(Model model) {
        logger.debug("Mostrando lista de servicios");
        model.addAttribute("servicios", servicioService.obtenerTodos());
        return "servicios/lista";
    }

    /**
     * Muestra el formulario para crear un nuevo servicio.
     */
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        logger.debug("Mostrando formulario de nuevo servicio");
        model.addAttribute("servicio", new ServicioDTO());
        return "servicios/formulario";
    }

    /**
     * Procesa la creación de un nuevo servicio.
     */
    @PostMapping("/nuevo")
    public String crear(@ModelAttribute ServicioDTO servicioDTO, RedirectAttributes redirectAttributes) {
        try {
            logger.debug("Creando nuevo servicio");
            servicioService.crear(servicioDTO);
            redirectAttributes.addFlashAttribute("mensaje", "Servicio creado exitosamente");
            return "redirect:/servicios";
        } catch (Exception e) {
            logger.error("Error al crear servicio: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/servicios/nuevo";
        }
    }

    /**
     * Muestra el formulario para editar un servicio existente.
     */
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        logger.debug("Mostrando formulario de edición para servicio ID: {}", id);
        model.addAttribute("servicio", servicioService.obtenerPorId(id));
        return "servicios/formulario";
    }

    /**
     * Procesa la actualización de un servicio existente.
     */
    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable Integer id, @ModelAttribute ServicioDTO servicioDTO,
                            RedirectAttributes redirectAttributes) {
        try {
            logger.debug("Actualizando servicio ID: {}", id);
            servicioService.actualizar(id, servicioDTO);
            redirectAttributes.addFlashAttribute("mensaje", "Servicio actualizado exitosamente");
            return "redirect:/servicios";
        } catch (Exception e) {
            logger.error("Error al actualizar servicio: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/servicios/editar/" + id;
        }
    }

    /**
     * Elimina un servicio del sistema.
     */
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            logger.debug("Eliminando servicio ID: {}", id);
            servicioService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Servicio eliminado exitosamente");
        } catch (Exception e) {
            logger.error("Error al eliminar servicio: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/servicios";
    }
}

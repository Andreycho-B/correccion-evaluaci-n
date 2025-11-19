package com.andrey.sistema_citas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador para gestionar el dashboard principal del sistema.
 */
@Controller
public class DashboardController {

    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

    /**
     * Muestra el dashboard principal.
     */
    @GetMapping("/dashboard")
    public String dashboard() {
        logger.debug("Mostrando dashboard principal");
        return "dashboard";
    }
}

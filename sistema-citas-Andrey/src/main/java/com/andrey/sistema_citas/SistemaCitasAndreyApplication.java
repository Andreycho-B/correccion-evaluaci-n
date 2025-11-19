package com.andrey.sistema_citas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Clase principal de la aplicación del sistema de citas para centro de ayuda emocional y psicológica.
 * Esta aplicación proporciona un sistema completo de gestión de citas con arquitectura en capas.
 */
@SpringBootApplication
public class SistemaCitasAndreyApplication {

    private static final Logger logger = LoggerFactory.getLogger(SistemaCitasAndreyApplication.class);

    public static void main(String[] args) {
        logger.info("Iniciando Sistema de Citas para Centro de Ayuda Emocional y Psicológica...");
        SpringApplication.run(SistemaCitasAndreyApplication.class, args);
        logger.info("Sistema de Citas iniciado correctamente");
    }

    /**
     * Bean para codificar contraseñas usando BCrypt.
     * Se utiliza en todo el sistema para garantizar la seguridad de las contraseñas.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

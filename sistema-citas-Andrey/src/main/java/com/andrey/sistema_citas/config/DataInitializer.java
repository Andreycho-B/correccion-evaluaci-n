package com.andrey.sistema_citas.config;

import com.andrey.sistema_citas.entity.Usuario;
import com.andrey.sistema_citas.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuración para inicializar datos en la base de datos al arrancar la aplicación.
 * Crea un usuario SuperAdmin por defecto si no existe.
 */
@Configuration
public class DataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    /**
     * Inicializa un usuario SuperAdmin por defecto en el sistema.
     */
    @Bean
    public CommandLineRunner initData(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!usuarioRepository.existsByEmail("admin@sistema.com")) {
                Usuario admin = new Usuario();
                admin.setNombre("Administrador del Sistema");
                admin.setEmail("admin@sistema.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setTelefono("0000000000");
                admin.setRol("SUPERADMIN");

                usuarioRepository.save(admin);
                logger.info("Usuario SuperAdmin creado exitosamente");
                logger.info("Email: admin@sistema.com | Password: admin123");
            } else {
                logger.info("Usuario SuperAdmin ya existe en el sistema");
            }
        };
    }
}

package com.andrey.sistema_citas.service;

import com.andrey.sistema_citas.dto.UsuarioDTO;
import com.andrey.sistema_citas.dto.UsuarioRegistroDTO;
import com.andrey.sistema_citas.entity.Usuario;
import com.andrey.sistema_citas.exception.ResourceNotFoundException;
import com.andrey.sistema_citas.exception.DuplicateResourceException;
import com.andrey.sistema_citas.repository.UsuarioRepository;
import com.andrey.sistema_citas.util.EntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio que gestiona la lógica de negocio relacionada con usuarios.
 * Implementa operaciones CRUD y validaciones de negocio.
 */
@Service
@Transactional
public class UsuarioService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Obtiene todos los usuarios del sistema.
     */
    @Transactional(readOnly = true)
    public List<UsuarioDTO> obtenerTodos() {
        logger.debug("Obteniendo todos los usuarios");
        return usuarioRepository.findAll().stream()
                .map(EntityMapper::toUsuarioDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene un usuario por su ID.
     */
    @Transactional(readOnly = true)
    public UsuarioDTO obtenerPorId(Integer id) {
        logger.debug("Obteniendo usuario con ID: {}", id);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
        return EntityMapper.toUsuarioDTO(usuario);
    }

    /**
     * Obtiene un usuario por su email.
     */
    @Transactional(readOnly = true)
    public UsuarioDTO obtenerPorEmail(String email) {
        logger.debug("Obteniendo usuario con email: {}", email);
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con email: " + email));
        return EntityMapper.toUsuarioDTO(usuario);
    }

    /**
     * Registra un nuevo usuario en el sistema.
     */
    public UsuarioDTO registrar(UsuarioRegistroDTO registroDTO) {
        logger.debug("Registrando nuevo usuario con email: {}", registroDTO.getEmail());

        if (usuarioRepository.existsByEmail(registroDTO.getEmail())) {
            throw new DuplicateResourceException("Ya existe un usuario con el email: " + registroDTO.getEmail());
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(registroDTO.getNombre());
        usuario.setEmail(registroDTO.getEmail());
        usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));
        usuario.setTelefono(registroDTO.getTelefono());
        usuario.setRol("SUPERADMIN");

        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        logger.info("Usuario registrado exitosamente con ID: {}", usuarioGuardado.getId());

        return EntityMapper.toUsuarioDTO(usuarioGuardado);
    }

    /**
     * Actualiza la información de un usuario existente.
     */
    public UsuarioDTO actualizar(Integer id, UsuarioDTO usuarioDTO) {
        logger.debug("Actualizando usuario con ID: {}", id);

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));

        if (!usuario.getEmail().equals(usuarioDTO.getEmail()) && 
            usuarioRepository.existsByEmail(usuarioDTO.getEmail())) {
            throw new DuplicateResourceException("Ya existe un usuario con el email: " + usuarioDTO.getEmail());
        }

        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setTelefono(usuarioDTO.getTelefono());

        Usuario usuarioActualizado = usuarioRepository.save(usuario);
        logger.info("Usuario actualizado exitosamente con ID: {}", usuarioActualizado.getId());

        return EntityMapper.toUsuarioDTO(usuarioActualizado);
    }

    /**
     * Elimina un usuario del sistema.
     */
    public void eliminar(Integer id) {
        logger.debug("Eliminando usuario con ID: {}", id);

        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " + id);
        }

        usuarioRepository.deleteById(id);
        logger.info("Usuario eliminado exitosamente con ID: {}", id);
    }
}

package com.andrey.sistema_citas.exception;

/**
 * Excepción lanzada cuando se intenta crear un recurso que ya existe en el sistema.
 * Se utiliza para validar unicidad de recursos como emails de usuario.
 */
public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String mensaje) {
        super(mensaje);
    }

    public DuplicateResourceException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}

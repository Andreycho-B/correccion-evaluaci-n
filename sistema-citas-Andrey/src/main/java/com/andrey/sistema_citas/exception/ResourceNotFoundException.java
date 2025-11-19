package com.andrey.sistema_citas.exception;

/**
 * Excepción lanzada cuando un recurso solicitado no se encuentra en el sistema.
 * Se utiliza para operaciones de búsqueda que no encuentran el recurso esperado.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }

    public ResourceNotFoundException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}

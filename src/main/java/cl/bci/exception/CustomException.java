package cl.bci.exception;

import org.springframework.http.HttpStatus;

/**
 * Excepción personalizada para manejar errores específicos de negocio en la aplicación.
 * Incluye soporte para definir mensajes personalizados y códigos de estado HTTP.
 * 
 * @author PABI
 */
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final HttpStatus status;

    /**
     * Constructor para inicializar la excepción con un mensaje.
     * 
     * @param message Descripción del error.
     */
    public CustomException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST; // Por defecto, se asume un error 400
    }

    /**
     * Constructor para inicializar la excepción con un mensaje y un código de estado.
     * 
     * @param message Descripción del error.
     * @param status Código de estado HTTP asociado al error.
     */
    public CustomException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    /**
     * Obtiene el código de estado HTTP asociado a la excepción.
     * 
     * @return Código de estado HTTP.
     */
    public HttpStatus getStatus() {
        return status;
    }
}

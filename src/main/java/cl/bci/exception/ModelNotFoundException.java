package cl.bci.exception;

/**
 * Clase para excepcion en modelo.
 * 
 * @author PABI1
 *
 */
public class ModelNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5208234351317362814L;

	public ModelNotFoundException(String mensaje) {
		super(mensaje);
	}

	public ModelNotFoundException(Exception e) {
		super(e);
	}
}

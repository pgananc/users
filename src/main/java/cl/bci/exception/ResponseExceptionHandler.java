package cl.bci.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Handler para excepciones.
 * 
 * @author PABI1
 *
 */
@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> manejarTodasExcepciones(ModelNotFoundException ex,
			WebRequest request) {
		ExceptionResponse er = new ExceptionResponse(ex.getMessage());
		return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ModelNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> manejarModeloException(ModelNotFoundException ex,
			WebRequest request) {
		ExceptionResponse er = new ExceptionResponse(ex.getMessage());
		return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
	}


//	@ExceptionHandler(ConstraintViolationException.class)
//	protected ResponseEntity<Object> handleConstraintViolationException(MethodArgumentNotValidException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		StringBuilder errors = listarErrores(ex);
//		ExceptionResponse er = new ExceptionResponse(errors.toString());
//		return new ResponseEntity<Object>(er, HttpStatus.BAD_REQUEST);
//	}
	
	  @ExceptionHandler(ConstraintViolationException.class)
	    public ResponseEntity<ExceptionResponse> handleConstraintViolationException(ConstraintViolationException ex) {
	        // Obtiene el conjunto de violaciones de la restricción
	        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
	        
	        // Recorre las violaciones y obtiene solo el mensaje interpolado
	        StringBuilder errorMessages = new StringBuilder();
	        
	        for (ConstraintViolation<?> violation : violations) {
	            // Obtiene el mensaje interpolado de cada violación
	            errorMessages.append(violation.getMessage()).append("\n");
	        }

	        // Devuelve los mensajes de error como respuesta
	        ExceptionResponse er = new ExceptionResponse(errorMessages.toString());
			return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
	    }
	

}

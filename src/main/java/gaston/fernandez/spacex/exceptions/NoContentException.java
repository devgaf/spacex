package gaston.fernandez.spacex.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepcion lanzada cuando no hay contenido disponible.
 */
@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoContentException extends RuntimeException {

	private static final long serialVersionUID = 1L;

    /**
     * Constructor de la clase NoContentException con un mensaje.
     * 
     * @param message el mensaje de la excepcion
     */
	public NoContentException(String message) {
		super(message);
	}

}

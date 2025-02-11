package gaston.fernandez.spacex.exceptions;

/**
 * Excepcion lanzada cuando hay un error en la configuracion de SSL.
 */
public class SSLConfigurationException extends RuntimeException {
    /**
     * Constructor por defecto de la clase SSLConfigurationException.
     */
    public SSLConfigurationException() {
        super("SSL Configuration Error");
    }

    /**
     * Constructor de la clase SSLConfigurationException con un mensaje y una causa.
     * 
     * @param message el mensaje de la excepcion
     * @param cause   la causa de la excepcion
     */
    public SSLConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor de la clase SSLConfigurationException con un mensaje.
     * 
     * @param message el mensaje de la excepcion
     */
    public SSLConfigurationException(String message) {
        super(message);
    }
}

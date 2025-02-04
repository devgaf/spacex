package gaston.fernandez.spacex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación
 */
@SpringBootApplication
public class SpacexApplication {

	/**
	 * Punto de entrada principal de la aplicación.
	 * 
	 * Llamando a este método se inicia el contexto de Spring y se activan
	 * todos los componentes configurados en {@link SpacexApplication}.
	 *
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpacexApplication.class, args);
	}

}

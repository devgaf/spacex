package gaston.fernandez.spacex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicacion. En este proyecto usare Lombok para la generacion automatica de codigo mediante sus anotaciones
 */
@SpringBootApplication
public class SpacexApplication {

	/**
	 * Punto de entrada principal de la aplicacion. Llamando a este metodo se inicia el contexto de Spring y se activan todos los componentes configurados en {@link SpacexApplication}.
	 *
	 * @param args Argumentos de la linea de comandos
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpacexApplication.class, args);
	}

}

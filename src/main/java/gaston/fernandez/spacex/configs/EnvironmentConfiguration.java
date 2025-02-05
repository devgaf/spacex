package gaston.fernandez.spacex.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
/**
 * Esta clase se encarga de leer las variables de entorno del archivo .env utilizando la anotación @Configuration para indicarle a Spring que esta clase es una clase de configuración, la anotación @PropertySource para indicarle a Spring que lea las variables de entorno del archivo .env y la anotación de Lombok @Getter para generar los getters de las variables de la clase (launchesUrl y rocketsUrl).
 */
@Configuration
@PropertySource("file:.env")
@Getter
public class EnvironmentConfiguration {

    /**
     * Se encarga de leer la variable de entorno LAUNCHES_URL
     */
    @Value("${LAUNCHES_URL}")
    private String launchesUrl;

    /**
     * Se encarga de leer la variable de entorno ROCKETS_URL
     */
    @Value("${ROCKETS_URL}")
    private String rocketsUrl;
}

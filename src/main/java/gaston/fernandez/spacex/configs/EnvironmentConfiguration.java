package gaston.fernandez.spacex.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
/**
 * Esta clase se encarga de leer las variables de entorno del archivo .env
 */
@Configuration
@PropertySource("file:.env")
@Getter
public class EnvironmentConfiguration {

    /**
     * Se encarga de leer la variable de entorno API_URL
     */
    @Value("${LAUNCHES_URL}")
    private String launchesUrl;

    /**
     * Se encarga de leer la variable de entorno ROCKETS_URL
     */
    @Value("${ROCKETS_URL}")
    private String rocketsUrl;
}

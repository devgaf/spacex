package gaston.fernandez.spacex.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import gaston.fernandez.spacex.dtos.Rocket;
import gaston.fernandez.spacex.dtos.SpexLunches;
import gaston.fernandez.spacex.exceptions.NoContentException;
import gaston.fernandez.spacex.exceptions.SSLConfigurationException;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase que contiene la logica de negocio para obtener la informacion de los
 * lanzamientos de SpaceX.
 */
@Service
@Slf4j
public class SpexService {

    @Value("${LAUNCHES_URL}")
    private String launchesUrl;

    @Value("${ROCKETS_URL}")
    private String rocketsUrl;

    private final RestTemplate restTemplate;

    /**
     * Constructor de la clase SpexService.
     * 
     * @param restTemplate el objeto RestTemplate que se va a injectar para
     * utilizarlo al hacer las peticiones HTTP
     */

    public SpexService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Retorna el contenido del endpoint de lanzamientos de SpaceX. Si falla en
     * configurar SSL, lanza una SSLConfigurationException. Si falla al obtener el
     * contenido, lanza una IOException. Si falla por cualquier otro motivo, lanza
     * una Exception.
     * 
     * @return el contenido del endpoint de lanzamientos de SpaceX
     * @throws NoContentException        si falla al obtener el contenido
     * @throws SSLConfigurationException si falla en configurar SSL
     * @throws Exception                 si falla por cualquier otro motivo
     */
    private String getLaunches() throws NoContentException, SSLConfigurationException, Exception {

        try {
            String url = UriComponentsBuilder.fromUriString(launchesUrl).toUriString();
            String response = restTemplate.getForObject(url, String.class);
            if (response != null) {
                return response;
            } else {
                throw new NoContentException("Response body is null");
            }
        } catch (SSLConfigurationException sslEx) {
            log.error("SSL Configuration Error: {}", sslEx.toString());
            sslEx.printStackTrace();
            throw sslEx;
        } catch (NoContentException ioEx) {
            log.error("NoContentException Error: {}", ioEx.toString());
            ioEx.printStackTrace();
            throw ioEx;
        } catch (Exception ex) {
            log.error("Error fetching data from Lunches: {}", ex.toString());
            ex.printStackTrace();
            throw new Exception("Error fetching data from Lunches: " + ex.toString(), ex);
        }
    }

    /**
     * Retorna el contenido del endpoint de cohetes de SpaceX. Si falla en
     * configurar SSL, lanza una SSLConfigurationException. Si falla al obtener el
     * contenido, lanza una IOException. Si falla por cualquier otro motivo, lanza
     * una Exception.
     * 
     * @return el contenido del endpoint de cohetes de SpaceX
     * @throws NoContentException        si falla al obtener el contenido
     * @throws SSLConfigurationException si falla en configurar SSL
     * @throws Exception                 si falla por cualquier otro motivo
     */
    private String getRockets() throws NoContentException, SSLConfigurationException, Exception {
        String url = UriComponentsBuilder.fromUriString(rocketsUrl).toUriString();
        try {
            String response = restTemplate.getForObject(url, String.class);
            if (response != null) {
                return response;
            } else {
                throw new NoContentException("Response body is null");
            }
        } catch (SSLConfigurationException sslEx) {
            log.error("SSL Configuration Error: {}", sslEx.toString());
            sslEx.printStackTrace();
            throw sslEx;
        } catch (NoContentException ioEx) {
            log.error("NoContentException Error: {}", ioEx.toString());
            ioEx.printStackTrace();
            throw ioEx;
        } catch (Exception ex) {
            log.error("Error fetching data from Lunches: {}", ex.toString());
            ex.printStackTrace();
            throw new Exception("Error fetching data from Lunches: " + ex.toString(), ex);
        }
    }

    /**
     * Retorna un mapa de objetos Rocket que contienen la informacion de los cohetes
     * de SpaceX. El mapa se indexa por el campo rocket_id de cada cohete. Si falla
     * en configurar SSL, lanza una SSLConfigurationException. Si falla al obtener
     * el contenido, lanza una IOException. Si falla por cualquier otro motivo,
     * lanza una Exception.
     * 
     * @param objectMapper el objeto ObjectMapper que se utiliza para deserializar
     *                     el contenido del endpoint de rockets de SpaceX
     * @return un mapa de objetos Rocket que contienen la informacion de los cohetes
     *         de SpaceX
     * @throws IOException               si falla al obtener el contenido
     * @throws SSLConfigurationException si falla en configurar SSL
     * @throws Exception                 si falla por cualquier otro motivo
     */
    private Map<String, Rocket> getRockets(ObjectMapper objectMapper)
            throws SSLConfigurationException, IOException, Exception {
        List<Rocket> rockets = objectMapper.readValue(getRockets(), new TypeReference<List<Rocket>>() {
        });
        Map<String, Rocket> rocketMap = new HashMap<>();
        for (Rocket rocket : rockets) {
            rocketMap.put(rocket.getRocketId(), rocket);
        }
        return rocketMap;
    }

    /**
     * Retorna una lista de objetos SpexLunches que contienen la informacion de los
     * lanzamientos de SpaceX. Si falla en configurar SSL, lanza una
     * SSLConfigurationException. Si falla al obtener el contenido, lanza una
     * IOException. Si falla por cualquier otro motivo, lanza una Exception.
     * 
     * @param objectMapper el objeto ObjectMapper para parsear el JSON
     * @return una lista de objetos SpexLunches
     * @throws IOException               si falla al obtener el contenido
     * @throws SSLConfigurationException si falla en configurar SSL
     * @throws Exception                 si falla por cualquier otro motivo
     */
    private List<SpexLunches> getLaunches(ObjectMapper objectMapper)
            throws SSLConfigurationException, IOException, Exception {
        return objectMapper.readValue(getLaunches(), new TypeReference<List<SpexLunches>>() {
        });
    }

    /**
     * Serializa los lanzamientos de SpaceX asociándolos con sus respectivos cohetes.
     * <p>
     * Obtiene la lista de lanzamientos y el mapa de cohetes utilizando un ObjectMapper.
     * Para cada lanzamiento, establece el cohete correspondiente basado en el rocketId.
     * Si falla en configurar SSL, lanza una SSLConfigurationException. 
     * Si falla al obtener el contenido, lanza una IOException.
     * Si falla por cualquier otro motivo, lanza una Exception.
     * 
     * @return una lista de objetos SpexLunches con la información actualizada de los cohetes
     * @throws IOException si falla al obtener el contenido
     * @throws SSLConfigurationException si falla en configurar SSL
     * @throws Exception si falla por cualquier otro motivo
     */

    private List<SpexLunches> serializeSpexLunches() throws SSLConfigurationException, IOException, Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        List<SpexLunches> response = getLaunches(objectMapper);
        Map<String, Rocket> rocket = getRockets(objectMapper);
        response.forEach(launches -> launches.setRocket(rocket.get(launches.getRocket().getRocketId())));

        return response;
    }

    /**
     * Retorna una lista de objetos SpexLunches que contienen la informacion de los
     * lanzamientos de SpaceX. Si falla en configurar SSL, lanza una
     * SSLConfigurationException. Si falla al obtener el contenido, lanza una
     * IOException. Si falla por cualquier otro motivo, lanza una Exception.
     * 
     * @return una lista de objetos SpexLunches
     * @throws IOException               si falla al obtener el contenido
     * @throws SSLConfigurationException si falla en configurar SSL
     * @throws Exception                 si falla por cualquier otro motivo
     */
    public List<SpexLunches> getSpexLunches() throws IOException, SSLConfigurationException, Exception {
        return serializeSpexLunches();
    }

}

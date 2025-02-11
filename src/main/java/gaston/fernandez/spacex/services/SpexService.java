package gaston.fernandez.spacex.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import gaston.fernandez.spacex.collections.FavoritesList;
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

    private FavoritesList favoritesList = new FavoritesList();

    private List<SpexLunches> lunches = new ArrayList<>();

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
     * Serializa los lanzamientos de SpaceX asociandolos con sus respectivos cohetes.
     * <p>
     * Obtiene la lista de lanzamientos y el mapa de cohetes utilizando un ObjectMapper.
     * Para cada lanzamiento, establece el cohete correspondiente basado en el rocketId.
     * Si falla en configurar SSL, lanza una SSLConfigurationException. 
     * Si falla al obtener el contenido, lanza una IOException.
     * Si falla por cualquier otro motivo, lanza una Exception.
     * 
     * @return una lista de objetos SpexLunches con la informacion actualizada de los cohetes
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
        lunches = serializeSpexLunches();
        return lunches;
    }

    /**
     * Retorna el lanzamiento de SpaceX cuyo numero de vuelo coincida con el parametro
     * <code>flightNumber</code>. Si no se encuentra un lanzamiento con ese numero de
     * vuelo, devuelve <code>null</code>.
     * 
     * @param flightNumber el numero de vuelo a buscar
     * @return el lanzamiento de SpaceX con el numero de vuelo especificado o
     *         <code>null</code> si no se encuentra
     */
    private SpexLunches getSpexLunchesByFlightNumber(int flightNumber) {
        return lunches.stream().filter(launch -> launch.getFlightNumber() == flightNumber).findFirst().orElse(null);
    }

    /**
     * Retorna la lista de lanzamientos de SpaceX que se encuentran en la lista de
     * favoritos.
     * 
     * @return la lista de lanzamientos favoritos
     */
    public List<SpexLunches> getFavoritesLunches() throws NoContentException{
        List<SpexLunches> favorites = favoritesList.getFavorites();
        if (favorites == null) {
            throw new NoContentException("La lista de favoritos esta vacia");
        }
        return favorites;
    }

    /**
     * Agrega un lanzamiento de SpaceX a la lista de favoritos segun su numero de vuelo.
     * 
     * @param flightNumber el numero de vuelo del lanzamiento que se desea agregar a la lista de favoritos
     * @return la lista actualizada de lanzamientos favoritos
     */
    public List<SpexLunches> addFavoriteLaunch(int flightNumber) {
        SpexLunches spexLunches = getSpexLunchesByFlightNumber(flightNumber);
        if (spexLunches == null) {
            throw new NoContentException("El despegue que intenta insertar no fue encontrado");
        }
        favoritesList.getFavorites().add(spexLunches);
        return getFavoritesLunches();
    }

    /**
     * Elimina un lanzamiento de SpaceX de la lista de favoritos segun su numero de vuelo.
     * 
     * @param flightNumber el numero de vuelo del lanzamiento que se desea eliminar de la lista de favoritos
     * @return la lista actualizada de lanzamientos favoritos
     */

    public List<SpexLunches> removeFavoriteLaunch(int flightNumber) {
        SpexLunches spexLunches = favoritesList.getFavorite(flightNumber);
        if (spexLunches == null) {
            throw new NoContentException("El despegue que intenta borrar de los favoritos no fue encontrado en la lista");
        }
        favoritesList.getFavorites().remove(spexLunches);
        return getFavoritesLunches();
    }
}

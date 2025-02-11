package gaston.fernandez.spacex.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;

import gaston.fernandez.spacex.dtos.SpexLunches;
import gaston.fernandez.spacex.exceptions.NoContentException;
import gaston.fernandez.spacex.exceptions.SSLConfigurationException;
import gaston.fernandez.spacex.services.SpexService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SpexController {
    SpexService spexService;
    private final HttpHeaders headers = new HttpHeaders();

    /**
     * Inicializo el header de la respuesta HTTP.
     */
    {
        headers.add("Content-Type", "application/json; charset=UTF-8");
    }

    /**
     * Constructor de la clase SpexController.
     * 
     * @param spexService el objeto SpexService que se va a injectar para
     *                    utilizarlo al hacer las peticiones HTTP a la API de
     *                    SpaceX.
     */
    public SpexController(SpexService spexService) {
        this.spexService = spexService;
    }

    /**
     * Maneja excepciones generales no capturadas por metodos mas especificos.
     *
     * @param e La excepcion lanzada que no fue manejada por otros metodos.
     * @return Una respuesta HTTP con un mensaje de error y un estado de error
     *         interno del servidor (500).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        e.printStackTrace();
        headers.add("error", "Error general: " + e.toString());
        return new ResponseEntity<>("Error general: " + e.getMessage(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * <p>
     * Obtiene el listado de lanzamientos de SpaceX en formato JSON.
     * </p>
     * 
     * Si falla al obtener el contenido, lanza una NoContentException con el http
     * status 204 (NO_CONTENT).
     * Si falla al configurar SSL, lanza una SSLConfigurationException con el http
     * status 500 (INTERNAL_SERVER_ERROR).
     * Si falla al obtener el contenido, lanza una IOException con el http status
     * 500 (INTERNAL_SERVER_ERROR).
     * Si falla por cualquier otro motivo, lanza una Exception con el http status
     * 500 (INTERNAL_SERVER_ERROR).
     *
     * @return el listado de lanzamientos de SpaceX en formato JSON.
     */
    @GetMapping("lunches")
    public ResponseEntity<List<SpexLunches>> getLaunches() {
        try {
            return new ResponseEntity<>(spexService.getSpexLunches(), headers, HttpStatus.OK);
        } catch (SSLConfigurationException e) {
            e.printStackTrace();
            headers.add("error", "SSL Configuration Error " + e.getMessage());
            return new ResponseEntity<>(null, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NoContentException e) {
            e.printStackTrace();
            headers.add("error", "No content available: " + e.getMessage());
            return new ResponseEntity<>(null, headers,
                    HttpStatus.NO_CONTENT);
        } catch (IOException e) {
            e.printStackTrace();
            headers.add("error", "IO Error: " + e.getMessage());
            return new ResponseEntity<>(null, headers,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            headers.add("error", "Error general: " + e.toString());
            return new ResponseEntity<>(null, headers,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retorna la lista de lanzamientos de SpaceX que se encuentran en la lista de
     * favoritos.
     * 
     * Si la lista esta vacia, lanza una NoContentException con el http
     * status 204 (NO_CONTENT).
     * Si falla por cualquier otro motivo, lanza una Exception con el http status
     * 500 (INTERNAL_SERVER_ERROR).
     * 
     * @return la lista de lanzamientos favoritos
     */
    @GetMapping("favorites")
    public ResponseEntity<List<SpexLunches>> getFavoritesLunches() {
        try {
            return new ResponseEntity<>(spexService.getFavoritesLunches(), headers, HttpStatus.OK);
        } catch (NoContentException e) {
            e.printStackTrace();
            headers.add("error", "No content available: " + e.getMessage());
            return new ResponseEntity<>(null, headers, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            headers.add("error", "Error general: " + e.toString());
            return new ResponseEntity<>(null, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Agrega un lanzamiento de SpaceX a la lista de favoritos.
     * 
     * @param flightNumber el numero de vuelo del lanzamiento que se desea agregar a la lista de favoritos
     * 
     * Si el despegue a insertar no se encuentra, lanza una NoContentException con el http
     * status 204 (NO_CONTENT).
     * Si falla por cualquier otro motivo, lanza una Exception con el http status
     * 500 (INTERNAL_SERVER_ERROR).
     * 
     * @return la lista actualizada de lanzamientos favoritos
     */
    @PutMapping("favorites/{flightNumber}")
    public ResponseEntity<List<SpexLunches>> addFavoriteLaunch(@PathVariable int flightNumber) {
        try {
            return new ResponseEntity<>(spexService.addFavoriteLaunch(flightNumber), headers, HttpStatus.OK);
        } catch (NoContentException e) {
            e.printStackTrace();
            headers.add("error", "No content available: " + e.getMessage());
            return new ResponseEntity<>(null, headers,
                    HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            headers.add("error", "Error general: " + e.toString());
            return new ResponseEntity<>(null, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Borra un lanzamiento de SpaceX de la lista de favoritos.
     * 
     * @param flightNumber el numero de vuelo del lanzamiento que se desea borrar
     *                     de la lista de favoritos
     * 
     * 
     * 
     * Si el despegue a eliminar no esta en los favoritos, lanza una NoContentException con el http
     * status 204 (NO_CONTENT).
     * Si falla por cualquier otro motivo, lanza una Exception con el http status
     * 500 (INTERNAL_SERVER_ERROR).
     * @return la lista actualizada de lanzamientos favoritos
     */
    @DeleteMapping("favorites/{flightNumber}")
    public ResponseEntity<List<SpexLunches>> removeFavoriteLaunch(@PathVariable int flightNumber) {
        try {
            return new ResponseEntity<>(spexService.removeFavoriteLaunch(flightNumber), headers, HttpStatus.OK);
        } catch (NoContentException e) {
            e.printStackTrace();
            headers.add("error", "No content available: " + e.getMessage());
            return new ResponseEntity<>(null, headers,
                    HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            headers.add("error", "Error general: " + e.toString());
            return new ResponseEntity<>(null, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

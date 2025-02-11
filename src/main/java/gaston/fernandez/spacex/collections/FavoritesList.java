package gaston.fernandez.spacex.collections;

import java.util.ArrayList;
import java.util.List;

import gaston.fernandez.spacex.dtos.SpexLunches;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Clase que representa una lista de favoritos de lanzamientos de SpaceX.
 * <p>
 * <ul>
 *     <li> Se utiliza la anotacion @Getter de Lombok para generar los metodos getter</li>
 *     <li> Se utiliza la anotacion @NoArgsConstructor de Lombok para generar un constructor vacio</li>
 *     <li> Se utiliza la anotacion @AllArgsConstructor de Lombok para generar un constructor con todos los atributos de la clase</li>
 * </ul>
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FavoritesList {
    List<SpexLunches> favorites = new ArrayList<>();


    /**
     * Obtiene un lanzamiento de SpaceX de la lista de favoritos
     * segun su numero de vuelo.
     * 
     * @param flightNumber el n mero de vuelo del lanzamiento que se
     *                     desea obtener de la lista de favoritos
     * @return el lanzamiento de SpaceX con el n mero de vuelo
     *         especificado, o null si no se encuentra en la lista
     *         de favoritos
     */
    public SpexLunches getFavorite(int flightNumber) {
        for (SpexLunches spexLunches : favorites) {
            if (spexLunches.getFlightNumber() == flightNumber) {
                return spexLunches;
            }
        }
        return null;
    }
}       

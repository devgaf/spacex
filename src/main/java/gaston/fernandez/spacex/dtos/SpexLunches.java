package gaston.fernandez.spacex.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa un lanzamiento de SpaceX.
 * <p>
 * <ul>
 *      <li> Se utiliza la anotacion @JsonProperty para mapear los atributos de la respuesta de la API</li>
 *      <li> Se utiliza la anotacion @JsonIgnoreProperties para ignorar los atributos que no se encuentren en la clase</li>
 *      <li> Se utiliza la anotacion @Data de Lombok para generar los metodos getter, setter, equals, hashcode y toString</li>
 *      <li> Se utiliza la anotacion @AllArgsConstructor de Lombok para generar un constructor con todos los atributos de la clase</li>
 *      <li> Se utiliza la anotacion @NoArgsConstructor de Lombok para generar un constructor vacio</li>
 * </ul>
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpexLunches {

    @JsonProperty("flight_number")
    private int flightNumber;
    @JsonProperty("mission_name")
    private String missionName;
    @JsonProperty("details")
    private String details;
    @JsonProperty("rocket")
    private Rocket rocket;
}

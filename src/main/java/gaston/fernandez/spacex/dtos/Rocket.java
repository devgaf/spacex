package gaston.fernandez.spacex.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clase que representa un Cohete
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
public class Rocket {
    @JsonProperty("rocket_id")
    private String rocketId;
    @JsonProperty("rocket_name")
    private String rocketName;
    @JsonProperty("active")
    private boolean active;
    @JsonProperty("cost_per_launch")
    private long costPerLaunch;
    @JsonProperty("company")
    private String company;
}

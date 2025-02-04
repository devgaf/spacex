package gaston.fernandez.spacex.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpexLunches {

    private long flightNumber;
    private String missionName;
    private String missionPatch;
    private String details;
    private Rocket rocket;
}

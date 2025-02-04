package gaston.fernandez.spacex.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rocket {
    private String rocketId;
    private String rocketName;
    private boolean active;
    private long costPerLaunch;
    private String company;
}

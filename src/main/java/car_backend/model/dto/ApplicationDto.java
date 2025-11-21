package car_backend.model.dto;

import car_backend.model.enums.OptionsEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDto {

    private String codeCIA;
    private String applicationName;
    private OptionsEnum isBlocking;
}

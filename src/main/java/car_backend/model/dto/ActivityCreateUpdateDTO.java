package car_backend.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityCreateUpdateDTO {

    private GeneralInformationDetailsDTO generalInformation;
    
}

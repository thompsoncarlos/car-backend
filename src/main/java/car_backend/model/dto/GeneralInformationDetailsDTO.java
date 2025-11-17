package car_backend.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralInformationDetailsDTO {

    private Long activityId;
    private String activityName;
    private String activityType;
    private String activityDescription;
}

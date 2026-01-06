package car_backend.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralInformationDetailsDto {

    private String id;
    private String name;
    private String description;
    private String status;
    private String version;


    private String reviewDate;
    private String reviewedBy;
    private String updateHistory;
}

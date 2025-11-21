package car_backend.model.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralInformationDto {

    private String name;
    private String type;
    @Size (max = 50, message = "Description can't have more then 50 characters")
    private String description;
    private String status;
}

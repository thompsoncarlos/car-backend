package car_backend.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityCreateUpdateDto {

    private GeneralInformationDetailsDto generalInformation;
    private OrganizationalUnitDto organizationalUnit;
    private SubProcessDto subProcess;
    private ApplicationDto applicationDto;
}

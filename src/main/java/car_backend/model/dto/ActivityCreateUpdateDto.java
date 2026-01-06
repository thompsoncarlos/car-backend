package car_backend.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityCreateUpdateDto {

    private GeneralInformationDto generalInformation;
    private OrganizationalUnitDto organizationUnit;
    private SubProcessDto subProcess;
    private ApplicationDto applicationDTO;
}

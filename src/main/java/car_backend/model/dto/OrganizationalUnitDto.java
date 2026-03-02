package car_backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationalUnitDto {

    private String id;

    private String name;

    private String uoResponsible;
}

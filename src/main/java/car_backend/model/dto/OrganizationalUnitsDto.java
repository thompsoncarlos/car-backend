package car_backend.model.dto;

import car_backend.model.dao.Activity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationalUnitsDto {

    private List<OrganizationalUnitDto> organizationalUnitDtoList;
}

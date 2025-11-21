package car_backend.adapter;

import car_backend.model.dao.OrganizationalUnit;
import car_backend.model.dto.OrganizationalUnitDto;
import car_backend.model.dto.OrganizationalUnitsDto;

import java.util.ArrayList;
import java.util.List;

public class OrganizationUnitAdapter {

    private OrganizationUnitAdapter() {}

    public static OrganizationalUnitsDto adaptToDtoList(List<OrganizationalUnit> organizationalUnits) {
        OrganizationalUnitsDto organizationalUnitsDto = new OrganizationalUnitsDto();
        organizationalUnitsDto.setOrganizationalUnitDtoList(adaptToList(organizationalUnits));
        return organizationalUnitsDto;
    }

    public static List<OrganizationalUnitDto> adaptToList(List<OrganizationalUnit> organizationalUnits) {
        List<OrganizationalUnitDto> result = new ArrayList<>();
        for (OrganizationalUnit uo : organizationalUnits) {
            OrganizationalUnitDto unitDto = new OrganizationalUnitDto(uo.getId(), uo.getFrenchLabel());
            result.add(unitDto);
        }
        return result;
    }

    public static OrganizationalUnitDto adaptToDto(OrganizationalUnit organizationalUnit) {
        OrganizationalUnitDto uo = new OrganizationalUnitDto();
        uo.setId(organizationalUnit.getId());
        uo.setName(organizationalUnit.getFrenchLabel());
        return uo;
    }
}

package car_backend.adapter;

import car_backend.model.dao.OrganizationalUnit;
import car_backend.model.dto.OrganizationalUnitDto;
import car_backend.model.dto.OrganizationalUnitsDto;

import java.util.ArrayList;
import java.util.List;

public class OrganizationalUnitAdapter {

   private OrganizationalUnitAdapter() {}

    public static OrganizationalUnitsDto adaptToDtoList(List<OrganizationalUnit> organizationalUnits) {
        OrganizationalUnitsDto organizationalUnitsDto = new OrganizationalUnitsDto();
        organizationalUnitsDto.setOrganizationalUnitList(adaptToList(organizationalUnits));
        return organizationalUnitsDto;

    }

    private static List<OrganizationalUnitDto> adaptToList(List<OrganizationalUnit> organizationalUnits) {
        List<OrganizationalUnitDto> result = new ArrayList<>();
        for (OrganizationalUnit uo : organizationalUnits) {
            OrganizationalUnitDto unitDto = new OrganizationalUnitDto();
            unitDto.setId(uo.getId());
            unitDto.setName(uo.getFrenchLabel());
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

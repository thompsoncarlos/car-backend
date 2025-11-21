package car_backend.service;

import car_backend.model.dto.OrganizationalUnitDto;
import car_backend.model.dto.OrganizationalUnitsDto;

public interface OrganizationUnitService {

    OrganizationalUnitsDto uoList();

    OrganizationalUnitDto getByFrenchLabel(String uoFrenchLabel);

    OrganizationalUnitDto getById(String id);
}

package car_backend.service;

import car_backend.adapter.OrganizationUnitAdapter;
import car_backend.model.dao.OrganizationalUnit;
import car_backend.model.dto.OrganizationalUnitDto;
import car_backend.model.dto.OrganizationalUnitsDto;
import car_backend.repository.OrganizationUnitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
public class OrganizationUnitServiceImpl {

    @Autowired
    private OrganizationUnitRepository uoRepository;

    @Override
    public OrganizationalUnitsDto uoList() {
        OrganizationalUnitsDto uoList = OrganizationUnitAdapter.adaptToDtoList(uoRepository.findAll());
        if (uoList.getOrganizationalUnitDtoList().isEmpty()) {
            throw new NoSuchElementException("No organizational units found!");
        }
        return uoList;
    }

    @Override
    public OrganizationalUnitDto getByFrenchLabel(String uoFrenchLabel) {
        OrganizationalUnit uoEntity = uoRepository.findByFrenchLabel(uoFrenchLabel).orElseThrow();
        return OrganizationUnitAdapter.adaptToDto(uoEntity);
    }

    @Override
    public OrganizationalUnitDto getById(String id) {
        OrganizationalUnit uoEntity = uoRepository.findById(id).orElseThrow();
        return OrganizationUnitAdapter.adaptToDto(uoEntity);
    }
}

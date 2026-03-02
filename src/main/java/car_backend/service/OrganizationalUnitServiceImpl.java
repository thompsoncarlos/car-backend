package car_backend.service;

import car_backend.adapter.OrganizationalUnitAdapter;
import car_backend.model.dao.refs.OrganizationalUnitRef;
import car_backend.model.dto.OrganizationalUnitDto;
import car_backend.model.dto.OrganizationalUnitsDto;
import car_backend.repository.OrganizationalUnitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
public class OrganizationalUnitServiceImpl implements OrganizationalUnitService {

    @Autowired
    private OrganizationalUnitRepository uoRepository;

    @Override
    public OrganizationalUnitsDto uoList() {
        OrganizationalUnitsDto uoList = OrganizationalUnitAdapter.adaptToDtoList(uoRepository.findAll());
        if (uoList.getOrganizationalUnitList().isEmpty()) {
            throw new NoSuchElementException("No organizational units found!");
        }
        return uoList;
    }

    @Override
    public OrganizationalUnitDto getByFrenchLabel(String uoFrenchLabel) {
        OrganizationalUnitRef uoEntity = uoRepository.findByFrenchLabel(uoFrenchLabel).orElseThrow();
        return OrganizationalUnitAdapter.adaptToDto(uoEntity);
    }

    @Override
    public OrganizationalUnitDto getById(String id) {
        OrganizationalUnitRef uoEntity = uoRepository.findById(id).orElseThrow();
        return OrganizationalUnitAdapter.adaptToDto(uoEntity);
    }
}

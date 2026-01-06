package car_backend.controller;

import car_backend.model.dto.OrganizationalUnitsDto;
import car_backend.service.OrganizationUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/api")
public class OrganizationalUnitController {

    @Autowired
    OrganizationalUnitService uoService;

    @GetMapping("/organizational-unit")
    public ResponseEntity<OrganizationalUnitsDto> getUoList() {
        return ResponseEntity.ok().body(uoService.uoList());
    }
}
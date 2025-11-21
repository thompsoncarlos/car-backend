package car_backend.controller;

import car_backend.model.dto.PersonListDto;
import car_backend.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping("/person")
    public ResponseEntity<PersonListDto> getPersonListByUoId(@RequestParam(name = "uo_id") String uoId) {
        return ResponseEntity.ok().body(service.getPersonByUoId(uoId));
    }


}

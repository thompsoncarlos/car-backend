package car_backend.service;

import car_backend.model.dto.PersonListDto;

public interface PersonService {

    PersonListDto getPersonByUoId(String uoId);
}

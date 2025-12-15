package car_backend.service;

import car_backend.model.dao.views.PersonView;
import car_backend.model.dto.PersonListDto;

import java.util.List;

public interface PersonService {

    PersonListDto getPersonByUoId(String uoId);
}
package car_backend.service;

import car_backend.adapter.PersonAdapter;
import car_backend.model.dao.views.PersonView;
import car_backend.model.dto.PersonListDto;
import car_backend.repository.PersonViewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonViewRepository viewRepository;

    @Override
    public PersonListDto getPersonByUoId(String uoId) {
        List<PersonView> personViews = viewRepository.findByUoId(uoId);
        if (personViews.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No one is associated with this organizational unit");
        }
        return PersonAdapter.adaptViewToDtoList(personViews);
    }
}

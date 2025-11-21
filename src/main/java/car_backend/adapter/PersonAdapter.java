package car_backend.adapter;

import car_backend.model.dao.views.PersonView;
import car_backend.model.dto.PersonDto;
import car_backend.model.dto.PersonListDto;

import java.util.ArrayList;
import java.util.List;

public class PersonAdapter {

    private PersonAdapter() {}

    public static PersonListDto adaptViewToDtoList(List<PersonView> personViewList) {
        List<PersonDto> personDtoList = new ArrayList<>();

        for (PersonView personView : personViewList) {
            personDtoList.add(adaptViewToDto(personView));
        }

        PersonListDto output = new PersonListDto();
        output.setPersonDtoList(personDtoList);
        return output;
    }

    public static PersonDto adaptViewToDto(PersonView personView) {
        PersonDto personDto = new PersonDto();
        personDto.setName(personView.getName());
        personDto.setLastname(personView.getLastname());
        personDto.setOrganizationalUnit(personView.getUoId());
        personDto.setHierarchy(personView.getIdHierarchy());
        return personDto;
    }
}

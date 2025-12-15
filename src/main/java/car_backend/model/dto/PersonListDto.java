package car_backend.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationalUnitList {
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonListDto {

    private List<OrganizationalUnitDto> organizationalUnitList;
    private List<PersonDto> personDtoList;
}
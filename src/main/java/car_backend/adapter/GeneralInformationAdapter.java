package car_backend.adapter;

import car_backend.model.dao.Activity;
import car_backend.model.dto.GeneralInformationDetailsDto;

public class GeneralInformationAdapter {

    private GeneralInformationAdapter() {}

    public static GeneralInformationDetailsDto adaptToGeneralInformationDto(Activity activity) {
        GeneralInformationDetailsDto detailsDTO = new GeneralInformationDetailsDto();
        detailsDTO.setActivityId(activity.getId());
        detailsDTO.setActivityDescription(activity.getActivityDescription());
        detailsDTO.setStatus(activity.getStatus());
        detailsDTO.setVersion(activity.getActivityVersion());
        detailsDTO.setReviewDate("28/08/2025");
        detailsDTO.setReviewedBy("Camille Valois");
        detailsDTO.setUpdateHistory("06/06/2025: Camiller Valois - Création activité");
        return detailsDTO;
    }
}

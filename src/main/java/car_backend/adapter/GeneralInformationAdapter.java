package car_backend.adapter;

import car_backend.model.dao.Activity;
import car_backend.model.dao.ActivityInput;
import car_backend.model.dao.views.ActivityView;
import car_backend.model.dto.GeneralInformationDetailsDto;

public class GeneralInformationAdapter {

   private GeneralInformationAdapter() {}

    public static GeneralInformationDetailsDto adaptToGeneralInformationDto(Activity activity) {
        GeneralInformationDetailsDto detailsDTO = new GeneralInformationDetailsDto();
        detailsDTO.setId(activity.getActivityId());
        detailsDTO.setDescription(activity.getActivityDescription());
        detailsDTO.setStatus(activity.getStatus());
        detailsDTO.setVersion(activity.getActivityVersion());
        detailsDTO.setReviewDate(activity.getReviewDate());
        detailsDTO.setReviewedBy(activity.getModifier());
        detailsDTO.setUpdateHistory("06/06/2025: Camille Valois - Création activité");
        return detailsDTO;
    }

    public static GeneralInformationDetailsDto adaptViewToDto(ActivityView activityView) {
        GeneralInformationDetailsDto detailsDto = new GeneralInformationDetailsDto();
        detailsDto.setId(activityView.getActivityId());
        detailsDto.setName(activityView.getActivityName());
        return detailsDto;
    }
}
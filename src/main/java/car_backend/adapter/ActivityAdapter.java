package car_backend.adapter;

import car_backend.model.dao.Activity;
import car_backend.model.dto.ActivityCreateUpdateDTO;
import car_backend.model.dto.ActivityDetailsDTO;
import car_backend.model.dto.GeneralInformationDetailsDTO;

public class ActivityAdapter {

    private ActivityAdapter() {}   

    public static Activity adaptToModel(ActivityCreateUpdateDTO activityDTO) {
        Activity activity = new Activity();
        activity.setActivityName(activityDTO.getGeneralInformation().getActivityName());
        activity.setActivityDescription(activityDTO.getGeneralInformation().getActivityDescription());
        activity.setActivityType(activityDTO.getGeneralInformation().getActivityType());
        return activity;
    }

    public static ActivityDetailsDTO adaptToDto(Activity activity) {
        ActivityDetailsDTO activityDetails = new ActivityDetailsDTO();
        activityDetails.setGeneralInformation(adaptToGeneralInformationDto(activity));
        return activityDetails;
    }

    private static GeneralInformationDetailsDTO adaptToGeneralInformationDto(Activity activity) {
        GeneralInformationDetailsDTO detailsDTO = new GeneralInformationDetailsDTO();
        detailsDTO.setActivityId(activity.getId());
        detailsDTO.setActivityType(activity.getActivityType());
        detailsDTO.setActivityName(activity.getActivityName());
        detailsDTO.setActivityDescription(activity.getActivityDescription());
        return detailsDTO;
    }
}
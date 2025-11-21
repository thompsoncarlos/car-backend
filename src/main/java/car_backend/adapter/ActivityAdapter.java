package car_backend.adapter;

import car_backend.model.Constants;
import car_backend.model.dao.Activity;
import car_backend.model.dto.ActivityCreateUpdateDTO;
import car_backend.model.dto.ActivityDetailsDTO;
import car_backend.model.enums.StatusEnum;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ActivityAdapter {

    private ActivityAdapter() {}

    public static Activity adaptToModel(ActivityCreateUpdateDTO activityDTO) {
        Activity activity = new Activity();
        activity.setActivityName(activityDTO.getGeneralInformation().getActivityName());
        activity.setActivityDescription(activityDTO.getGeneralInformation().getActivityDescription());
        activity.setStatus(StatusEnum.NEW.getValue());
        activity.setActivityVersion(Constants.NEW_ACTIVITY_VERSION);
        activity.setCreationDate(LocalDateTime.now());
        return activity;
    }

    public static Activity adaptToModelUpdate(Activity activity, ActivityCreateUpdateDTO activityDTO) {
        activity.setActivityName(activityDTO.getGeneralInformation().getActivityName());
        activity.setActivityDescription(activityDTO.getGeneralInformation().getActivityDescription());
        activity.setStatus(StatusEnum.DRAFT.getValue());
        activity.setCreationDate(LocalDateTime.now());
        return activity;
    }

    public static Activity adaptToModelValidate(Activity activity) {
        Long currentVersion = activity.getActivityVersion();
        activity.setStatus(StatusEnum.VALIDATED.getValue());
        activity.setActivityVersion(currentVersion + 1);
        return activity;
    }

    public static List<ActivityDetailsDTO> adaptToDtoList(List<Activity> activities) {
        List<ActivityDetailsDTO> activitiesDto = new ArrayList<>();
        for (Activity activity : activities) {
            activitiesDto.add(adaptToDto(activity));
        }
        return  activitiesDto;
    }

    public static ActivityDetailsDTO adaptToDto(Activity activity) {
        ActivityDetailsDTO activityDetails = new ActivityDetailsDTO();
        activityDetails.setGeneralInformation(adaptToGeneralInformationDto(activity));
        return activityDetails;
    }
}
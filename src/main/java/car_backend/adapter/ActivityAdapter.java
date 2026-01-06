package car_backend.adapter;

import car_backend.model.Constants;
import car_backend.model.dao.Activity;
import car_backend.model.dao.ActivityInput;
import car_backend.model.dao.views.ActivityView;
import car_backend.model.dto.ActivityCreateUpdateDto;
import car_backend.model.dto.ActivityDetailsDto;
import car_backend.model.enums.StatusEnum;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ActivityAdapter {

    private ActivityAdapter() {}

    public static Activity adaptToModel(ActivityCreateUpdateDto activityDTO) {
        Activity activity = new Activity();
        activity.setActivityLabel(activityDTO.getGeneralInformation().getName());
        activity.setActivityDescription(activityDTO.getGeneralInformation().getDescription());
        activity.setSource(Constants.ACTIVITY_SOURCE_INPUT);
        return activity;
    }

    public static Activity adaptToModelUpdate(Activity activity, ActivityCreateUpdateDto activityDTO) {
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
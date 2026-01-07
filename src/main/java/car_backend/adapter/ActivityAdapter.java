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
        activity.setActivityLabel(activityDTO.getGeneralInformation().getName());
        activity.setActivityDescription(activityDTO.getGeneralInformation().getDescription());
        activity.setStatus(StatusEnum.DRAFT.getValue());
        activity.setCreationDate(LocalDateTime.now());
        return activity;
    }

    public static ActivityInput adaptToModelValidate(ActivityInput activityInput) {
        String currentVersion = activityInput.getActivityVersion();
        activityInput.setStatus(StatusEnum.VALIDATED.getValue());
        activityInput.setActivityVersion(currentVersion + 1);
        return activityInput;
    }

    public static List<ActivityDetailsDto> adaptToDtoList(List<Activity> activities) {
        List<ActivityDetailsDto> activitiesDto = new ArrayList<>();
        for (Activity activity : activities) {
            activitiesDto.add(adaptToDto(activity));
        }
        return activitiesDto;
    }

    public static ActivityDetailsDto adaptToDto(Activity activity) {
        ActivityDetailsDto activityDetails = new ActivityDetailsDto();
        activityDetails.setGeneralInformation(GeneralInformationAdapter.adaptToGeneralInformationDto(activity));
        //activityDetails.setOrganizationUnit(adaptToDto(activityInput.getOrganizationalUnit()));
        return activityDetails;
    }

    public static List<ActivityDetailsDto> adaptViewToDtoList(List<ActivityView> activityViewList) {
        List<ActivityDetailsDto> output = new ArrayList<>();
        for (ActivityView activityView : activityViewList) {
            ActivityDetailsDto activityDetailsDto = new ActivityDetailsDto();
            activityDetailsDto.setGeneralInformation(GeneralInformationAdapter.adaptViewToDto(activityView));
            output.add(activityDetailsDto);
        }
        return output;
    }
}
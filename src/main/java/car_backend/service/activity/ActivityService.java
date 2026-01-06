package car_backend.service.activity;

import car_backend.model.dto.ActivityCreateUpdateDto;
import car_backend.model.dto.ActivityDetailsDto;

import java.util.List;

public interface ActivityService {

    ActivityDetailsDto createActivity(ActivityCreateUpdateDto activityDTO);

    ActivityDetailsDto updateActivity(String id, ActivityCreateUpdateDto activityDTO);

    void deleteActivityDraft(String id);

    ActivityDetailsDto getActivity(String id);

    List<ActivityDetailsDto> getActivities();

    List<ActivityDetailsDto> getActivitiesByUo(String uoId);
}
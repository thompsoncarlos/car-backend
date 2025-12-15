package car_backend.service.activity;

import car_backend.model.dto.ActivityCreateUpdateDTO;
import car_backend.model.dto.ActivityDetailsDTO;

import java.util.List;

public interface ActivityService {
    
    void createActivity(ActivityCreateUpdateDTO activityDTO);

    void updateActivity(Long id, ActivityCreateUpdateDTO activityDTO);

    void validateUpdateActivity(Long id);

    ActivityDetailsDTO getActivity(Long id);

    List<ActivityDetailsDTO> getActivities();
}
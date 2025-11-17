package car_backend.service;

import car_backend.model.dto.ActivityCreateUpdateDTO;
import car_backend.model.dto.ActivityDetailsDTO;

public interface ActivityService {
    
    void createActivity(ActivityCreateUpdateDTO activityDTO);
    
    ActivityDetailsDTO getActivity();
}
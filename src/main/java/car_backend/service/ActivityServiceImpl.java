package car_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import car_backend.adapter.ActivityAdapter;
import car_backend.model.dao.Activity;
import car_backend.model.dto.ActivityCreateUpdateDTO;
import car_backend.model.dto.ActivityDetailsDTO;
import car_backend.repository.ActivityRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityRepository activityRepository;

    @Override
    public void createActivity(ActivityCreateUpdateDTO activityDTO) {
        Activity activity = ActivityAdapter.adaptToModel(activityDTO);
        activityRepository.save(activity);
    }

    @Override
    public ActivityDetailsDTO getActivity() {
        return ActivityAdapter.adaptToDto(activityRepository.findAll().getFirst());
    }
}
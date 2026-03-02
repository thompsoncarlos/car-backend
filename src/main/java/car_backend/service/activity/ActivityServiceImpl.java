package car_backend.service.activity;

import car_backend.adapter.ActivityAdapter;
import car_backend.model.dao.Activity;
import car_backend.model.dao.views.ActivityView;
import car_backend.model.dto.ActivityCreateUpdateDto;
import car_backend.model.dto.ActivityDetailsDto;
import car_backend.model.enums.StatusEnum;
import car_backend.repository.ActivityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityRepository repository;

    @Override
    public ActivityDetailsDto createActivity(ActivityCreateUpdateDto activityDTO) {
        try {
            Activity activity = ActivityAdapter.adaptToModel(activityDTO);
            activity.setStatus(StatusEnum.NEW.getValue());
            activity.setCreationDate(LocalDateTime.now());
            repository.save(activity);
            return ActivityAdapter.adaptToDto(activity);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ActivityDetailsDto updateActivity(String id, ActivityCreateUpdateDto activityDTO) {
        try {
            Activity activity = repository.findById(id).orElseThrow();
            ActivityAdapter.adaptToModelUpdate(activity, activityDTO);
            repository.save(activity);
            return ActivityAdapter.adaptToDto(activity);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public void deleteActivityDraft(String id) {
        Activity activity = repository.findById(id).orElseThrow();
        if (!activity.getStatus().equals(StatusEnum.DRAFT.getValue())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Activity is not in draft");
        }
        repository.delete(activity);
    }

    @Override
    public ActivityDetailsDto getActivity(String id) {
        Activity activity = repository.findById(id).orElseThrow();
        return ActivityAdapter.adaptToDto(activity);
    }

    @Override
    public List<ActivityDetailsDto> getActivities() {
        List<Activity> activities = repository.findAll();
        if (activities.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "List of Activities is Empty.");
        }
        return ActivityAdapter.adaptToDtoList(activities);

    }

    @Override
    public List<ActivityDetailsDto> getActivitiesByUo(String uoId) {
        List<ActivityView> views = repository.getActivitiesByUoId(uoId);
        return ActivityAdapter.adaptViewToDtoList(views);
    }
}

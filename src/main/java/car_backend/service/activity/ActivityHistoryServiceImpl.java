package car_backend.service.activity;

import car_backend.adapter.ActivityHistoryAdapter;
import car_backend.model.Constants;
import car_backend.model.dao.ActivityInput;
import car_backend.model.dao.ActivityHistory;
import car_backend.model.enums.StatusEnum;
import car_backend.repository.ActivityHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class ActivityHistoryServiceImpl implements ActivityHistoryService {

     @Autowired
    ActivityHistoryRepository repository;

    @Override
    public void addActivityToHistory(ActivityInput activityInput) {
        if (activityIsNew(activityInput)) {
            ActivityHistory activityHistory = ActivityHistoryAdapter.adaptToModel(activityInput);
            repository.save(activityHistory);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Activity already exists, please update it or create a new one");
        }
    }

    @Override
    public void updateActivityHistory(ActivityInput activityInput) {
        if (activityInput.getStatus().equals(StatusEnum.DRAFT.getValue())) {
            ActivityHistory activityHistory = ActivityHistoryAdapter.adaptToModel(activityInput);
            repository.save(activityHistory);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This activity is not pendent of validation");
        }
    }

    private boolean activityIsNew(ActivityInput activityInput) {
        return repository.findById(activityInput.getId()).isEmpty()
                && activityInput.getStatus().equals(StatusEnum.NEW.getValue())
                && activityInput.getActivityVersion().equals(Constants.NEW_ACTIVITY_VERSION);
    }
}

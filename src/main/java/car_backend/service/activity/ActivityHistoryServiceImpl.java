package car_backend.service.activity;

import car_backend.adapter.ActivityHistoryAdapter;
import car_backend.model.Constants;
import car_backend.model.dao.Activity;
import car_backend.model.dao.ActivityHistory;
import car_backend.model.enums.StatusEnum;
import car_backend.repository.ActivityFileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class ActivityHistoryServiceImpl implements ActivityHistoryService {

    @Autowired
    ActivityFileRepository repository;

    @Override
    public void addActivityToHistory(Activity activity) {
        if (activityIsNew(activity)) {
            ActivityHistory activityHistory = ActivityHistoryAdapter.adaptToModel(activity);
            repository.save(activityHistory);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Activity already exists, please update it or create a new one");
        }
    }
    private boolean activityIsNew(Activity activity) {
        return repository.findById(activity.getId()).isEmpty()
                && activity.getStatus().equals(StatusEnum.NEW.getValue())
                && activity.getActivityVersion().equals(Constants.NEW_ACTIVITY_VERSION);
    }
}

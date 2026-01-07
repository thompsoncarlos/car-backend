package car_backend.adapter;

import car_backend.model.dao.ActivityInput;
import car_backend.model.dao.ActivityHistory;

import java.time.LocalDateTime;
import java.util.Objects;

public class ActivityHistoryAdapter {

    private ActivityHistoryAdapter() {}

    public static ActivityHistory adaptToModel(ActivityInput activityInput) {
        ActivityHistory activityHistory = new ActivityHistory();
        activityHistory.setActivityId(activityInput.getId());
        activityHistory.setActivityName(activityInput.getActivityName());
        activityHistory.setActivityDescription(activityInput.getActivityDescription());
        activityHistory.setStatus(activityInput.getStatus());
        activityHistory.setActivityVersion(activityInput.getActivityVersion());
        activityHistory.setCreationDate(Objects.isNull(activityInput.getCreationDate()) ? LocalDateTime.now() : activity.getCreationDate());
        return activityHistory;
    }
}

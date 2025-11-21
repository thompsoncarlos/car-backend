package car_backend.adapter;

import car_backend.model.dao.Activity;
import car_backend.model.dao.ActivityHistory;

import java.time.LocalDateTime;
import java.util.Objects;

public class ActivityHistoryAdapter {

    private ActivityHistoryAdapter() {}

    public static ActivityHistory adaptToModel(Activity activity) {
        ActivityHistory activityHistory = new ActivityHistory();
        activityHistory.setActivityId(activity.getId());
        activityHistory.setActivityName(activity.getActivityName());
        activityHistory.setActivityDescription(activity.getActivityDescription());
        activityHistory.setStatus(activity.getStatus());
        activityHistory.setActivityVersion(activity.getActivityVersion());
        activityHistory.setCreationDate(Objects.isNull(activity.getCreationDate()) ? LocalDateTime.now() : activity.getCreationDate());
        return activityHistory;
    }
}

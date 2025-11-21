package car_backend.service.activity;

import car_backend.model.dao.Activity;

public interface ActivityHistoryService {

    void addActivityToHistory(Activity activity);

    void updateActivityHistory(Activity activity);
}

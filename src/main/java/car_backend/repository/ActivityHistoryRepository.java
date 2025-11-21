package car_backend.repository;

import car_backend.model.dao.ActivityHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityHistoryRepository extends JpaRepository<ActivityHistory, Long> {
}

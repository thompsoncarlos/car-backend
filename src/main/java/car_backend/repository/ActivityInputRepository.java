package car_backend.repository;

import car_backend.model.dao.ActivityInput;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityInputRepository extends JpaRepository<ActivityInput, Long> {
}

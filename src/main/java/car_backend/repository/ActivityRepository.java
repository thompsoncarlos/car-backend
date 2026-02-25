package car_backend.repository;

import car_backend.model.dao.Activity;
import car_backend.model.dao.ActivityInput;
import car_backend.model.dao.views.ActivityView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, String> {

    @Query(value = "SELECT DISTINCT " +
            "a.ACTIVITY_ID, " +
            "a.ACTIVITY_LABEL, " +
            "a.ACTIVITY_TYPE, " +
            "a.AGREEMENT_IS_NECESSARY, " +
            "a.DO_YOU_CONFIRM, " +
            "a.IS_BLOCKING " +
            "FROM PHI_V_ACTIVITY a " +
            "LEFT OUTER JOIN PHI_V_REL_ACTIVITY_UO auo " +
            "ON a.ACTIVITY_ID = auo.ACTIVITY_ID " +
            "WHERE auo.ID_UO = :uo_id",
            nativeQuery = true)
    List<ActivityView> getActivitiesByUoId(@Param("uo_id") String uoId);
}

package car_backend.repository;

import car_backend.model.dao.files.ActivityFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityFileRepository extends JpaRepository<ActivityFile, Long> {

    @Query("SELECT DISTINCT a.activityId as serviceId, " +
            "NULL as serviceType, " +
            "a.activityId || ' - ' || a.activityLabel as serviceUniqueLabel, " +
            "NULL as serviceRecipientName, " +
            "NULL as serviceRecipientCode, " +
            "NULL as serviceProviderEntityName, " +
            "NULL as serviceProviderEntityCode, " +
            "NULL as serviceProviderEntityType, " +
            "NULL as serviceProviderParentName, " +
            "NULL as serviceProviderParentCode, " +
            "NULL as serviceProviderParentType, " +
            "NULL as serviceProviderDelivery, " +
            "NULL as criticality, " +
            "NULL as contractId, " +
            "NULL as governingLaw, " +
            "NULL as resolutionFeatures, " +
            "NULL as businessReorganizationPlan, " +
            "NULL as alternativeMitigatingActions, " +
            "NULL as criticalIct, " +
            "NULL as ictService " +
            "FROM ActivityFile a " +
            "LEFT OUTER JOIN ActivityOrganizationalUnit auo " +
            "ON a.activityId = auo.activityId " +
            "WHERE a.sqlLoadId = :sqlLoadId")
    List<Object[]> findActivitiesRelevantServices(@Param("sqlLoadId") String sqlLoadId);
}
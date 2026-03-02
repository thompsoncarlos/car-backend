package car_backend.repository.reports;

import car_backend.model.reports.ReportZ03;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportZ03Repository extends JpaRepository<ReportZ03, String> {

    @Query(value = """
        SELECT 
            apr.activity_id as activityId,
            pr.role_id as roleId,
            r.role_name as roleName,
            LISTAGG(DISTINCT puo.uo_code, ' -- ') WITHIN GROUP (ORDER BY puo.uo_code) as concatenatedUos,
            COUNT(DISTINCT apr.person_id) as personCount
        FROM activity_person_rel apr
        INNER JOIN person_role pr ON apr.person_id = pr.person_id
        INNER JOIN role r ON pr.role_id = r.role_id
        INNER JOIN person_uo puo ON apr.person_id = puo.person_id
        WHERE apr.activity_id IN :activityIds
        GROUP BY apr.activity_id, pr.role_id, r.role_name
        ORDER BY apr.activity_id, r.role_name
        """, nativeQuery = true)
    List<Object[]> findActivityRoleUoMapping(@Param("activityIds") List<String> activityIds);

}

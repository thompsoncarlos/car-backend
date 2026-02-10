package car_backend.repository.reports;

import car_backend.model.excel.ReportZ04;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportZ04Repository extends JpaRepository<ReportZ04, String> {

    @Query(value = """
            SELECT DISTINCT
                a.ACTIVITY_ID,
                a.ACTIVITY_LABEL,
                CONCAT(a.ACTIVITY_ID, a.ACTIVITY_LABEL),
                le.COUNTRY,
                eef.EBA_CODE
            FROM
                PHI_T_ACTIVITY a
            LEFT OUTER JOIN PHI_T_REL_ACTIVITY_ENTITY rae
                ON a.ACTIVITY_ID = rae.ACTIVITY_ID
            LEFT OUTER JOIN PHI_T_LEGAL_ENTITY le
                ON rae.LEGAL_ENTITY_ID = le.LEGAL_ENTITY_ID
            LEFT OUTER JOIN PHI_T_ACTIVITY_TYPE_CATEGORY atc
                ON a.ACTIVITY_TYPE_ID = atc.ACTIVITY_TYPE_ID
            LEFT OUTER JOIN PHI_T_REL_EBA_SERVICES_ACTIVITY_TYPE resat
                ON atc.ACTIVITY_TYPE_ID = resat.ACTIVITY_TYPE_ID
            LEFT OUTER JOIN PHI_T_EBA_SERVICES es
                ON resat.EBA_SERVICES_ID = es.EBA_SERVICES_ID
            LEFT OUTER JOIN PHI_T_EBA_ECO_FUNCTION eef
                ON atc.EBA_ECO_FUNCTION_ID = eef.EBA_ECO_FUNCTION_ID
            ORDER BY
                le.COUNTRY,
                a.ACTIVITY_ID
            """, nativeQuery = true)
    List<Object[]> generateReportData();
}

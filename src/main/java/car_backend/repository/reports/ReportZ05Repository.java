package car_backend.repository.reports;

import car_backend.model.reports.ReportZ05;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportZ05Repository extends JpaRepository<ReportZ05, String> {
    @Query(value = """
      SELECT DISTINCT
          a.ACTIVITY_ID,
          COALESCE(es.EBA_SERVICES_ID, '#UNV'),
          CASE
              WHEN a.ACTIVITY_LABEL IS NOT NULL
              THEN CONCAT(CONCAT(a.ACTIVITY_ID, '_'), a.ACTIVITY_LABEL)
              ELSE CONCAT(a.ACTIVITY_ID, '_#UNV')
          END,
          COALESCE(ebl.CORE_BUSINESS_LINE_NAME, '#UNV'),
          COALESCE(ebl.CORE_BUSINESS_LINE_ID, '#UNV')
      FROM
          PHI_T_ACTIVITY a
          LEFT OUTER JOIN PHI_T_ACTIVITY_TYPE_CATEGORY atc
              ON a.ACTIVITY_TYPE_ID = atc.ACTIVITY_TYPE_ID
          LEFT OUTER JOIN PHI_T_REL_EBA_SERVICES_ACTIVITY_TYPE resat
              ON atc.ACTIVITY_TYPE_ID = resat.ACTIVITY_TYPE_ID
          LEFT OUTER JOIN PHI_T_EBA_SERVICES es
              ON resat.EBA_SERVICES_ID = es.EBA_SERVICES_ID
          LEFT OUTER JOIN phi_t_eba_business_line ebl
              ON ebl.core_business_line_id = atc.core_business_line_id
        """, nativeQuery = true)
    List<Object[]> generateReportData();

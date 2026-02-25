package car_backend.repository.reports;

import car_backend.model.reports.ReportZ04;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportZ04Repository extends JpaRepository<ReportZ04, String> {

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE PHI_T_REPORT_Z04", nativeQuery = true)
    void truncateReportTable();

    @Modifying
    @Query(value = """
            INSERT INTO PHI_T_REPORT_Z04 (SERVICE_IDENTIFIER_0005, SERVICE_TYPE_0010, UNIQUE_SERVICE_TITLE_BK_TAXO_0020, CRITICAL_FUNCTION_COUNTRY_0030, CRITICAL_FUNCTION_COUNTRY_CODE_0040)
            SELECT DISTINCT
              a.ACTIVITY_ID,
              COALESCE(es.EBA_SERVICES_ID, '#UNV'),
              CASE
                  WHEN a.ACTIVITY_LABEL IS NOT NULL
                  THEN CONCAT(CONCAT(a.ACTIVITY_ID, ' - '), a.ACTIVITY_LABEL)
                  ELSE CONCAT(a.ACTIVITY_ID, '_#UNV')
              END,
              COALESCE(le.COUNTRY, '#UNV'),
              COALESCE(eef.EBA_ECO_FUNCTION_ID, '#UNV')
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
              LEFT OUTER JOIN PHI_T_REL_ACTIVITY_TYPE_EBA_ECO_FUNCTION rtef
                   ON atc.ACTIVITY_TYPE_ID = rtef.activity_type_id
              LEFT OUTER JOIN PHI_T_EBA_ECO_FUNCTION eef
                  ON rtef.EBA_ECO_FUNCTION_ID = eef.EBA_ECO_FUNCTION_ID;
               """, nativeQuery = true)
    void insertReportTable();
}

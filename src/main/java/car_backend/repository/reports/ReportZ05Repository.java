package car_backend.repository.reports;

import car_backend.model.reports.ReportZ05;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface ReportZ05Repository extends JpaRepository<ReportZ05, String> {

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE PHI_T_REPORT_Z05", nativeQuery = true)
    void truncateReportTable();

    @Modifying
    @Query(value = """
      INSERT INTO PHI_T_REPORT_Z05 (SERVICE_IDENTIFIER_0005, SERVICE_TYPE_0010, UNIQUE_SERVICE_TITLE_BK_TAXO_0020, CORE_BUSINESS_LINE_NAME_0030, CORE_BUSINESS_LINE_ID_0040)
        SELECT DISTINCT
            a.ACTIVITY_ID,
            COALESCE(es.EBA_SERVICES_ID, '#UNV'),
            CASE
                WHEN a.ACTIVITY_LABEL IS NOT NULL
                THEN CONCAT(CONCAT(a.ACTIVITY_ID, ' - '), a.ACTIVITY_LABEL)
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
                ON ebl.core_business_line_id = atc.core_business_line_id;
        """, nativeQuery = true)
    void insertReportTable();
}
package car_backend.repository.reports;

import car_backend.model.excel.ReportZ01;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportZ01Repository extends JpaRepository<ReportZ01, String> {

    @Query(value = """
        SELECT DISTINCT
            A.ACTIVITY_ID,
            A.ACTIVITY_CATEGORY_TYPE,
            CONCAT(A.ACTIVITY_ID, A.ACTIVITY_LABEL),
            NVL(REF.LEGAL_ENTITY_LABEL, AUO.LEGAL_ENTITY_LABEL),
            '0040',
            P.PRESTATION_LABEL,
            P.PRESTATION_ID
        FROM PHI_T_ACTIVITY A
        LEFT JOIN PHI_T_FILES_ACTIVITY_UO AUO
            ON A.ACTIVITY_ID = AUO.ACTIVITY_ID
        LEFT JOIN PHI_T_REL_ACITVITY_ENTITY REE
            ON REE.ACTIVITY_ID = A.ACTIVITY_ID
        LEFT JOIN PHI_T_REF_LEGAL_ENTITY REF
            ON REF.LEGAL_ENTITY_ID = REE.LEGAL_ENTITY_ID
        JOIN PHI_T_REL_ACTIVITY_PROVIDER_PRESTATION PP
            ON A.ACTIVITY_ID = PP.ACTIVITY_ID
        JOIN PHI_T_PRESTATION P
            ON P.PRESTATION_ID = PP.PRESTATION_ID
        JOIN PHI_T_REL_ACTIVITY_PROVIDER_ACTIVITY APA
            ON APA.PROVIDER_ACTIVITY_ID = PP.ACTIVITY_ID
        WHERE PP.IS_BLOCKING = 'YES'
        
        UNION
        
        SELECT DISTINCT
            A.ACTIVITY_ID,
            A.ACTIVITY_CATEGORY_TYPE,
            CONCAT(A.ACTIVITY_ID, A.ACTIVITY_LABEL),
            COALESCE(REF.LEGAL_ENTITY_LABEL, AUO.LEGAL_ENTITY_LABEL),
            '0040',
            CA.ACTIVITY_LABEL,
            CA.ACTIVITY_ID
        FROM PHI_T_ACTIVITY A
        LEFT JOIN PHI_T_FILES_ACTIVITY_UO AUO
            ON A.ACTIVITY_ID = AUO.ACTIVITY_ID
        LEFT JOIN PHI_T_REL_ACITVITY_ENTITY REE
            ON REE.ACTIVITY_ID = A.ACTIVITY_ID
        LEFT JOIN PHI_T_REF_LEGAL_ENTITY REF
            ON REF.LEGAL_ENTITY_ID = REE.LEGAL_ENTITY_ID
        JOIN PHI_T_REL_ACTIVITY_PROVIDER_ACTIVITY PA
            ON A.ACTIVITY_ID = PA.BENEFICIARY_ACIVITY_ID
        JOIN PHI_T_ACTIVITY CA
            ON CA.ACTIVITY_ID = PA.PROVIDER_ACTIVITY_ID
        WHERE UPPER(COALESCE(PA.IS_BLOCKING, 'NO')) = 'YES'
        """, nativeQuery = true)
    List<Object[]> generateReportData();
}

package car_backend.repository.reports;

import car_backend.model.reports.ReportServiceCatalogue;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface ReportServiceCatalogueRepository extends JpaRepository<ReportServiceCatalogue, String> {

@Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE PHI_T_REPORT_SERVICE_CATALOGUE", nativeQuery = true)
    void truncateReportTable();

    @Modifying
    @Transactional
    @Query(value = """
    INSERT INTO PHI_T_REPORT_SERVICE_CATALOGUE (
            SERVICE_IDENTIFIER_0005,
            SERVICE_TYPE_0010,
            UNIQUE_SERVICE_TITLE_BK_TAXO_0015,
            SERVICE_DESCRIPTION_0020,
            SERVICE_PROVIDER_ENTITY_NAME_0030,
            SERVICE_PROVIDER_ENTITY_DEPARTMENT_NAME_0040,
            SERVICE_PROVIDER_ENTITY_CODE_0050,
            SERVICE_RECIPIENT_ENTITY_NAME_0060,
            SERVICE_RECIPIENT_ENTITY_CODE_0070,
            DELIVERY_MODEL_0080,
            CRITICAL_FUNCTION_ID_0090,
            CORE_BUSINESS_LINE_0100,
            SUBSTITUTABILITY_0110,
            COST_0120,
            CONTRACT_ID_0130,
            INCLUSION_OF_RESOLUTION_0140,
            COUNTRY_0150,
            MACRO_BUSINESS_LINE_0160,
            MACRO_PROCESS_0170,
            PROCESS_0180,
            SUB_PROCESS_0190,
            ACTIVITY_CATEGORY_0200,
            IS_BLOCKING_0210
    )
    SELECT
    a.activity_id AS SERVICE_IDENTIFIER_0005,
    COALESCE(es.EBA_SERVICES_ID, '#UNV') AS SERVICE_TYPE_0010,
    CASE
    WHEN a.ACTIVITY_LABEL IS NOT NULL THEN CONCAT (CONCAT (a.ACTIVITY_ID, '_'), a.ACTIVITY_LABEL)
    ELSE CONCAT (a.ACTIVITY_ID, '_#UNV')
    END AS UNIQUE_SERVICE_TITLE_BK_TAXO_0015,
    COALESCE(a.ACTIVITY_DESCRIPTION, '#UNV') AS SERVICE_DESCRIPTION_0020,
    COALESCE(
            CASE
                    WHEN sup.SUPPLIER_ID IS NOT NULL THEN sup.SUPPLIER_LABEL -- SUPPLIER (Provider)
    ELSE COALESCE(le.legal_entity_label, '#UNV')
    END,
            '#UNV'
            ) AS SERVICE_PROVIDER_ENTITY_NAME_0030,
    COALESCE (uo.uo_label_en,
    '#UNV'
    ) AS SERVICE_PROVIDER_ENTITY_DEPARTMENT_NAME_0040,
    COALESCE(
            CASE
                    WHEN sup.SUPPLIER_ID IS NOT NULL THEN
                    COALESCE(
                    sup.LEI_SUPPLIER_CODE,
            sup.CRN_SUPPLIER,
            sup.SIREN_SIRET_SUPPLIER_CODE
            )
    ELSE
    uo.id_uo
            END,
        '#UNV'
                ) AS SERVICE_PROVIDER_ENTITY_CODE_0050,
    COALESCE(le.legal_entity_label, '#UNV') AS SERVICE_RECIPIENT_ENTITY_NAME_0060,
    COALESCE(
            COALESCE(
            le.LEI_CODE,
            le.CRN_CODE,
            le.SIREN_CODE
            ),
        '#UNV'
                ) AS SERVICE_RECIPIENT_ENTITY_CODE_0070,
    COALESCE(le.regulated,'#UNV') AS DELIVERY_MODEL_0080,
    COALESCE(eef.eba_eco_function_id,'#UNV') AS CRITICAL_FUNCTION_ID_0090,
    COALESCE(ebl.CORE_BUSINESS_LINE_ID, '#UNV') AS CORE_BUSINESS_LINE_0100,
    COALESCE(c.SUBSTITUTABILITY, '#UNV') AS SUBSTITUTABILITY_0110,
    c.COST AS COST_0120,
    COALESCE(c.contract_id, '#UNV') AS CONTRACT_ID_0130,
    COALESCE(c.inclusion_of_resolution, '#UNV') AS INCLUSION_OF_RESOLUTION_0140,
    COALESCE(c.COUNTRY, '#UNV') AS COUNTRY_0150,
    COALESCE(mbl.LABEL, '#UNV') AS MACRO_BUSINESS_LINE_0160,
    COALESCE(mpb.LABEL, '#UNV') AS MACRO_PROCESS_0170,
    COALESCE(p.LABEL, '#UNV') AS PROCESS_0180,
    COALESCE(sp.LABEL, '#UNV') AS SUB_PROCESS_0190,
    COALESCE(atc.ACTIVITY_TYPE_LABEL, '#UNV') AS ACTIVITY_CATEGORY_0200,
    'Yes' AS IS_BLOCKING_0210
    FROM
    PHI_T_ACTIVITY a
    -- EBA_SERVICES_ID -> SERVICE_TYPE_0010
    LEFT OUTER JOIN PHI_T_ACTIVITY_TYPE_CATEGORY atc
    ON a.ACTIVITY_TYPE_ID = atc.ACTIVITY_TYPE_ID
    LEFT OUTER JOIN PHI_T_REL_EBA_SERVICES_ACTIVITY_TYPE resat
    ON atc.ACTIVITY_TYPE_ID = resat.ACTIVITY_TYPE_ID
    LEFT OUTER JOIN PHI_T_EBA_SERVICES es
    ON resat.EBA_SERVICES_ID = es.EBA_SERVICES_ID
    -- SERVICE_PROVIDER_ENTITY_NAME_0030
    LEFT OUTER JOIN PHI_T_REL_ACTIVITY_PROVIDER_ACTIVITY rapa
    ON a.activity_id = rapa.provider_activity_id
    LEFT OUTER JOIN PHI_T_CONTRACT c
    ON rapa.contract_id = c.contract_id
    LEFT OUTER JOIN phi_t_rel_contract_supplier rcs
    ON c.contract_id = rcs.contract_id
    LEFT OUTER JOIN phi_t_supplier sup
    ON rcs.supplier_id = sup.supplier_id
    -- SERVICE_PROVIDER_ENTITY_DEPARTMENT_NAME_0040
    LEFT OUTER JOIN PHI_T_REL_ACTIVITY_UO rauo
    ON a.activity_id = rauo.activity_id
    LEFT OUTER JOIN PHI_T_REF_UO uo
    ON rauo.id_uo = uo.id_uo
    -- EBA_ECO_FUNCTION_ID -> CRITICAL_FUNCTION_ID_0090
    LEFT OUTER JOIN PHI_T_REL_ACTIVITY_TYPE_EBA_ECO_FUNCTION rteef
    ON atc.ACTIVITY_TYPE_ID = rteef.activity_type_id
    LEFT OUTER JOIN PHI_T_EBA_ECO_FUNCTION eef
    ON rteef.eba_eco_function_id = eef.eba_eco_function_id
    -- CORE_BUSINESS_LINE_ID -> CORE_BUSINESS_LINE_0100
    LEFT OUTER JOIN PHI_T_EBA_BUSINESS_LINE ebl
    ON ebl.CORE_BUSINESS_LINE_ID = atc.CORE_BUSINESS_LINE_ID
    -- SUB_PROCESS_ID --> SUB_PROCESS_0190
    LEFT OUTER JOIN PHI_T_SUB_PROCESS sp
    ON atc.sub_process_id = sp.sub_process_id
    -- PROCESS_ID => PROCESS_0180
    LEFT OUTER JOIN PHI_T_PROCESS p
    ON sp.PROCESS_ID = p.PROCESS_ID
    -- MACRO_PROCESS_ID => MACRO_PROCESS_0170
    LEFT OUTER JOIN PHI_T_MACRO_PROCESS_BL mpb
    ON p.MACRO_PROCESS_ID = mpb.MACRO_PROCESS_ID
    -- BUSINESS_ID => MACRO_BUSINESS_LINE_0160
    LEFT OUTER JOIN PHI_T_MACRO_BUSINESS_LINE mbl
    ON mpb.BUSINESS_ID = mbl.BUSINESS_ID
    --  LEGAL_ENTITY_LABEL => SERVICE_PROVIDER_ENTITY_NAME_0030
    LEFT OUTER JOIN PHI_T_REL_ACTIVITY_ENTITY rae
    ON a.activity_id = rae.activity_id
    LEFT OUTER JOIN PHI_T_LEGAL_ENTITY le
    ON rae.LEGAL_ENTITY_ID = le.LEGAL_ENTITY_ID;
    """, nativeQuery = true)
    void insertReportTable();
}

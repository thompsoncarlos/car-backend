package car_backend.repository.reports;

import car_backend.model.reports.ContractReport;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportContractRepository extends JpaRepository<ContractReport, String> {

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE PHI_T_REPORT_CONTRACT_REPOSITORY_FINAL", nativeQuery = true)
    void truncateReportTable();

    @Modifying
    @Transactional
    @Query(value = """
    INSERT INTO PHI_T_REPORT_CONTRACT_REPOSITORY_FINAL (
        IDENTIFIER,
        SERVICE_IDENTIFIER,
        START_DATE_OF_THE_CONTRACT,
        END_DATE_OF_THE_CONTRACT,
        NEXT_RENEWAL_DATE,
        SERVICE_RECIPIENT_NAME,
        PROVIDER_ENTITY_NAME,
        PROVIDER_ENTITY_CODE,
        PROVIDER_ENTITY_TYPE_OF_CODE,
        PROVIDER_ENTITY_REGISTERED_ADDRESS,
        PROVIDER_PARENT_NAME,
        PROVIDER_PARENT_CODE,
        PROVIDER_PARENT_TYPE_OF_CODE,
        SUBCONTRACTOR,
        PART_OF_THE_GROUP_SERVICE_DELIVERY,
        PART_OF_THE_RESOLUTION_GROUP,
        GROUP_DEPARTMENT_RESPONSIBLE,
        BRIEF_DESCRIPTION_OF_THE_SERVICE,
        PRICING_STRUCTURE_PREDICTABLE,
        ESTIMATED_TOTAL_ANNUAL_BUDGET_COST,
        DEGREE_OF_CRITICALITY,
        CRITICAL_FUNCTION_FOR_SERVICE,
        CORE_BUSINESS_LINES_FOR_SERVICE,
        RESOLUTION_GROUPS_FOR_SERVICE,
        NAME_OF_ALTERNATIVE_SERVICE_PROVIDER,
        JURISDICTION_OF_CONTRACT,
        GOVERNING_LAW,
        COUNTRIES_WHERE_SERVICES_PROVIDED,
        RESOLUTION_RESILIENT_CONTRACT,
        PENALTIES_FOR_SUSPENSION,
        TRIGGERS_FOR_EARLY_TERMINATION,
        TERMINATION_NOTICE_PERIOD_PROVIDER,
        DURATION_POST_TERMINATION_ASSISTANCE,
        RELATIONSHIPS_BETWEEN_CONTRACTS,
        CONDITIONS_OF_PAYMENT,
        EXISTENCE_AUTOMATIC_RENEWAL_CLAUSES,
        QUANTITATIVE_PERFORMANCE_TARGETS,
        QUALITATIVE_PERFORMANCE_TARGETS,
        PARTIES_ALLOWED_TO_TERMINATE,
        ESTIMATED_TIME_FOR_SUBSTITUTABILITY
    )
    SELECT
        c.CONTRACT_ID,
        COALESCE(a1.ACTIVITY_ID, a2.ACTIVITY_ID, a3.ACTIVITY_ID),
        c.STARTING_DATE,
        c.INITIAL_END_DATE,
        c.NEXT_RENEWAL_DATE,
        CASE
            WHEN a3.ACTIVITY_ID IS NOT NULL
             AND rapa_beneficiary.BENEFICIARY_ACIVITY_ID IS NOT NULL
                THEN le_beneficiary.LEGAL_ENTITY_LABEL
            ELSE le.LEGAL_ENTITY_LABEL
        END,
        CASE
            WHEN a3.ACTIVITY_ID IS NOT NULL THEN le_provider.LEGAL_ENTITY_LABEL
            ELSE s.SUPPLIER_LABEL
        END,
        CASE
            WHEN a3.ACTIVITY_ID IS NOT NULL THEN
                COALESCE(le_provider.LEI_CODE, le_provider.CRN_CODE, le_provider.SIREN_CODE)
            ELSE
                COALESCE(s.LEI_SUPPLIER_CODE, s.CRN_SUPPLIER, s.SIREN_SIRET_SUPPLIER_CODE)
        END,
        CASE
            WHEN a3.ACTIVITY_ID IS NOT NULL THEN
                CASE
                    WHEN le_provider.LEI_CODE IS NOT NULL THEN 'LEI'
                    WHEN le_provider.CRN_CODE IS NOT NULL THEN 'Corporate registration number'
                    WHEN le_provider.SIREN_CODE IS NOT NULL THEN 'SIREN_SIRET'
                END
            ELSE
                CASE
                    WHEN s.LEI_SUPPLIER_CODE IS NOT NULL THEN 'LEI'
                    WHEN s.CRN_SUPPLIER IS NOT NULL THEN 'Corporate registration number'
                    WHEN s.SIREN_SIRET_SUPPLIER_CODE IS NOT NULL THEN 'SIREN_SIRET'
                END
        END,
        CASE
            WHEN a3.ACTIVITY_ID IS NOT NULL THEN le_provider.LEGAL_ENTITY_ADDRESS
            ELSE s.SUPPLIER_ADDRESS
        END,
        CASE
            WHEN a3.ACTIVITY_ID IS NOT NULL THEN le_provider_parent.LEGAL_ENTITY_LABEL
            ELSE sp.SUPPLIER_LABEL
        END,
        CASE
            WHEN a3.ACTIVITY_ID IS NOT NULL THEN
                COALESCE(le_provider_parent.LEI_CODE, le_provider_parent.CRN_CODE, le_provider_parent.SIREN_CODE)
            ELSE
                COALESCE(sp.LEI_SUPPLIER_CODE, sp.CRN_SUPPLIER, sp.SIREN_SIRET_SUPPLIER_CODE)
        END,
        CASE
            WHEN a3.ACTIVITY_ID IS NOT NULL THEN
                CASE
                    WHEN le_provider_parent.LEI_CODE IS NOT NULL THEN 'LEI'
                    WHEN le_provider_parent.CRN_CODE IS NOT NULL THEN 'Corporate registration number'
                    WHEN le_provider_parent.SIREN_CODE IS NOT NULL THEN 'SIREN_SIRET'
                END
            ELSE
                CASE
                    WHEN sp.LEI_SUPPLIER_CODE IS NOT NULL THEN 'LEI'
                    WHEN sp.CRN_SUPPLIER IS NOT NULL THEN 'Corporate registration number'
                    WHEN sp.SIREN_SIRET_SUPPLIER_CODE IS NOT NULL THEN 'SIREN_SIRET'
                END
        END,
        c.SUBCONTRACTING,
        CASE
            WHEN a3.ACTIVITY_ID IS NOT NULL THEN
                CASE
                    WHEN le_provider.LEGAL_ENTITY_ID = le_beneficiary.LEGAL_ENTITY_ID THEN 'Intra-entity'
                    WHEN le_provider.REGULATED IN ('Y', 'Régulé') THEN 'Intra-group - regulated entity'
                    WHEN le_provider.REGULATED IN ('N', 'Non régulé') THEN 'Intra-group - unregulated entity'
                    ELSE 'Extra-group entity'
                END
            ELSE 'Extra-group entity'
        END,
        CASE
            WHEN a3.ACTIVITY_ID IS NOT NULL THEN
                CASE
                    WHEN le_provider.PART_OF_RESOLUTION_GROUP IS NULL
                      OR TRIM(le_provider.PART_OF_RESOLUTION_GROUP) = '' THEN '#UNV'
                    WHEN UPPER(le_provider.PART_OF_RESOLUTION_GROUP) = 'YES' THEN 'Y'
                    WHEN UPPER(le_provider.PART_OF_RESOLUTION_GROUP) = 'NO' THEN 'N'
                    ELSE le_provider.PART_OF_RESOLUTION_GROUP
                END
            ELSE
                CASE
                    WHEN le.PART_OF_RESOLUTION_GROUP IS NULL
                      OR TRIM(le.PART_OF_RESOLUTION_GROUP) = '' THEN '#UNV'
                    WHEN UPPER(le.PART_OF_RESOLUTION_GROUP) = 'YES' THEN 'Y'
                    WHEN UPPER(le.PART_OF_RESOLUTION_GROUP) = 'NO' THEN 'N'
                    ELSE le.PART_OF_RESOLUTION_GROUP
                END
        END,
        c.RESPONSIBLE_DEPARTMENT,
        c.SERVICE_DESCRIPTION,
        c.PREDICTABLE_PRICING_STRUCTURE,
        c.ESTIMATED_TOTAL_ANNUAL_BUDGET,
        CASE
            WHEN COALESCE(a1.CRITICAL_ESSENTIAL, a2.CRITICAL_ESSENTIAL, a3.CRITICAL_ESSENTIAL) = 'Critical'
             AND LOWER(c.SUBSTITUTABILITY) = 'no'
                THEN 'High'
            ELSE 'Medium'
        END,
        ef.EBA_CODE,
        COALESCE(ebl.CORE_BUSINESS_LINE_NAME, '#UNV'),
        COALESCE(le.RESOLUTION_GROUP, '#UNV'),
        c.ALTERNATIVE_SERVICE_PROVIDER,
        c.JURISDICTION,
        c.GOVERNING_LAW,
        c.COUNTRY_OF_SERVICE,
        c.RESOLUTION_RESILIENCE_FEATURES,
        c.PENALTIES,
        c.TRIGGER_FOR_EARLY_TERMINATION,
        c.TERMINATION_NOTICE_PERIOD,
        c.POST_TERMINATION_ASSISTANCE_DURATION,
        c.RELATED_FRAMEWORK_AGREEMENT,
        c.CONDITIONS_OF_PAYMENT,
        c.AUTOMATIC_RENEWAL_CLAUSES,
        c.QUANTITATIVE_PERFORMANCE_TARGETS,
        c.QUALITATIVE_PERFORMANCE_TARGETS,
        c.PARTIES_ALLOWED_TO_TERMINATE,
        c.ESTIMATED_TIME_FOR_SUBSTITUTABILITY
    FROM PHI_T_CONTRACT c
    LEFT JOIN PHI_T_REL_CONTRACT_SUPPLIER rcs ON c.CONTRACT_ID = rcs.CONTRACT_ID
    LEFT JOIN PHI_T_SUPPLIER s ON rcs.SUPPLIER_ID = s.SUPPLIER_ID
    LEFT JOIN PHI_T_SUPPLIER sp ON s.SUPPLIER_PARENT_ID = sp.SUPPLIER_ID
    LEFT JOIN PHI_T_REL_CONTRACT_PRESTATION rcp ON c.CONTRACT_ID = rcp.CONTRACT_ID
    LEFT JOIN PHI_T_PRESTATION p ON rcp.PRESTATION_ID = p.PRESTATION_ID
    LEFT JOIN PHI_T_REL_ACTIVITY_PROVIDER_PRESTATION rapp ON p.PRESTATION_ID = rapp.PRESTATION_ID
    LEFT JOIN PHI_T_ACTIVITY a1 ON rapp.ACTIVITY_ID = a1.ACTIVITY_ID
    LEFT JOIN PHI_T_REL_APPLICATION_CONTRACT rac ON c.CONTRACT_ID = rac.CONTRACT_ID
    LEFT JOIN PHI_T_APPLICATION app ON rac.APPLICATION_ID = app.APPLICATION_ID
    LEFT JOIN PHI_T_REL_ACTIVITY_APPLICATION raa ON app.APPLICATION_ID = raa.APPLICATION_ID
    LEFT JOIN PHI_T_ACTIVITY a2 ON raa.ACTIVITY_ID = a2.ACTIVITY_ID
    LEFT JOIN PHI_T_REL_ACTIVITY_PROVIDER_ACTIVITY rapa ON c.CONTRACT_ID = rapa.CONTRACT_ID
    LEFT JOIN PHI_T_ACTIVITY a3 ON rapa.PROVIDER_ACTIVITY_ID = a3.ACTIVITY_ID
    LEFT JOIN PHI_T_REL_ACTIVITY_ENTITY rae
        ON COALESCE(a1.ACTIVITY_ID, a2.ACTIVITY_ID, a3.ACTIVITY_ID) = rae.ACTIVITY_ID
    LEFT JOIN PHI_T_LEGAL_ENTITY le ON rae.LEGAL_ENTITY_ID = le.LEGAL_ENTITY_ID
    LEFT JOIN PHI_T_REL_ACTIVITY_ENTITY rae_provider ON a3.ACTIVITY_ID = rae_provider.ACTIVITY_ID
    LEFT JOIN PHI_T_LEGAL_ENTITY le_provider ON rae_provider.LEGAL_ENTITY_ID = le_provider.LEGAL_ENTITY_ID
    LEFT JOIN PHI_T_LEGAL_ENTITY le_provider_parent
        ON le_provider.LEGAL_ENTITY_PARENT_ID = le_provider_parent.LEGAL_ENTITY_ID
    LEFT JOIN PHI_T_REL_ACTIVITY_PROVIDER_ACTIVITY rapa_beneficiary
        ON a3.ACTIVITY_ID = rapa_beneficiary.PROVIDER_ACTIVITY_ID
    LEFT JOIN PHI_T_REL_ACTIVITY_ENTITY rae_beneficiary
        ON rapa_beneficiary.BENEFICIARY_ACIVITY_ID = rae_beneficiary.ACTIVITY_ID
    LEFT JOIN PHI_T_LEGAL_ENTITY le_beneficiary
        ON rae_beneficiary.LEGAL_ENTITY_ID = le_beneficiary.LEGAL_ENTITY_ID
    LEFT JOIN PHI_T_ACTIVITY_TYPE_CATEGORY atc
        ON COALESCE(a1.ACTIVITY_TYPE_ID, a2.ACTIVITY_TYPE_ID, a3.ACTIVITY_TYPE_ID) = atc.ACTIVITY_TYPE_ID
    LEFT JOIN PHI_T_EBA_ECO_FUNCTION ef ON atc.EBA_ECO_FUNCTION_ID = ef.EBA_ECO_FUNCTION_ID
    LEFT JOIN PHI_T_EBA_BUSINESS_LINE ebl ON atc.CORE_BUSINESS_LINE_ID = ebl.CORE_BUSINESS_LINE_ID
    WHERE COALESCE(a1.ACTIVITY_ID, a2.ACTIVITY_ID, a3.ACTIVITY_ID) IN (
        SELECT DISTINCT apa.PROVIDER_ACTIVITY_ID
        FROM PHI_T_REL_ACTIVITY_PROVIDER_ACTIVITY apa
        WHERE apa.PROVIDER_ACTIVITY_ID IS NOT NULL
    )
    """, nativeQuery = true)
    void insertReportTable();

}

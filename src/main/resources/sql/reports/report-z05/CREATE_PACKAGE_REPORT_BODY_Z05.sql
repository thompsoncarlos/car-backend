create or replace PACKAGE BODY P_REPORT_Z05 AS

PROCEDURE TRUNCATE_Z05 IS
BEGIN
EXECUTE IMMEDIATE 'TRUNCATE TABLE PHI_T_REPORT_Z05';
END TRUNCATE_Z05;
---------------------------------------------------------------------------------------------------------------------------------------------

PROCEDURE INSERT_INFO_REPORT_Z05 IS
BEGIN
INSERT INTO PHI_T_REPORT_Z05 (SERVICE_IDENTIFIER_0005, SERVICE_TYPE_0010, UNIQUE_SERVICE_TITLE_BK_TAXO_0020, CORE_BUSINESS_LINE_NAME_0030, CORE_BUSINESS_LINE_ID_0040)
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
        ON ebl.core_business_line_id = atc.core_business_line_id;
COMMIT;

END INSERT_INFO_REPORT_Z05;
------------------------------------------------------------------------------------------------------------------

PROCEDURE MAIN_Z05 IS 
BEGIN 
TRUNCATE_Z05();
INSERT_INFO_REPORT_Z05();
END MAIN_Z05;
------------------------------------------------------------------------------------------------------------------

END P_REPORT_Z05;
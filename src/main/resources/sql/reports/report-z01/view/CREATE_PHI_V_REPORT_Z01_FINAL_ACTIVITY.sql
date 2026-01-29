
  CREATE OR REPLACE FORCE EDITIONABLE VIEW "HPIOA"."PHI_V_REPORT_Z01_FINAL_ACTIVITY" ("SERVICE_IDENTIFIER_0005", "SERVICE_TYPE_0010", "UNIQUE_SERVICE_TITLE_BK_TAXO_0020", "SERVICE_RECIPIENT_NAME_0030", "SERVICE_RECIPIENT_CODE_0040", "SERVICE_PROVIDER_ENTITY_NAME_0050", "SERVICE_PROVIDER_ENTITY_CODE_0060", "SERVICE_PROVIDER_ENTITY_CODE_TYPE_0070", "SERVICE_PROVIDER_PARENT_NAME_0080", "SERVICE_PROVIDER_PARENT_CODE_0090", "SERVICE_PROVIDER_PARENT_CODE_TYPE_0100", "SERVICE_PROVIDER_DELIVERY_0110", "CRITICALITY_0120", "CONTRACT_ID_0130", "GOVERNING_LAW_0140", "RESOLUTION_RESILIENCE_FEATURES_0150", "RESOLUTION_RESILIENCE_BRP_0160", "RESOLUTION_RESILIENCE_ALT_MIT_0170", "CRITICAL_ICT_THD_PARTY_SERV_PROV_UND_DORA_0180", "ICT_SERVICE_UNDER_DORA_0190") AS 
  select distinct

a.activity_id   ---5
,mapeba.eba_services_id  --10
,a.activity_id||' - '||a.activity_label  Activty --20
--,mapeba.legal_entity_label --0030
,benel.legal_entity_label_0030
--,mapeba.crn_code  col_0040

--,nvl(mapeba.lei_code,nvl(mapeba.CRN_code ,nvl(mapeba.SIREN_CODE,'#UNV'))) as col_0040
,benel.col_0040
/*,maa.supplier_label-- 0050
,nvl(maa.LEI_SUPPLIER_CODE,nvl(maa.CRN_SUPPLIER ,nvl(maa.SIREN_SIRET_SUPPLIER_CODE,'#UNV'))) as service_provider_entity_code_0060 --0060
, decode (maa.LEI_SUPPLIER_CODE,null,decode(nvl(maa.CRN_SUPPLIER ,maa.SIREN_SIRET_SUPPLIER_CODE),null,'#UNV','Corporate registration number' ) ,'LEI')  col__0070--070
*/

,mapeba.legal_entity_label --0050
,nvl(mapeba.lei_code,nvl(mapeba.CRN_code ,nvl(mapeba.SIREN_CODE,'#UNV'))) as col_0060
--,decode (mapeba.lei_code,null,decode(nvl(mapeba.CRN_code ,mapeba.SIREN_CODE),null,'#UNV','Corporate registration number' ) ,'LEI')  col__0070--070
,decode (mapeba.lei_code ,null  ,decode(mapeba.CRN_code, null     ,decode(mapeba.SIREN_CODE,null,'#UNV','SIREN/SIRET'),'Corporate registration number' ) ,'LEI')  as col__0070

/*
,maa.parent_supplier_label   --0080
,nvl(maa.parent_LEI_SUPPLIER_CODE,nvl(maa.parent_CRN_SUPPLIER ,nvl(maa.parent_SIREN_SIRET_SUPPLIER_CODE,'#N/A')) ) as service_provider_parent_code_0090 --0090
, decode (maa.parent_LEI_SUPPLIER_CODE,null,decode(nvl(maa.parent_CRN_SUPPLIER ,maa.parent_SIREN_SIRET_SUPPLIER_CODE),null,'#N/A','Corporate registration number' ) ,'LEI')  col__0100--0100
*/

,mapeba.PARENT_ENTITY_NAME --0080
,nvl(mapeba.PARENT_LEI_CODE,nvl(mapeba.parent_crn_code ,nvl(mapeba.PARENT_siren_CODE,'#N/A')) ) as service_provider_parent_code_0090 --0090
--, decode (mapeba.PARENT_LEI_CODE,null,decode(nvl(mapeba.parent_crn_code ,mapeba.PARENT_siren_CODE),null,'#N/A','Corporate registration number' ) ,'LEI')  col__0100 --0100
,decode (mapeba.PARENT_LEI_CODE ,null  ,decode(mapeba.parent_crn_code, null     ,decode(mapeba.PARENT_siren_CODE,null,'#N/A','SIREN/SIRET'),'Corporate registration number' ) ,'LEI')  as col__0100

,'External entity' as col__0110
,a.critical_essential as col__0120 -- critical essentiel


,maa.contract_id --0130
,maa.governing_law --0140
--,maa.RESOLUTION_RESILIENCE_FEATURES -- 0150
,nvl(par150.result_mapping,'Not assessed') ALTERNATIVE_MITIGATING_ACTIONS -- 0150
,nvl(par150.result_mapping,'Not assessed')  as col__0160
--,maa.ALTERNATIVE_MITIGATING_ACTIONS --0170
,nvl(par170.result_mapping,'No') ALTERNATIVE_MITIGATING_ACTIONS -- 0170

--,maa.CRIT_ICT_THRD_P_SER_PROV_DORA --- 0180
,decode(lower(maa.CRIT_ICT_THRD_P_SER_PROV_DORA),'critical','yes','no')  CRIT_ICT_THRD_P_SER_PROV_DORA--- 0180
,maa.ICT_SERVICE_UNDER_DORA -- 0190

from phi_t_activity a
left outer join phi_v_activity_activity_map maa on a.activity_id=maa.activity_id 
left outer join phi_v_activity_bene_legal_entity benel on benel.activity_id  = a.activity_id 
left outer join phi_v_activity_eba_entity_map mapeba on a.activity_id = mapeba.activity_id
left outer join phi_t_param_report_z01 par170  on lower( maa.ALTERNATIVE_MITIGATING_ACTIONS ) = lower(par170.mapping_value) and par170.col_number='0170'
left outer join phi_t_param_report_z01 par150  on lower( maa.RESOLUTION_RESILIENCE_FEATURES ) = lower(par150.mapping_value) and par150.col_number='0150'


order by a.activity_id;


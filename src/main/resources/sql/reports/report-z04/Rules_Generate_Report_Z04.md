B.                Report Objectives Z08.04
This report aims at listing critical functions to the activities marked 'critical' or 'critical and important ' in report Z08.01 (relating to an EBA service).

An activity will appear in report Z08.04:

if it is present in report Z08.01.
It’s ‘critical’ or ‘critical and important’ (do not select ‘important’ ones)
C.               Concept of propagation – Critical, important or critical and important
Same workflow as the one explained in detail in the Z08.01 specs.

II.            Fill out the report
Once an activity is identified as part of report Z08.04, the fields must be filled out.

 

All the fields are composed as follows:

Label → name of the field - example: Service Identifier
 Identifier → identifying number of the same field - example: 0005
Please find below an explanation of each field with the following details: a description, the transformation rules, the source, and an example.

1.            General
Each field is mandatory 
N°Z08_04_PK: Primary key rules | Lines can only be duplicated when at least one of the fields in the primary key is different. If the primary key consists of 3 fields for example, there cannot be 2 lines in the report with the same values in all 3 of these fields. In Report Z08.04, these fields are 0010, 0020, 0030,0040 values.
N°Z08_04_GI: General information | If an activity is associated with several critical functions for one country only, the line should be duplicated.
N°Z08_04_GI_2: General information | If an activity is associated with only one critical function for different countries, the line should be also duplicated.
N°Z08_04_GI_3: General information | If an activity is associated with several critical functions for different countries, the line should be duplicated as many times as the number of combinations (functions x countries) we have identified.
N°Z08_04_GI_4: General information | If the same activity is linked to several (more than 1) EBA Services, then the entire duplicated lines above will have to be repeated (EBA services x countries x functions).
The formats are there for information purposes.

2.            Service Identifier - 0005
Description: ID Code representing the Activity
Transformation rule:
N°Z08_04_005: Retrievement of Z08 01 activity related to people | Retrieve the activities that are related to critical functions
Source: Activity tab - ID Activity column
Example: BPRI_CDF_45
Format: text
3.            Service Type - 0010
Description: the ID of the activity's service type  
Transformation rule: 
N°Z08_04_0010: EBA services correspondence | Indicate the id of the EBA services corresponding to the Service Identifier - 0005
Source: EBA Service tab - Service ID column
Example: 6.1 (refers to Human Resources)
Format: text
4.            Unique service title as per bank taxonomy - 0020
Description: a merge of the Service Identifier AND the Label Activity → Service_Identifier+_+Label Acitivty  
Transformation rule: 
N°Z08_04_0020: Service Identifier+Label activity | "Service Identifier"+"_"+"Label activity" 
N° Z08_04_0020_2: The Label Activity can be either in French or English
Source:
Service identifier → Activity tab - ID Activity column
Label Activity →  Activity tab - Label Activity column
Example: BPRI_CDF_45_Traiter les évènements liés à un service de paiement contre la fraude
Format: text
5.            Critical function - Country - 0030
Description:  Indicate the country corresponding to the activity
Transformation rule: 
N°Z08_04_0030: Country | Indicate the country corresponding to the Legal Entity of the service
Source: Legal Entity tab – Country
Example: France
Format: text
6.            Critical function - ID - 0040
Description:  ID corresponding to the critical function according to the EBA taxonomy
Transformation rule: 
N°Z08_04_0040: Critical function ID: A single activity can be linked to several critical function IDs; in this case, the activity will be duplicated
Source: EBA eco function tab – EBA code
Example: 0040
Format: text
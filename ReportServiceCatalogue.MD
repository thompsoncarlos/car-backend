
Generate the template 6 : Service catalogue
  
The fields are composed as follows: 
•	Label → name of the field - example: Service Identifier 
•	Identifier → identifying number of the same field

Please find below an explanation of each field with the following details: a description, the transformation rules, the source, and an example. 
1.      General 
•	Each field is mandatory  
N°SC_PK: Primary key rules | Lines can be duplicated when at least one of the fields in the primary key is different. If the primary key consists of 3 fields, for example, there cannot be 2 lines in the report with the same values in all 3 of these fields. In Report Service Catalogue the primary keys are 0010, 0015, 0050, 0070, 0130 values. 
•	The formats are there for information purpose.
2.      Service Identifier - 0005 
•	Description: ID Code representing the Activity 
•	Transformation rule: 
N°SC_0005: Retrieve the information present in field 0005 from the activity of report Z08.01 according to the activity
•	Source: Z08.01 Report - 0005
•	Example: BPRI_CDF_45 
•	Format: text
3.      Service Type - 0010 
•	Description: the ID of the activity's service type   
•	Transformation rule:  
N°SC_0010: Retrieve the information present in field 0010 from the activity of report Z08.01 according to the activity
•	Source: Z08.01 Report – 0010
•	Example: 6.1 (refers to Human Resources) 
•	Format: text 
4.      Unique service title as per bank taxonomy - 0015
•	Description: a merge of the Service Identifier AND the Label Activity → Service_Identifier+_+Label Acitivty   
•	Transformation rule:
N°SC_0015: Retrieve the information present in field 0020 from the activity of report Z08.01 according to the activity
Source: Z08.01 Report – 0020
•	Example: BPRI_CDF_45_Traiter les évènements liés à un service de paiement contre la fraude 
•	Format: text 
5.      Service Description - 0020 
•	Description: Description of the activity 
•	Transformation rule:  
N°SC_0020: Display text from the field without translation
•	Source: Activity tab - description
•	Example: Activité qui permet d’assurer la continuité de l’activité de l’audit UX des applications SGG
•	Format: text 
6.      Service provider – Entity - Name - 0030 
•	Description:  The activity provider name
•	Transformation rule: 
N°SC_0030: Retrieve the information present in field 0050 from the activity of report Z08.01 according to the activity
•	Source: Z08.01 Report – 0050
•	Example: US consulting  
•	Format: text 
7.      Service provider – Entity – Departement Name - 0040 
•	Description:  UO name of the provider
•	Transformation rule:  
N°SC_0040: Display the field “UO Name” corresponding to the Service identifier (0005)
•	Source: UO tab - UO Name of Activity provider
•	Example: DSI SGG
•	Format : text 
8.      Service provider – Entity – Code - 0050 
•	Description:  UO code of the provider
•	Transformation rule:  
N°SC_0050: Retrieve the information present in field 0060 from the activity of report Z08.01 according to the activity
•	Source: Z08.01 Report – 0060
•	Example: G1245157
•	Format : text 
9.      Service recipient – Entity – Name - 0060 
•	Description:  Recipient Entity name 
•	Transformation rule:
N°SC_0060: Retrieve the information present in field 0030 from the activity of report Z08.01 according to the activity
•	Source: Z08.01 Report – 0030
•	Example: BPCE – IT
•	Format : text 
10.   Service recipient – Entity – Code - 0070 
•	Description: Recipient Entity code
•	Transformation rule:  
N°SC_0070: Retrieve the information present in field 0040 from the activity of report Z08.01 according to the activity
•	Source: Z08.01 Report – 0040
•	Example: 1 
•	Format : text 
11.   Delivery model - 0080 
•	Description:  description of the delivery of the service  
•	Transformation rule:
N°SC_0080: Retrieve the information present in field 0110 from the activity of report Z08.01 according to the activity
Source: Z08.01 Report – 0110
•	Format : text 
12.   Critical Functions - ID - 0090 
•	Description: 
•	Transformation rule:  
N°SC_0090: Retrieve the information present in field 0040 from the activity of report Z08.04 according to the critical functions
N°SC_0090_2 :If Critical Functions = "Oui", display each ID with a ";" separator
•	If Critical Functions = "Non", ID is not displayed
•	If no ID in the field, display "Not Applicable"
•	Source: Z08.04 - ID 0040
Warning! no duplicated line but possibly a list of codes separated by “;” 
•	Example: 0040
•	Format : text 
13.   Core Business Line - ID - 0100 
•	Description:  ID corresponding to the Core business line according to the EBA taxonomy 
•	Transformation rule: 
N°SC_0100 - Core business ID | A single activity can be linked to several Core business line IDs; in this case – the ID will then be duplicated in the same field with “;”
•	Source: EBA Business line tab – Core business line ID 
•	Example: 1 
•	Format : text 
14.   Substitutability - 0110 
•	Description:  Indicates whether the service can be reasonably replaced within an acceptable timeframe without major disruption  
•	Transformation rule: 
N°SC_0110: Display field “Substituability” from Continuité tab
•	Source: Contract tab - Niveau de Substituabilité entreprise (onglet Continuité)
•	Example: no
•	Format : text 
15.   Cost - 0120 
•	Description:  annual cost of the service 
•	Transformation rule:  
N°SC_0120: Display field " Estimated total annual budget cost for the service " from JurisLine + “Devise” field
•	Source: Contract - Estimated total annual budget cost for the service
•	Example: 100000EUR
•	Format : text 
16.   Contact ID - 0130 
•	Description:  contract Id of the prestation 
•	Transformation rule:  
N°SC_0130: Retrieve the information present in field 0130 from the activity of report Z08.01 according to the activity
•	Display the ID field
•	If not displayed, fill with “N/A”
•	A single activity can be linked to Contract IDs; in this case, the activity will be duplicated 
•	Source: Z08.01 Report – 0130
•	Example: CT121456
•	Format : text 
17.   Inclusion of Resolution Clause - 0140 
•	Description:  Specifies whether the contract includes resolution clauses that ensure continuity during resolution ( e.g , no termination, portability clauses:  
N°SC_0140: - Display « Yes » if “Signé”
- Display « NO » if « Accepté » or « en lancement » or « en négociation » or « sans réponses » or « refusé »
- Display « Not applicable » if “Non applicable”
•	Source: "Statut de la clause de résolution bancaire" from Jurisline
•	Example: no 
•	Format: text 
18.   Location (Country) - 0150 
•	Description:  Location where the service is delivered or used - can be a physical site or cloud/geographic region
•	Transformation rule:  
N°SC_0150: Display flied "Location (Country)" from JurisLine
•	Source: contract tab - Pays
•	Example: France
•	Format: text

19.     Macro-Business Line- 0160 
•	Description: 
•	Transformation rule:  
N°SC_0160: Display flied "name"
•	Source: Macro Business Line tab - Name
•	Example: xxxx
•	Format: text

20.     Macro-process - 0170 
•	Description: 
•	Transformation rule:  
N°SC_0170: Display flied "name"
•	Source: Macro-process tab - Name
•	Example: xxxx
•	Format: text

21.     Process - 0180 
•	Description: 
•	Transformation rule:  
N°SC_0180: Display flied "name"
•	Source: Process tab - Name
•	Example: xxxx
•	Format: text

22.     Sub-process- 0190 
•	Description: 
•	Transformation rule:  
N°SC_0190: Display flied "name"
•	Source: Sub-process- Name
•	Example: xxxx
•	Format: text

23.     Activity Category- 0200
•	Description: 
•	Transformation rule:  
N°SC_0200: Display flied "name"
•	Source: Activity Category - Name
•	Example: xxxx
•	Format: text
24.     Relevant services (Blocking Yes/No) - 0210 
•	Description: 
•	Transformation rule:  
N°SC_0210: Always say yes for now (the ultimate goal is to see the recovery of all current activities, even those not critical to this report).
Source: Activity Category - xxxx
•	Example: Yes
•	Format: text

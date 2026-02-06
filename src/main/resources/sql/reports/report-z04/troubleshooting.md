# TROUBLESHOOTING - REPORT Z04 DOCUMENTATION

## ISSUE: CREATION PACKAGE BODY P_REPORT_Z04

### Problem Description

When attempting to create the package body `P_REPORT_Z04`, you may encounter compilation errors indicating that the identifier must be declared and that the package body cannot be compiled without its specification.

### Error Message
````sql
---------------------
LINE/COL  ERROR
------------------------------------------------------------------
0/0       PL/SQL: Compilation unit analysis terminated
1/14      PLS-00201: l'identificateur 'P_REPORT_Z04' doit être déclaré
1/14      PLS-00304: impossible de compiler le corps de 'P_REPORT_Z04' sans sa spécification
Erreurs : consulter le journal du compilateur

Elément Package Body P_REPORT_Z04 compilé
````
### Possible Causes

1. **Missing Package Specification**: The package specification for `P_REPORT_Z04` may not have been created or is not accessible in the current schema.
   
### Resolution Steps
Create the package specification for `P_REPORT_Z04` before attempting to create the package body. Ensure that the specification is correctly defined and compiled.

```sql      
CREATE OR REPLACE PACKAGE P_REPORT_Z04 AS

    PROCEDURE TRUNCATE_Z04;
    
    PROCEDURE INSERT_INFO_REPORT_Z04;
    
    PROCEDURE MAIN_Z04;

END P_REPORT_Z04;
/
```
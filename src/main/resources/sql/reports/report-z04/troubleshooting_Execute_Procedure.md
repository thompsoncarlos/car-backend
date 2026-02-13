# TROUBLESHOOTING - REPORT Z04 DOCUMENTATION

## ISSUE: CREATION PACKAGE BODY P_REPORT_Z04

### Problem Description

When attempting to execute procedure: EXEC P_REPORT_Z04.MAIN_Z04.

### Error Message
````sql
Erreur commençant à la ligne: 13 de la commande -
BEGIN P_REPORT_Z04.MAIN_Z04; END;
Rapport d'erreur -
ORA-00001: violation de contrainte unique (HPIOA.PHI_X_REPORT_Z04_UNIQ)
ORA-06512: à "HPIOA.P_REPORT_Z04", ligne 11
ORA-06512: à "HPIOA.P_REPORT_Z04", ligne 44
ORA-06512: à ligne 1
00001. 00000 -  "unique constraint (%s.%s) violated"
*Cause:    An UPDATE or INSERT statement attempted to insert a duplicate key.
           For Trusted Oracle configured in DBMS MAC mode, you may see
           this message if a duplicate entry exists at a different level.
*Action:   Either remove the unique restriction or do not insert the key.
````

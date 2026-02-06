# ReportZ04 - Correções Oracle Recomendadas

## 🔍 Problemas Encontrados no SQL

Durante a análise da structure Oracle para ReportZ04, foram identificados alguns problemas que devem ser corrigidos para garantir o funcionamento correto.

---

## ❌ Problema 1: Procedure INSERT_INFO_REPORT_Z04 com Sintaxe Incorreta

**Arquivo:** `CREATE_PACKAGE_REPORT_Z04.sql`

### Linha Problemática:
```sql
PROCEDURE INSERT_INFO_REPORT_Z04 IS 
BEGIN
    INSERT INTO PHI_T_REPORT_Z04 (...)
    SELECT DISTINCT ... 
    FROM ... 
    LEFT OUTER JOIN ...
    -- ❌ FALTA: END INSERT_INFO_REPORT_Z04; e ponto-e-vírgula
END INSERT_INFO_REPORT_Z04;
```

### ❌ Erro Sintático:
Na linha do final do INSERT, falta `END INSERT_INFO_REPORT_Z04;` - a procedure está incompleta.

### ✅ Correção Recomendada:

```sql
PROCEDURE INSERT_INFO_REPORT_Z04 IS 
BEGIN
    INSERT INTO PHI_T_REPORT_Z04 (
        SERVICE_IDENTIFIER_0005,
        SERVICE_TYPE_0010,
        UNIQUE_SERVICE_TITLE_BK_TAXO_0020,
        CRITICAL_FUNCTION_COUNTRY_0030,
        CRITICAL_FUNCTION_COUNTRY_CODE_0040
    )
    SELECT DISTINCT
        a.ACTIVITY_ID,
        a.ACTIVITY_LABEL,
        CONCAT(a.ACTIVITY_ID, a.ACTIVITY_LABEL),
        le.COUNTRY,
        eef.EBA_CODE
    FROM
        PHI_T_ACTIVITY a
        LEFT OUTER JOIN PHI_T_REL_ACTIVITY_ENTITY rae 
            ON a.ACTIVITY_ID = rae.ACTIVITY_ID
        LEFT OUTER JOIN PHI_T_LEGAL_ENTITY le 
            ON rae.LEGAL_ENTITY_ID = le.LEGAL_ENTITY_ID
        LEFT OUTER JOIN PHI_T_ACTIVITY_TYPE_CATEGORY atc 
            ON a.ACTIVITY_TYPE_ID = atc.ACTIVITY_TYPE_ID
        LEFT OUTER JOIN PHI_T_REL_EBA_SERVICES_ACTIVITY_TYPE resat 
            ON atc.ACTIVITY_TYPE_ID = resat.ACTIVITY_TYPE_ID
        LEFT OUTER JOIN PHI_T_EBA_SERVICES es 
            ON resat.EBA_SERVICES_ID = es.EBA_SERVICES_ID
        LEFT OUTER JOIN PHI_T_EBA_ECO_FUNCTION eef 
            ON atc.EBA_ECO_FUNCTION_ID = eef.EBA_ECO_FUNCTION_ID;
    
    COMMIT;
    -- ✅ AGORA: fechamento correto
END INSERT_INFO_REPORT_Z04;
```

---

## ❌ Problema 2: JOIN Incorreta na Procedure

### Linha Problemática:
```sql
LEFT OUTER JOIN PHI_T_REL_EBA_SERVICES_ACTIVITY_TYPE resat
    ON atc.ACTIVITY_TYPE_ID = resat.ACTIVITY_TYPE_ID
```

### ⚠️ Questão (Potencial Erro):
A tabela `PHI_T_REL_EBA_SERVICES_ACTIVITY_TYPE` pode não estar mapeada corretamente.

### ✅ Verificação Recomendada:

```sql
-- Verificar a estrutura da tabela de relacionamento
DESC PHI_T_REL_EBA_SERVICES_ACTIVITY_TYPE;

-- Ou
SELECT COLUMN_NAME, DATA_TYPE 
FROM USER_TAB_COLUMNS 
WHERE TABLE_NAME = 'PHI_T_REL_EBA_SERVICES_ACTIVITY_TYPE';
```

Se a tabela se chama `PHI_T_REL_EBA_SERVICES_ENTITY_TYPE`, usar:
```sql
LEFT OUTER JOIN PHI_T_REL_EBA_SERVICES_ENTITY_TYPE resat
    ON a.ACTIVITY_TYPE_ID = resat.ACTIVITY_TYPE_ID
```

---

## ❌ Problema 3: Constraint na Tabela com Coluna Potencialmente Nullable

**Arquivo:** `CREATE_T_REPORT_Z04.sql`

### Problema:
```sql
CONSTRAINT "PHI_X_REPORT_Z04_UNIQ" UNIQUE ("SERVICE_TYPE_0010")
```

A coluna `SERVICE_TYPE_0010` tem UNIQUE mas não tem NOT NULL. 

### Risco:
Em Oracle, valores NULL são permitidos em constraints UNIQUE, o que pode causar duplicatas indesejadas.

### ✅ Solução Recomendada:

```sql
CREATE TABLE "HPIOA"."PHI_T_REPORT_Z04" 
(	
    "SERVICE_IDENTIFIER_0005" VARCHAR2(400 BYTE) NOT NULL ENABLE, 
    "SERVICE_TYPE_0010" VARCHAR2(400 BYTE) NOT NULL ENABLE,  -- ✅ Adicionar NOT NULL
    "UNIQUE_SERVICE_TITLE_BK_TAXO_0020" VARCHAR2(800 BYTE),
    "CRITICAL_FUNCTION_COUNTRY_0030" VARCHAR2(400 BYTE), 
    "CRITICAL_FUNCTION_COUNTRY_CODE_0040" VARCHAR2(400 BYTE),
    CONSTRAINT "PHI_X_REPORT_Z04_UNIQ" UNIQUE ("SERVICE_TYPE_0010")
      USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
      STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
      PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
      BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
      TABLESPACE "HPICA1"  ENABLE
)
```

---

## ❌ Problema 4: Falta de Índices para Performance

### ⚠️ Recomendação de Performance:

Adicionar índices nas colunas mais consultadas:

```sql
-- Índice na chave primária e estrangeiras
CREATE INDEX PHI_X_REPORT_Z04_ID 
    ON PHI_T_REPORT_Z04(SERVICE_IDENTIFIER_0005) 
    TABLESPACE HPICA1;

CREATE INDEX PHI_X_REPORT_Z04_TYPE 
    ON PHI_T_REPORT_Z04(SERVICE_TYPE_0010) 
    TABLESPACE HPICA1;

CREATE INDEX PHI_X_REPORT_Z04_COUNTRY 
    ON PHI_T_REPORT_Z04(CRITICAL_FUNCTION_COUNTRY_0030) 
    TABLESPACE HPICA1;
```

---

## ❌ Problema 5: Falta de Log/Audit na Procedure

### Recomendação de Melhoria:

```sql
PROCEDURE MAIN_Z04 IS
    v_count_truncated NUMBER;
    v_count_inserted NUMBER;
BEGIN
    -- Log de início
    DBMS_OUTPUT.PUT_LINE('Iniciando P_REPORT_Z04.MAIN_Z04 em ' || SYSDATE);
    
    TRUNCATE_Z04();
    
    -- Log após truncate
    DBMS_OUTPUT.PUT_LINE('Tabela truncada em ' || SYSDATE);
    
    INSERT_INFO_REPORT_Z04();
    
    -- Contar registros inseridos
    SELECT COUNT(*) INTO v_count_inserted 
    FROM PHI_T_REPORT_Z04;
    
    -- Log final
    DBMS_OUTPUT.PUT_LINE('Procedure concluída: ' || v_count_inserted || 
                         ' registros inseridos em ' || SYSDATE);
    
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERRO em P_REPORT_Z04.MAIN_Z04: ' || SQLERRM);
        RAISE;
END MAIN_Z04;
```

---

## ✅ Script de Correção Completo

Para corrigir todos os problemas, execute:

```sql
-- 1. Recrear a tabela com constraints corretas
DROP TABLE PHI_T_REPORT_Z04 CASCADE CONSTRAINTS;

CREATE TABLE "HPIOA"."PHI_T_REPORT_Z04" 
(	
    "SERVICE_IDENTIFIER_0005" VARCHAR2(400 BYTE) NOT NULL ENABLE, 
    "SERVICE_TYPE_0010" VARCHAR2(400 BYTE) NOT NULL ENABLE,
    "UNIQUE_SERVICE_TITLE_BK_TAXO_0020" VARCHAR2(800 BYTE),
    "CRITICAL_FUNCTION_COUNTRY_0030" VARCHAR2(400 BYTE), 
    "CRITICAL_FUNCTION_COUNTRY_CODE_0040" VARCHAR2(400 BYTE),
    CONSTRAINT "PHI_X_REPORT_Z04_UNIQ" UNIQUE ("SERVICE_TYPE_0010")
      USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
      STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
      PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
      BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
      TABLESPACE "HPICA1"  ENABLE
) 
PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
NOCOMPRESS LOGGING
STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
TABLESPACE "HPICA1";

-- 2. Recrear o package com procedure corrigida
CREATE OR REPLACE PACKAGE BODY P_REPORT_Z04 AS 

    PROCEDURE TRUNCATE_Z04 IS 
    BEGIN 
        EXECUTE IMMEDIATE 'TRUNCATE TABLE PHI_T_REPORT_Z04';
        DBMS_OUTPUT.PUT_LINE('Tabela PHI_T_REPORT_Z04 truncada com sucesso');
    END TRUNCATE_Z04;

    PROCEDURE INSERT_INFO_REPORT_Z04 IS 
        v_inserted NUMBER;
    BEGIN
        INSERT INTO PHI_T_REPORT_Z04 (
            SERVICE_IDENTIFIER_0005,
            SERVICE_TYPE_0010,
            UNIQUE_SERVICE_TITLE_BK_TAXO_0020,
            CRITICAL_FUNCTION_COUNTRY_0030,
            CRITICAL_FUNCTION_COUNTRY_CODE_0040
        )
        SELECT DISTINCT
            a.ACTIVITY_ID,
            a.ACTIVITY_LABEL,
            CONCAT(a.ACTIVITY_ID, a.ACTIVITY_LABEL),
            le.COUNTRY,
            eef.EBA_CODE
        FROM
            PHI_T_ACTIVITY a
            LEFT OUTER JOIN PHI_T_REL_ACTIVITY_ENTITY rae 
                ON a.ACTIVITY_ID = rae.ACTIVITY_ID
            LEFT OUTER JOIN PHI_T_LEGAL_ENTITY le 
                ON rae.LEGAL_ENTITY_ID = le.LEGAL_ENTITY_ID
            LEFT OUTER JOIN PHI_T_ACTIVITY_TYPE_CATEGORY atc 
                ON a.ACTIVITY_TYPE_ID = atc.ACTIVITY_TYPE_ID
            LEFT OUTER JOIN PHI_T_REL_EBA_SERVICES_ACTIVITY_TYPE resat 
                ON atc.ACTIVITY_TYPE_ID = resat.ACTIVITY_TYPE_ID
            LEFT OUTER JOIN PHI_T_EBA_SERVICES es 
                ON resat.EBA_SERVICES_ID = es.EBA_SERVICES_ID
            LEFT OUTER JOIN PHI_T_EBA_ECO_FUNCTION eef 
                ON atc.EBA_ECO_FUNCTION_ID = eef.EBA_ECO_FUNCTION_ID;
        
        v_inserted := SQL%ROWCOUNT;
        DBMS_OUTPUT.PUT_LINE(v_inserted || ' registros inseridos em PHI_T_REPORT_Z04');
        COMMIT;
    END INSERT_INFO_REPORT_Z04;

    PROCEDURE MAIN_Z04 IS 
    BEGIN 
        TRUNCATE_Z04();
        INSERT_INFO_REPORT_Z04();
        DBMS_OUTPUT.PUT_LINE('P_REPORT_Z04.MAIN_Z04 executada com sucesso');
    END MAIN_Z04;

END P_REPORT_Z04;
/

-- 3. Criar índices
CREATE INDEX PHI_X_REPORT_Z04_ID 
    ON PHI_T_REPORT_Z04(SERVICE_IDENTIFIER_0005) 
    TABLESPACE "HPICA1";

CREATE INDEX PHI_X_REPORT_Z04_TYPE 
    ON PHI_T_REPORT_Z04(SERVICE_TYPE_0010) 
    TABLESPACE "HPICA1";

CREATE INDEX PHI_X_REPORT_Z04_COUNTRY 
    ON PHI_T_REPORT_Z04(CRITICAL_FUNCTION_COUNTRY_0030) 
    TABLESPACE "HPICA1";

-- 4. Testar
EXEC P_REPORT_Z04.MAIN_Z04();

-- 5. Validar dados
SELECT COUNT(*) FROM PHI_T_REPORT_Z04;
SELECT * FROM PHI_T_REPORT_Z04 WHERE ROWNUM <= 10;
```

---

## 📋 Checklist de Validação

- [ ] Procedure `P_REPORT_Z04.MAIN_Z04` funciona sem erros
- [ ] Tabela `PHI_T_REPORT_Z04` tem dados após execução
- [ ] Não há violações de UNIQUE constraint
- [ ] NULLs são tratados corretamente
- [ ] Query Java retorna dados esperados
- [ ] Arquivo Excel é gerado com sucesso
- [ ] Índices foram criados para performance

---

## 🔗 Referências

- [Oracle PL/SQL Best Practices](https://docs.oracle.com/database/sql-language)
- [Estrutura de ReportZ04 no Java](./REPORTZ04_IMPLEMENTATION.md)
- [Guia Rápido de Uso](./REPORTZ04_QUICK_START.md)


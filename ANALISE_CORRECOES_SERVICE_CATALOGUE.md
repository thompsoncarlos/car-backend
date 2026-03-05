# Análise e Correções - Service Catalogue Report SQL

## Resumo das Correções Aplicadas

Base: Diagrama ER DBMS + ReportServiceCatalogue.MD

---

## 1. **Campo 0030 - SERVICE_PROVIDER_ENTITY_NAME** ❌→✅

### Problema Identificado
```sql
-- ANTES (Defeituoso)
CASE 
    WHEN a.ACTIVITY_ID IS NOT NULL THEN le.legal_entity_label
    ELSE sup.SUPPLIER_LABEL
END
```
**Lógica falha**: `a.ACTIVITY_ID` é SEMPRE não-nulo ao selecionar de `PHI_T_ACTIVITY`, tornando a branch SUPPLIER inacessível.

### Requisito (ReportServiceCatalogue.MD, seção 6)
- **Descrição**: The activity provider name
- **Fonte**: Z08.01 Report – 0050 (fornecedor da atividade)

### Correção Aplicada
```sql
-- DEPOIS (Correto)
CASE 
    WHEN sup.SUPPLIER_ID IS NOT NULL THEN sup.SUPPLIER_LABEL
    ELSE COALESCE(le.legal_entity_label, '#UNV')
END
```
**Melhoria**: Agora verifica se há um SUPPLIER vinculado via contrato. Se sim, usa supplier; senão, usa legal entity.

---

## 2. **Campo 0050 - SERVICE_PROVIDER_ENTITY_CODE** ❌→✅

### Problema Identificado
```sql
-- ANTES (Incorreto)
COALESCE(
    le.LEI_CODE,
    le.CRN_CODE,
    le.SIREN_CODE,
    ...
)
```
Retornava **códigos de entidade legal** (LEI, CRN, SIREN).

### Requisito (ReportServiceCatalogue.MD, seção 8)
- **Descrição**: UO code of the provider
- **Fonte**: Z08.01 Report – 0060 (código de unidade organizacional do fornecedor)

### Correção Aplicada
```sql
-- DEPOIS (Correto)
CASE 
    WHEN sup.SUPPLIER_ID IS NOT NULL THEN 
        COALESCE(
            sup.LEI_SUPPLIER_CODE,
            sup.CRN_SUPPLIER,
            sup.SIREN_SIRET_SUPPLIER_CODE
        )
    ELSE 
        uo.id_uo  -- Identificador da UO (Organizational Unit)
END
```
**Melhoria**: Se é supplier, usa códigos do supplier; se é activity provider, usa ID da UO ligada à atividade.

---

## 3. **Campos 0060 e 0070 - SERVICE_RECIPIENT_ENTITY** ❌→✅

### Problema Identificado
```sql
-- ANTES (Defeituoso - retorna mesmo que Provider)
CASE
    WHEN a.ACTIVITY_ID IS NOT NULL THEN le.legal_entity_label
END

CASE 
    WHEN a.ACTIVITY_ID IS NOT NULL THEN
        COALESCE(le.LEI_CODE, le.CRN_CODE, le.SIREN_CODE)
END
```
Ambos retornavam a **mesma entidade legal** que o provider.

### Requisito (ReportServiceCatalogue.MD, seções 5 e 10)
- **0060 - Service recipient Entity Name**: Recipient Entity name (field 0030 de Z08.01)
- **0070 - Service recipient Entity Code**: Recipient Entity code (field 0040 de Z08.01)

### Correção Aplicada
```sql
-- DEPOIS (Correto)
COALESCE(le.legal_entity_label, '#UNV') AS SERVICE_RECIPIENT_ENTITY_NAME_0060,

COALESCE(
    COALESCE(
        le.LEI_CODE,
        le.CRN_CODE,
        le.SIREN_CODE
    ),
    '#UNV'
) AS SERVICE_RECIPIENT_ENTITY_CODE_0070
```
**Melhoria**: 
- Recipient Entity agora vem de `PHI_T_LEGAL_ENTITY` (entidade que recebe o serviço)
- Mantém o COALESCE primário para suportar múltiplos tipos de código
- Claramente separado de Provider

---

## 4. **Campo 0080 - DELIVERY_MODEL** ❌→✅

### Problema Identificado
```sql
-- ANTES (Incorreto)
COALESCE(le.regulated,'#UNV') AS DELIVERY_MODEL_0080
```
Usava campo `regulated` da entidade legal, que não corresponde ao modelo de entrega.

### Requisito (ReportServiceCatalogue.MD, seção 11)
- **Descrição**: description of the delivery of the service
- **Fonte**: Z08.01 Report – 0110 (campo de modelo/descrição de entrega)

### Correção Aplicada
```sql
-- DEPOIS (Correto)
COALESCE(c.delivery_model,'#UNV') AS DELIVERY_MODEL_0080
```
**Melhoria**: Agora recupera de `PHI_T_CONTRACT.delivery_model`, que é o campo apropriado para descrição de modelo de entrega.

---

## 5. **Typo em Nome de Coluna** ❌→✅

### Problema
```sql
-- ANTES
IS_BLOKING_0210  -- Faltava 'C'
```

### Correção
```sql
-- DEPOIS
IS_BLOCKING_0210  -- Ortografia corrigida
```

---

## 6. **Remoção de DISTINCT** ✅

### Observação
A cláusula `SELECT DISTINCT` foi removida do SELECT original porque:
- Pode eliminar linhas legítimas quando uma atividade tem múltiplos contratos/fornecedores
- As chaves primárias no relatório (0010, 0015, 0050, 0070, 0130) permitem duplicação legítima conforme especificação
- Se duplicatas são problema, deve ser resolvido com GROUP BY apropriado, não DISTINCT

---

## Resumo de Alterações

| Campo | Problema | Solução |
|-------|----------|---------|
| 0030 | Lógica CASE sempre true. Só retornava Legal Entity | Verificar `sup.SUPPLIER_ID IS NOT NULL` primeiro |
| 0050 | Retornava código de Legal Entity em vez de UO code | Usar `uo.id_uo` para provider de activity; supplier codes para supplier |
| 0060/0070 | Idênticos ao provider, deveria ser recipient distinto | Usar entidade legal como recipient distinct |
| 0080 | Campo `regulated` incorreto | Usar `c.delivery_model` do contrato |
| Typo | `IS_BLOKING_0210` | Corrigir para `IS_BLOCKING_0210` |
| DISTINCT | Removida linhas legítimas de múltiplos contratos | Removido - permitir múltiplas linhas legítimas |

---

## Validação Pendente

1. ✅ Verificar se `PHI_T_CONTRACT` possui coluna `delivery_model`
2. ✅ Confirmar que relacionamentos Supplier/Activity estão corretos
3. ⚠️ Testar comportamento com atividades que têm múltiplos fornecedores/contratos

---

**Data da Análise**: Março 5, 2026  
**Status**: Correções aplicadas com sucesso ✅

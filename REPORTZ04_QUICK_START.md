# ReportZ04 - Guia de Uso Rápido

## 🚀 Como Usar

### Pré-requisitos
- O procedimento `P_REPORT_Z04` deve estar criado no Oracle
- A tabela `PHI_T_REPORT_Z04` deve existir e estar vazia ou com dados anteriores
- A aplicação Spring Boot deve estar rodando

---

## 📋 Endpoints Disponíveis

### 1️⃣ Gerar Dados do Relatório (Executar Procedure)

```
POST /api/report/z04/generate
```

**Descrição:** Executa a procedure Oracle `P_REPORT_Z04.MAIN_Z04` que:
- Trunca a tabela `PHI_T_REPORT_Z04`
- Insere dados processados das atividades

**Request:**
```bash
curl -X POST http://localhost:8080/api/report/z04/generate \
  -H "Content-Type: application/json"
```

**Response (200 OK):**
```json
"Relatório Z04 gerado com sucesso"
```

**Response (500 Internal Server Error):**
```json
{
  "error": "Internal Server Error",
  "message": "Erro ao gerar dados do relatório Z04: [detalhes do erro]"
}
```

---

### 2️⃣ Baixar Relatório em Excel

```
GET /api/report/z04
```

**Descrição:** Retorna o relatório em arquivo Excel com os dados armazenados em `PHI_T_REPORT_Z04`

**Request:**
```bash
curl -X GET http://localhost:8080/api/report/z04 \
  -H "Accept: application/vnd.ms-excel" \
  -o ReportZ04_20260206_120530.xlsX
```

**Response (200 OK):**
- Arquivo Excel com extensão `.xlsX`
- Nome: `ReportZ04_yyyyMMdd_HHmmss.xlsX`
- Colunas:
  1. Service Identifier (0005)
  2. Service Type (0010)
  3. Unique Service Title (0020)
  4. Critical Function Country (0030)
  5. Critical Function Country Code (0040)

**Response (500 Internal Server Error):**
```json
{
  "error": "Internal Server Error"
}
```

---

## 📊 Fluxo Completo

```
┌─────────────────────────────────────┐
│ 1. Gerar Dados (POST /z04/generate) │
│    Executa procedure Oracle         │
└──────────────┬──────────────────────┘
               │
               ▼
┌─────────────────────────────────────┐
│ Dados processados e armazenados em  │
│ PHI_T_REPORT_Z04                    │
└──────────────┬──────────────────────┘
               │
               ▼
┌─────────────────────────────────────┐
│ 2. Baixar Arquivo (GET /z04)        │
│    Lê dados da tabela               │
│    Cria arquivo Excel               │
└──────────────┬──────────────────────┘
               │
               ▼
┌─────────────────────────────────────┐
│ ReportZ04_YYYYMMDD_HHMMSS.xlsX      │
│ Pronto para download                │
└─────────────────────────────────────┘
```

---

## 🔄 Casos de Uso

### Caso 1: Atualizar e Exportar
```bash
# Passo 1: Regenerar dados
curl -X POST http://localhost:8080/api/report/z04/generate

# Passo 2: Baixar
curl -X GET http://localhost:8080/api/report/z04 -o report.xlsX
```

### Caso 2: Apenas Exportar (sem regenerar)
```bash
# Dados já foram gerados anteriormente
curl -X GET http://localhost:8080/api/report/z04 -o report.xlsX
```

### Caso 3: Integração em Script (.sh ou .bat)
```bash
#!/bin/bash
BASE_URL="http://localhost:8080/api/report"

# Gerar relatório
echo "Gerando relatório Z04..."
curl -X POST ${BASE_URL}/z04/generate

# Esperar 2 segundos
sleep 2

# Baixar arquivo
echo "Baixando arquivo..."
curl -X GET ${BASE_URL}/z04 -o "ReportZ04_$(date +%Y%m%d_%H%M%S).xlsX"

echo "Pronto!"
```

---

## 🗂️ Estrutura dos Dados da Tabela

```sql
SELECT 
  SERVICE_IDENTIFIER_0005,           -- Identificador único do serviço
  SERVICE_TYPE_0010,                 -- Tipo de serviço
  UNIQUE_SERVICE_TITLE_BK_TAXO_0020, -- Título único do serviço
  CRITICAL_FUNCTION_COUNTRY_0030,    -- País da função crítica
  CRITICAL_FUNCTION_COUNTRY_CODE_0040 -- Código do país
FROM PHI_T_REPORT_Z04;
```

---

## 📝 Exemplo de Resposta Excel

| Service Identifier | Service Type | Unique Service Title | Critical Function Country | Country Code |
|---|---|---|---|---|
| ACT001 | Activity | ACT001Activity | Portugal | PT |
| ACT002 | Activity | ACT002Activity | Spain | ES |
| ACT003 | Activity | ACT003Activity | France | FR |

---

## ⚠️ Possíveis Erros

### ❌ "Erro ao gerar dados do relatório Z04: Procedure P_REPORT_Z04.MAIN_Z04 não encontrada"

**Solução:**
```sql
-- Verificar se a procedure existe
SELECT * FROM ALL_PROCEDURES 
WHERE OBJECT_NAME = 'P_REPORT_Z04' AND PROCEDURE_NAME = 'MAIN_Z04';

-- Recriar o package se necessário
@CREATE_PACKAGE_REPORT_Z04.sql
```

---

### ❌ "Erro ao gerar dados do relatório Z04: Tabela PHI_T_REPORT_Z04 não existe"

**Solução:**
```sql
-- Criar a tabela
@CREATE_T_REPORT_Z04.sql
```

---

### ❌ "Template File report_Z04.xlsx Not Found"

**Solução (não crítica - sistema criará automaticamente):**
- Arquivo será criado automaticamente sem template customizado
- Para melhor formatação, crie e coloque em:
  - `src/main/resources/templates/report_Z04.xlsx`

---

## 🔍 Logging e Debug

### Ativar Debug Logging

**application.properties ou application.yml:**
```properties
logging.level.car_backend.service.reports.ReportZ04ServiceImpl=DEBUG
logging.level.car_backend.utils.reports.ReportsExcelBuilder=DEBUG
```

### Exemplo de Logs Esperados
```
[INFO]  Iniciando geração do ReportZ04
[DEBUG] Escrevendo 150 registros no relatório Z04
[DEBUG] Criando novo workbook para ReportZ04
[INFO]  Procedure P_REPORT_Z04.MAIN_Z04 executada com sucesso
```

---

## 🧪 Testando com Postman

### 1. Criar Environment
```json
{
  "base_url": "http://localhost:8080",
  "report_endpoint": "/api/report/z04"
}
```

### 2. Requisição Generate
```
POST {{base_url}}{{report_endpoint}}/generate
Content-Type: application/json
```

### 3. Requisição Download
```
GET {{base_url}}{{report_endpoint}}
Accept: application/vnd.ms-excel
```

---

## 📌 Notas Importantes

✅ **Transações:** As operações são gerenciadas por transações Spring
✅ **Null Safety:** Valores nulos são substituídos por strings vazias no Excel
✅ **Timestamps:** Nome do arquivo inclui data/hora da geração
✅ **Performance:** Read-only queries são otimizadas
✅ **Logging:** Todas as operações são registradas em logs

---

## 🔗 Referências

- [ReportZ04 - Documentação Completa](./REPORTZ04_IMPLEMENTATION.md)
- Arquivo de Procedure: `src/main/resources/sql/reports/report-z04/CREATE_PACKAGE_REPORT_Z04.sql`
- Arquivo de Tabela: `src/main/resources/sql/reports/report-z04/CREATE_T_REPORT_Z04.sql`


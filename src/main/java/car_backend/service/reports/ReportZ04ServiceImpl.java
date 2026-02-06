package car_backend.service.reports;

import car_backend.model.excel.ReportZ04;
import car_backend.repository.reports.ReportZ04Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ReportZ04ServiceImpl implements ReportZ04Service {

    @Autowired
    private ReportZ04Repository repository;

    @Override
    @Transactional(readOnly = true)
    public List<ReportZ04> getReport() {
        log.info("Iniciando geração do ReportZ04");
        try {
            return repository.generateReportData().stream().map(row -> {
                ReportZ04 data = new ReportZ04();
                data.setServiceIdentifier(Objects.isNull(row[0]) ? null : row[0].toString());
                data.setServiceType(Objects.isNull(row[1]) ? null : row[1].toString());
                data.setUniqueServiceTitle(Objects.isNull(row[2]) ? null : row[2].toString());
                data.setCriticalFunctionCountry(Objects.isNull(row[3]) ? null : row[3].toString());
                data.setCriticalFunctionCountryCode(Objects.isNull(row[4]) ? null : row[4].toString());
                return data;
            }).toList();
        } catch (Exception e) {
            log.error("Erro ao gerar ReportZ04", e);
            throw new RuntimeException("Erro ao gerar relatório Z04: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void generateReportData() {
        log.info("Executando procedure P_REPORT_Z04.MAIN_Z04 para processar dados");
        try {
            repository.executeReportProcedure();
            log.info("Procedure P_REPORT_Z04.MAIN_Z04 executada com sucesso");
        } catch (Exception e) {
            log.error("Erro ao executar procedure P_REPORT_Z04.MAIN_Z04", e);
            throw new RuntimeException("Erro ao processar dados do relatório Z04: " + e.getMessage(), e);
        }
    }
}

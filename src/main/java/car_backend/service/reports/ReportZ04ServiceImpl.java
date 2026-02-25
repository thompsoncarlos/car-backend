package car_backend.service.reports;

import car_backend.car.model.reports.ReportZ04;
import car_backend.car.repository.reports.ReportZ04Repository;
import car_backend.car.utils.reports.ReportsExcelBuilder;
import jakarta.persistence.Column;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReportZ04ServiceImpl implements ReportZ04Service {

    @Autowired
    private ReportZ04Repository reportZ04Repository;

    @Autowired
    ReportsExcelBuilder excelBuilder;

    @Override
    public List<ReportZ04> getReport() {

        reportZ04Repository.truncateReportTable();

        reportZ04Repository.insertReportTable();

        return reportZ04Repository.findAll();
    }

    @Override
    public byte[] generateZ04ReportExcel() {
        try {
            List<ReportZ04> reports = getReport();
            return excelBuilder.buildGenericReport(reports, "templates/report_z04.xlsx", 6, this::writeReportZ04);
        } catch (IOException e) {
            log.error("Error generating report_Z04 excel", e);
            throw new RuntimeException("Failed to generate report_Z04", e);
        }
    }

    private void writeReportZ04(ReportZ04 reportZ04, Row row) {
        int cellCount = 1;

        excelBuilder.fillCellWithString(reportZ04.getServiceId(), row, cellCount++); // SERVICE_IDENTIFIER_0005
        excelBuilder.fillCellWithString(reportZ04.getServiceType(), row, cellCount++); // SERVICE_TYPE_0010
        excelBuilder.fillCellWithString(reportZ04.getServiceUniqueLabel(), row, cellCount++); // UNIQUE_SERVICE_TITLE_BK_TAXO_0020
        excelBuilder.fillCellWithString(reportZ04.getCriticalFunctionId(), row, cellCount++); // CRITICAL_FUNCTION_COUNTRY_0030
        excelBuilder.fillCellWithString(reportZ04.getCriticalFunctionCountry(), row, cellCount++); // CRITICAL_FUNCTION_COUNTRY_CODE_0040
    }
}

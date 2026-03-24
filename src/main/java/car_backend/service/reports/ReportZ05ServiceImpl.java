
package car_backend.service.reports;

import car_backend.model.reports.ReportZ05;
import car_backend.repository.reports.ReportZ05Repository;
import car_backend.utils.reports.ReportsExcelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class ReportZ05ServiceImpl implements ReportZ05Service {

    @Autowired
    private ReportZ05Repository reportZ05Repository;

    @Autowired
    ReportsExcelBuilder excelBuilder;
    
    @Override
    public byte[] generateZ05ReportExcel() {
        try {
            List<ReportZ05> reports = getReport();
            return excelBuilder.buildGenericReport(reports, "templates/report_z05.xlsx", 6, this::writeReportZ05);
        } catch (IOException e) {
            log.error("Error generating report_Z05 excel", e);
            throw new RuntimeException("Failed to generate report_Z05", e);
        }
    }

    private List<ReportZ05> getReport() {
        reportZ05Repository.executeReportZ05();
        return reportZ05Repository.findAll();
    }


    private void writeReportZ05(ReportZ05 reportZ05, Row row) {
        int cellCount = 1;

        excelBuilder.fillCellWithString(reportZ05.getServiceId(), row, cellCount++); // SERVICE_IDENTIFIER_0005
        excelBuilder.fillCellWithString(reportZ05.getServiceType(), row, cellCount++); // SERVICE_TYPE_0010
        excelBuilder.fillCellWithString(reportZ05.getServiceUniqueLabel(), row, cellCount++); // UNIQUE_SERVICE_TITLE_BK_TAXO_0020
        excelBuilder.fillCellWithString(reportZ05.getCoreBusinessLineName(), row, cellCount++); // CORE_BUSINESS_LINE_NAME_0030
        excelBuilder.fillCellWithString(reportZ05.getCoreBusinessLineId(), row, cellCount++); // CORE_BUSINESS_LINE_ID_0040
    }
}

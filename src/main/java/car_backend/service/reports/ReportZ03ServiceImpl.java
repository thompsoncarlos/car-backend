package car_backend.service.reports;

import car_backend.model.reports.ReportZ03;
import car_backend.repository.reports.ReportZ03Repository;
import car_backend.utils.reports.ReportsExcelBuilder;
import lombok.extern.slf4j.Slf4j;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class ReportZ03ServiceImpl implements ReportZ03Service {

    @Autowired
    private ReportZ03Repository z03Repository;

    @Autowired
    ReportsExcelBuilder excelBuilder;

    @Override
    public byte[] generateZ03ReportExcel() {
        try {
            List<ReportZ03> reports = getReport();
            return excelBuilder.buildGenericReport(reports, "templates/report_Z03.xlsx", 16, this::writeZ03Report);
        } catch (IOException e) {
            log.error("Error generating Z03 report excel", e);
            throw new RuntimeException("Failed to generate Z03 report", e);
        }
    }

    private List<ReportZ03> getReport() {
        z03Repository.executeReportZ03();
        return z03Repository.findAll();
    }

      private void writeZ03Report(ReportZ03 report, Row row) {
        int cellCount = 1;
        excelBuilder.fillCellWithString(report.getServiceIdentifier0005(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getServiceType0010(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getUniqueServiceTitleBkTaxo0020(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getRoleIdentifier0030(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getRoleName0040(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getDepartment0050(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getCriticality0060(), row, cellCount++);

      }
}

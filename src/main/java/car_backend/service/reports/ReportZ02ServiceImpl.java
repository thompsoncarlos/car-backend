package car_backend.service.reports;

import car_backend.model.reports.ReportZ02;
import car_backend.repository.reports.ReportZ02Repository;
import car_backend.utils.reports.ReportsExcelBuilder;
import lombok.extern.slf4j.Slf4j;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class ReportZ02ServiceImpl implements ReportZ02Service {

    @Autowired
    ReportZ02Repository z02Repository;

    @Autowired
    ReportsExcelBuilder excelBuilder;

    @Override
    public byte[] generateZ02ReportExcel() {
                try {
            List<ReportZ02> reports = getReport();
            return excelBuilder.buildGenericReport(reports, "templates/report_Z02.xlsx", 16, this::writeZ02Report);
        } catch (IOException e) {
            log.error("Error generating Z02 report excel", e);
            throw new RuntimeException("Failed to generate Z02 report", e);
        }
    }

    private List<ReportZ02> getReport() {
        z02Repository.executeReportZ02();
        return z02Repository.findAll();
    }

      private void writeZ02Report(ReportZ02 report, Row row) {
        int cellCount = 1;
        excelBuilder.fillCellWithString(report.getServiceIdentifier0005(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getServiceType0010(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getUniqueServiceTitleBkTaxo0020(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getAssetIdentifier0030(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getAssetType0040(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getAssetName0050(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getCriticality0060(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getContractType0070(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getContractId0080(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getGoverningLaw0090(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getResolutionResilienceFeatures0100(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getResolutionResilienceBrp0110(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getResolutionResilienceAltMit0120(), row, cellCount++);
      }
}

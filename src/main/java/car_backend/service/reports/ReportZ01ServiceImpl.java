package car_backend.service.reports;

import car_backend.model.reports.ReportZ01;
import car_backend.repository.reports.ReportZ01Repository;
import car_backend.utils.reports.ReportsExcelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class ReportZ01ServiceImpl implements ReportZ01Service {

    @Autowired
    ReportZ01Repository repository;

    @Autowired
    ReportsExcelBuilder excelBuilder;

    @Override
    // generate the excel report following the template
    public byte[] generateZ01ReportExcel() {
        try {
            List<ReportZ01> reports = getReport();
            return excelBuilder.buildGenericReport(reports, "templates/report_Z01.xlsx", 16, this::writeZ01Report);
        } catch (IOException e) {
            log.error("Error generating Z01 report excel", e);
            throw new RuntimeException("Failed to generate Z01 report", e);
        }
    }

    private List<ReportZ01> getReport() {
        repository.executeReportZ01();
        return repository.findAll();
    }

    // map the excel report fields with its model
    private void writeZ01Report(ReportZ01 report, Row row) {
        int cellCount = 1;
        excelBuilder.fillCellWithString(report.getServiceIdentifier0005(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getServiceType0010(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getUniqueServiceTitleBkTaxo0020(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getServiceRecipientName0030(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getServiceRecipientCode0040(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getServiceProviderEntityName0050(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getServiceProviderEntityCode0060(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getServiceProviderEntityCodeType0070(), row, cellCount);
        excelBuilder.fillCellWithString(report.getServiceProviderParentName0080(), row, cellCount);
        excelBuilder.fillCellWithString(report.getServiceProviderParentCode0090(), row, cellCount);
        excelBuilder.fillCellWithString(report.getServiceProviderParentCodeType0100(), row, cellCount);
        excelBuilder.fillCellWithString(report.getServiceProviderDelivery0110(), row, cellCount);
        excelBuilder.fillCellWithString(report.getCriticality0120(), row, cellCount);
        excelBuilder.fillCellWithString(report.getContractId0130(), row, cellCount);
        excelBuilder.fillCellWithString(report.getGoverningLaw0140(), row, cellCount);
        excelBuilder.fillCellWithString(report.getResolutionResilienceFeatures0150(), row, cellCount);
        excelBuilder.fillCellWithString(report.getResolutionResilienceBrp0160(), row, cellCount);
        excelBuilder.fillCellWithString(report.getResolutionResilienceAltMit0170(), row, cellCount);
        excelBuilder.fillCellWithString(report.getCriticalIctThdPartyServProvUndDora0180(), row, cellCount);
        excelBuilder.fillCellWithString(report.getIctServiceUnderDora0190(), row, cellCount);
    }

}

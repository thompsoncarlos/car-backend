package car_backend.service.reports;

import car_backend.model.enums.ReportFilesInformation;
import car_backend.model.reports.ReportZ01;
import car_backend.repository.reports.ReportZ01Repository;
import car_backend.utils.reports.ReportsExcelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ReportZ01ServiceImpl implements ReportZ01Service {

    @Autowired
    ReportZ01Repository repository;

    @Autowired
    ReportsExcelBuilder excelBuilder;

    @Override
    // get the report content
    public List<ReportZ01> getReport() {
        return repository.generateReportData().stream().map(row -> {
            ReportZ01 data = new ReportZ01();
            data.setServiceId(Objects.isNull(row[0]) ? null : row[0].toString());
            data.setServiceType(Objects.isNull(row[1]) ? null : row[1].toString());
            data.setServiceUniqueLabel(Objects.isNull(row[2]) ? null : row[2].toString());
            data.setServiceRecipientName(Objects.isNull(row[3]) ? null : row[3].toString());
            data.setServiceRecipientCode(Objects.isNull(row[4]) ? null : row[4].toString());
            data.setServiceProviderEntityName(Objects.isNull(row[5]) ? null : row[5].toString());
            data.setServiceProviderEntityCode(Objects.isNull(row[6]) ? null : row[6].toString());
            return data;
        }).toList();
    }

    @Override
    // generate the excel report following the template
    public byte[] generateZ01ReportExcel() {
        try {
            List<ReportZ01> reports = getReport();
            return excelBuilder.buildGenericReport(reports, "templates/report_Z01.xlsx", 5, this::writeZ01Report);
        } catch (IOException e) {
            log.error("Error generating Z01 report excel", e);
            throw new RuntimeException("Failed to generate Z01 report", e);
        }
    }

    // map the excel report fields with its model
    private void writeZ01Report(ReportZ01 report, Row row) {
        int cellCount = 1;
        excelBuilder.fillCellWithString(report.getServiceId(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getServiceType(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getServiceUniqueLabel(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getServiceRecipientName(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getServiceRecipientCode(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getServiceProviderEntityName(), row, cellCount++);
        excelBuilder.fillCellWithString(report.getServiceProviderEntityCode(), row, cellCount);
    }

}

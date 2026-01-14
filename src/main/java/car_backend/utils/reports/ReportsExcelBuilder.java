package car_backend.utils.reports;

import car_backend.model.excel.ReportZ01;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ReportsExcelBuilder {

    private CellStyle dateStyle;
    private CellStyle headerStyle;

    public byte[] buildReport(List<ReportZ01> servicesReportList) throws IOException {

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("templates/report_Z01.xlsx")) {
            if (inputStream == null) {
                log.error("Template File Not Found");
                throw new IOException(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            }
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            writeRelevantServices(workbook.getSheetAt(0), servicesReportList);
            return workbookToBytes(workbook);
        }
    }

    private void writeRelevantServices(XSSFSheet serviceSheet, List<ReportZ01> services) {

        int rowCount = 5;
        for (ReportZ01 relevantService : services) {
            Row row = serviceSheet.createRow(rowCount++);
            writeRelevantService(relevantService, row);
        }

    }

    private void writeRelevantService(ReportZ01 report, Row row) {
        int cellCount = 1;
        fillCellWithString(report.getServiceId(), row, cellCount++);
        fillCellWithString(report.getServiceType(), row, cellCount++);
        fillCellWithString(report.getServiceUniqueLabel(), row, cellCount++);
        fillCellWithString(report.getServiceRecipientName(), row, cellCount++);
        fillCellWithString(report.getServiceRecipientCode(), row, cellCount++);
        fillCellWithString(report.getServiceProviderEntityName(), row, cellCount++);
        fillCellWithString(report.getServiceProviderEntityCode(), row, cellCount);
    }

    private void fillCellWithString(String value, Row row, int cellCount) {
        Cell cell = row.createCell(cellCount);
        String cellValue = value == null ? "" : value;
        cell.setCellValue(cellValue);
    }

    private void fillCellWithInteger(Integer value, Row row, int cellCount) {
        Cell cell = row.createCell(cellCount);
        if (value != null) {
            cell.setCellValue(value);
        }
    }

    private void fillCellWithDouble(Double value, Row row, int cellCount) {
        Cell cell = row.createCell(cellCount);
        if (value != null) {
            cell.setCellValue(value);
        }
    }

    private void fillCellWithDate(LocalDateTime date, Row row, int cellCount) {
        Cell cell = row.createCell(cellCount);
        if (date != null) {
            cell.setCellValue(date.toString());
        }
        cell.setCellStyle(dateStyle);
    }

    private byte[] workbookToBytes(Workbook workbook) throws IOException {
        byte[] bytes = null;
        ByteArrayOutputStream bos = null;
        bos = new ByteArrayOutputStream();
        workbook.write(bos);
        bytes = bos.toByteArray();

        return bytes;
    }

}

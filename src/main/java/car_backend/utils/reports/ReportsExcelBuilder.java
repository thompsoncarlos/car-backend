package car_backend.utils.reports;

import car_backend.model.excel.ReportZ01;
import car_backend.model.excel.ReportZ04;
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

    public byte[] buildReportZ04(List<ReportZ04> reportZ04List) throws IOException {
        log.debug("Iniciando construção do arquivo Excel para ReportZ04");
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("templates/report_Z04.xlsx")) {
            if (inputStream == null) {
                log.warn("Template File report_Z04.xlsx Not Found - criando workbook vazio");
                return createZ04Workbook(reportZ04List);
            }
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            writeZ04Report(workbook.getSheetAt(0), reportZ04List);
            return workbookToBytes(workbook);
        }
    }

    private byte[] createZ04Workbook(List<ReportZ04> reportZ04List) throws IOException {
        log.debug("Criando novo workbook para ReportZ04");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("ReportZ04");

        // Criar header
        Row headerRow = sheet.createRow(0);
        String[] headers = {
                "Service Identifier (0005)",
                "Service Type (0010)",
                "Unique Service Title (0020)",
                "Critical Function Country (0030)",
                "Critical Function Country Code (0040)"
        };

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        writeZ04Report(sheet, reportZ04List);
        return workbookToBytes(workbook);
    }

    private void writeRelevantServices(XSSFSheet serviceSheet, List<ReportZ01> services) {

        int rowCount = 5;
        for (ReportZ01 relevantService : services) {
            Row row = serviceSheet.createRow(rowCount++);
            writeRelevantService(relevantService, row);
        }

    }

    private void writeZ04Report(XSSFSheet reportSheet, List<ReportZ04> reportData) {
        log.debug("Escrevendo {} registros no relatório Z04", reportData.size());
        int rowCount = 1;
        for (ReportZ04 reportZ04 : reportData) {
            Row row = reportSheet.createRow(rowCount++);
            writeZ04Row(reportZ04, row);
        }
    }

    private void writeZ04Row(ReportZ04 report, Row row) {
        int cellCount = 0;
        fillCellWithString(report.getServiceIdentifier(), row, cellCount++);
        fillCellWithString(report.getServiceType(), row, cellCount++);
        fillCellWithString(report.getUniqueServiceTitle(), row, cellCount++);
        fillCellWithString(report.getCriticalFunctionCountry(), row, cellCount++);
        fillCellWithString(report.getCriticalFunctionCountryCode(), row, cellCount);
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

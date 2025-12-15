package car_backend.utils.reports;

import car_backend.model.excel.RelevantServicesReport;
import car_backend.model.enums.HeaderRelevantServices;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportsExcelBuilder {

    private static final String SERV_1 = "Relevant Services";

    private CellStyle dateStyle;
    private CellStyle headerStyle;

    public byte[] buildReport(List<RelevantServicesReport> servicesReportList) throws IOException {

        SXSSFWorkbook workbook = new SXSSFWorkbook();
        initStyle(workbook);
        writeRelevantServices(workbook, servicesReportList);

        return workbookToBytes(workbook);
    }

    private void initStyle(Workbook workbook) {
        dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat((short) 15);

        headerStyle = workbook.createCellStyle();
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setFillForegroundColor(IndexedColors.LAVENDER.index);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    }

    private void writeHeaders(Sheet sheet, String[] headers) {
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }
    }

    private void writeRelevantServices(Workbook workbook, List<RelevantServicesReport> services) {

        SXSSFSheet serviceSheet = (SXSSFSheet) workbook.createSheet(SERV_1);
        serviceSheet.trackAllColumnsForAutoSizing();

        String[] headers = HeaderRelevantServices.getLabels();
        writeHeaders(serviceSheet, headers);

        int rowCount = 1;
        for (RelevantServicesReport relevantService : services) {
            Row row = serviceSheet.createRow(rowCount++);
            writeRelevantService(relevantService, row);
        }

        for (int i = 0; i < headers.length; i++) {
            serviceSheet.autoSizeColumn(i);
        }
    }

    private void writeRelevantService(RelevantServicesReport report, Row row) {
        int cellCount = 0;
        fillCellWithString(report.getServiceId(), row, cellCount++);
        fillCellWithString(null, row, cellCount++);
        fillCellWithString(report.getServiceUniqueLabel(), row, cellCount);
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

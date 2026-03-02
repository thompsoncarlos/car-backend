package car_backend.utils.reports;

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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ReportsExcelBuilder {

    private static final String NULL_VALUE_PLACEHOLDER = "#UNV";

    private CellStyle dateStyle;
    private CellStyle headerStyle;

    public <T> byte[] buildGenericReport(List<T> reportList, String templatePath, int startingRow, ReportWriter<T> reportWriter) throws IOException {
        try (InputStream inputStream = getTemplateStream(templatePath)) {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            initializeStyles(workbook);
            writeGenericReports(workbook.getSheetAt(0), reportList, startingRow, reportWriter);
            return workbookToBytes(workbook);
        }
    }

    private InputStream getTemplateStream(String templatePath) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(templatePath);
        if (inputStream == null) {
            log.error("Template file not found: {}", templatePath);
            throw new IOException(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        }
        return inputStream;
    }

    private void initializeStyles(XSSFWorkbook workbook) {
        dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("yyyy-MM-dd"));
        headerStyle = workbook.createCellStyle();
    }

    private <T> void writeGenericReports(XSSFSheet sheet, List<T> reports, int startingRow, ReportWriter<T> reportWriter) {
        int rowCount = startingRow;
        for (T report : reports) {
            Row row = sheet.createRow(rowCount++);
            reportWriter.writeReport(report, row);
        }
    }

    public void fillCellWithString(String value, Row row, int cellCount) {
        Cell cell = row.createCell(cellCount);
        String cellValue = (value == null || value.trim().isEmpty()) ? NULL_VALUE_PLACEHOLDER : value;
        cell.setCellValue(cellValue);
    }

    public void fillCellWithInteger(Integer value, Row row, int cellCount) {
        Cell cell = row.createCell(cellCount);
        if (value != null) {
            cell.setCellValue(value);
        } else {
            cell.setCellValue(NULL_VALUE_PLACEHOLDER);
        }
    }

    public void fillCellWithDouble(Double value, Row row, int cellCount) {
        Cell cell = row.createCell(cellCount);
        if (value != null) {
            cell.setCellValue(value);
        } else {
            cell.setCellValue(NULL_VALUE_PLACEHOLDER);
        }
    }

    public void fillCellWithDate(LocalDateTime date, Row row, int cellCount) {
        Cell cell = row.createCell(cellCount);
        if (date != null) {
            cell.setCellValue(date.toString());
            if (dateStyle != null) {
                cell.setCellStyle(dateStyle);
            }
        } else {
            cell.setCellValue(NULL_VALUE_PLACEHOLDER);
        }
    }

    public void fillCellWithLocalDate(LocalDate date, Row row, int cellCount) {
        Cell cell = row.createCell(cellCount);
        if (date != null) {
            cell.setCellValue(date.toString());
            if (dateStyle != null) {
                cell.setCellStyle(dateStyle);
            }
        } else {
            cell.setCellValue(NULL_VALUE_PLACEHOLDER);
        }
    }

    private byte[] workbookToBytes(Workbook workbook) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        return bos.toByteArray();
    }
}

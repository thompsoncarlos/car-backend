package car_backend.controller;

import car_backend.model.enums.ReportFilesInformation;
import car_backend.service.reports.ReportZ01Service;
import car_backend.utils.reports.ReportsExcelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value = "/api/report")
public class ReportsController {

    @Autowired
    ReportZ01Service reportZ01Service;

    private final ReportsExcelBuilder excelBuilder;

    public ReportsController(ReportsExcelBuilder excelBuilder) {
        this.excelBuilder = excelBuilder;
    }

    @GetMapping("/z01")
    public ResponseEntity<byte[]> downloadActivitiesReport() {
        try {
            byte[] bytes = excelBuilder.buildReport(reportZ01Service.getReport());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ReportFilesInformation.Z01.getDateMask());
            String datetime = LocalDateTime.now().format(formatter);
            String filename = ReportFilesInformation.Z01.getNamePrefix() + datetime
                    + ReportFilesInformation.Z01.getExtension();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            ReportFilesInformation.Z01.getAttachment() + filename + "\"")
                    .contentType(MediaType.parseMediaType(ReportFilesInformation.Z01.getExcelMediaType()))
                    .body(bytes);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

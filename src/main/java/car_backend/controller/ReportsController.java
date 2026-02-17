package car_backend.controller;

import car_backend.model.enums.ReportFilesInformation;
import car_backend.model.reports.ContractReport;
import car_backend.service.reports.ReportContractService;
import car_backend.service.reports.ReportZ01Service;
import car_backend.utils.reports.ReportsExcelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/report")
public class ReportsController {

    @Autowired
    ReportZ01Service reportZ01Service;

    @Autowired
    ReportContractService reportContractService;

    private final ReportsExcelBuilder excelBuilder;

    public ReportsController(ReportsExcelBuilder excelBuilder) {
        this.excelBuilder = excelBuilder;
    }

    @GetMapping("/z01")
    public ResponseEntity<byte[]> downloadZ01Report() {
        try {
            byte[] bytes = reportZ01Service.generateZ01ReportExcel();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ReportFilesInformation.Z01.getDateMask());
            String datetime = LocalDateTime.now().format(formatter);
            String filename = ReportFilesInformation.Z01.getNamePrefix() + datetime + ReportFilesInformation.Z01.getExtension();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, ReportFilesInformation.Z01.getAttachment() + filename + "\"")
                    .contentType(MediaType.parseMediaType(ReportFilesInformation.Z01.getExcelMediaType()))
                    .body(bytes);
        } catch (Exception e) {
            log.error("Error generating contract report", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error generating report", e);
        }
    }

    @GetMapping("/contract-repository")
    public ResponseEntity<byte[]> downloadContractReport() {
        try {
            byte[] bytes = reportContractService.generateContractReportExcel();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ReportFilesInformation.CONTRACT_REPOSITORY.getDateMask());
            String datetime = LocalDateTime.now().format(formatter);
            String filename = ReportFilesInformation.CONTRACT_REPOSITORY.getNamePrefix() + datetime + ReportFilesInformation.CONTRACT_REPOSITORY.getExtension();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, ReportFilesInformation.CONTRACT_REPOSITORY.getAttachment() + filename + "\"")
                    .contentType(MediaType.parseMediaType(ReportFilesInformation.CONTRACT_REPOSITORY.getExcelMediaType()))
                    .body(bytes);
        } catch (Exception e) {
            log.error("Error generating contract report", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error generating report", e);
        }
    }
}

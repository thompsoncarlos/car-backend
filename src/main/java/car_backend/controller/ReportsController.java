package car_backend.controller;

import car_backend.model.enums.ReportFilesInformation;
import car_backend.model.reports.ContractReport;
import car_backend.service.reports.ReportContractService;
import car_backend.service.reports.ReportServiceCatalogueService;
import car_backend.service.reports.ReportZ01Service;
import car_backend.service.reports.ReportZ04Service;
import car_backend.service.reports.ReportZ05Service;
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
    ReportZ04Service reportZ04Service;

    @Autowired
    ReportZ05Service reportZ05Service;

    @Autowired
    ReportServiceCatalogueService reportServiceCatalogueService;

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
            String filename = ReportFilesInformation.Z01.getNamePrefix() + datetime
                    + ReportFilesInformation.Z01.getExtension();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            ReportFilesInformation.Z01.getAttachment() + filename + "\"")
                    .contentType(MediaType.parseMediaType(ReportFilesInformation.Z01.getExcelMediaType()))
                    .body(bytes);
        } catch (Exception e) {
            log.error("Error generating Z01 report", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error generating report", e);
        }
    }

    @GetMapping("/contract-repository")
    public ResponseEntity<byte[]> downloadContractReport() {
        try {
            byte[] bytes = reportContractService.generateContractReportExcel();

            DateTimeFormatter formatter = DateTimeFormatter
                    .ofPattern(ReportFilesInformation.CONTRACT_REPOSITORY.getDateMask());
            String datetime = LocalDateTime.now().format(formatter);
            String filename = ReportFilesInformation.CONTRACT_REPOSITORY.getNamePrefix() + datetime
                    + ReportFilesInformation.CONTRACT_REPOSITORY.getExtension();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            ReportFilesInformation.CONTRACT_REPOSITORY.getAttachment() + filename + "\"")
                    .contentType(
                            MediaType.parseMediaType(ReportFilesInformation.CONTRACT_REPOSITORY.getExcelMediaType()))
                    .body(bytes);
        } catch (Exception e) {
            log.error("Error generating contract report", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error generating report", e);
        }
    }

    @GetMapping("/z04")
    public ResponseEntity<byte[]> downloadZ04Report() {
        try {
            byte[] bytes = reportZ04Service.generateZ04ReportExcel();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ReportFilesInformation.Z04.getDateMask());
            String datetime = LocalDateTime.now().format(formatter);
            String filename = ReportFilesInformation.Z04.getNamePrefix() + datetime
                    + ReportFilesInformation.Z04.getExtension();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            ReportFilesInformation.Z04.getAttachment() + filename + "\"")
                    .contentType(MediaType.parseMediaType(ReportFilesInformation.Z04.getExcelMediaType()))
                    .body(bytes);
        } catch (Exception e) {
            log.error("Error generating Z04 report", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error generating report", e);
        }
    }

    @GetMapping("/z05")
    public ResponseEntity<byte[]> downloadZ05Report() {
        try {
            byte[] bytes = reportZ05Service.generateZ05ReportExcel();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ReportFilesInformation.Z05.getDateMask());
            String datetime = LocalDateTime.now().format(formatter);
            String filename = ReportFilesInformation.Z05.getNamePrefix() + datetime
                    + ReportFilesInformation.Z05.getExtension();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            ReportFilesInformation.Z05.getAttachment() + filename + "\"")
                    .contentType(MediaType.parseMediaType(ReportFilesInformation.Z05.getExcelMediaType()))
                    .body(bytes);
        } catch (Exception e) {
            log.error("Error generating Z05 report", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error generating report", e);
        }
    }

    @GetMapping("/service-catalogue")
    public ResponseEntity<byte[]> downloadServiceCatalogueReport() {
        try {
            log.info("Starting Service Catalogue report generation");
            byte[] bytes = reportServiceCatalogueService.generateServiceCatalogueReportExcel();
            log.info("Service Catalogue report generated successfully with {} bytes", bytes.length);

            DateTimeFormatter formatter = DateTimeFormatter
                    .ofPattern(ReportFilesInformation.SERVICE_CATALOGUE.getDateMask());
            String datetime = LocalDateTime.now().format(formatter);
            String filename = ReportFilesInformation.SERVICE_CATALOGUE.getNamePrefix() + datetime
                    + ReportFilesInformation.SERVICE_CATALOGUE.getExtension();

            log.info("Returning Service Catalogue report: {}", filename);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            ReportFilesInformation.SERVICE_CATALOGUE.getAttachment() + filename + "\"")
                    .contentType(MediaType.parseMediaType(ReportFilesInformation.SERVICE_CATALOGUE.getExcelMediaType()))
                    .body(bytes);
        } catch (Exception e) {
            log.error("Error generating Service Catalogue report", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error generating report", e);
        }
    }
}
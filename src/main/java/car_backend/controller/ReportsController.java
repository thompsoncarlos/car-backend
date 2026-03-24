package car_backend.controller;

import car_backend.model.enums.ReportFilesInformation;
import car_backend.model.reports.ContractReport;
import car_backend.service.reports.ReportContractService;
import car_backend.service.reports.ReportServiceCatalogueService;
import car_backend.service.reports.ReportZ01Service;
import car_backend.service.reports.ReportZ02Service;
import car_backend.service.reports.ReportZ03Service;
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
    ReportZ02Service reportZ02Service;

    @Autowired
    ReportZ03Service reportZ03Service;

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

    @GetMapping("/z02")
    public ResponseEntity<byte[]> downloadZ02Report() {
        try {
            byte[] bytes = reportZ02Service.generateZ02ReportExcel();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ReportFilesInformation.Z02.getDateMask());
            String datetime = LocalDateTime.now().format(formatter);
            String filename = ReportFilesInformation.Z02.getNamePrefix() + datetime
                    + ReportFilesInformation.Z02.getExtension();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            ReportFilesInformation.Z02.getAttachment() + filename + "\"")
                    .contentType(MediaType.parseMediaType(ReportFilesInformation.Z02.getExcelMediaType()))
                    .body(bytes);
        } catch (Exception e) {
            log.error("Error generating Z02 report", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error generating report", e);
        }
    }

        @GetMapping("/z03")
    public ResponseEntity<byte[]> downloadZ03Report() {
        try {
            byte[] bytes = reportZ03Service.generateZ03ReportExcel();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ReportFilesInformation.Z03.getDateMask());
            String datetime = LocalDateTime.now().format(formatter);
            String filename = ReportFilesInformation.Z03.getNamePrefix() + datetime
                    + ReportFilesInformation.Z03.getExtension();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            ReportFilesInformation.Z03.getAttachment() + filename + "\"")
                    .contentType(MediaType.parseMediaType(ReportFilesInformation.Z03.getExcelMediaType()))
                    .body(bytes);
        } catch (Exception e) {
            log.error("Error generating Z03 report", e);
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
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            ReportFilesInformation.Z05.getAttachment() + filename + "\"")
                    .contentType(MediaType.parseMediaType(ReportFilesInformation.Z05.getExcelMediaType()))
                    .body(bytes);
        } catch (Exception e) {
            log.error("Error generating Z05 report", e);
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
}
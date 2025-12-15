package car_backend.controller;

import car_backend.service.activity.ActivityFileService;
import org.springframework.web.bind.annotation.RestController;

import car_backend.model.dto.ActivityCreateUpdateDTO;
import car_backend.model.dto.ActivityDetailsDTO;
import car_backend.model.enums.ReportFilesInformation;
import car_backend.service.activity.ActivityFileService;
import car_backend.service.activity.ActivityService;
import car_backend.utils.reports.ReportsExcelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static car_backend.model.Constants.REPORT_DOWNLOAD_HEAD_VALUES;

@RestController
@RequestMapping(value = "/api")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @Autowired
    ActivityFileService activityFileService;

    private final ReportsExcelBuilder excelBuilder;

    public ActivityController(ReportsExcelBuilder excelBuilder) {
        this.excelBuilder = excelBuilder;
    }

    @PostMapping("/activity")
    public ResponseEntity<String> create(@RequestBody ActivityCreateUpdateDTO activity) {
        activityService.createActivity(activity);
        return ResponseEntity.ok().body("Success");
    }

    @GetMapping("/activity")
    public ResponseEntity<List<ActivityDetailsDTO>> getAll() {
        return ResponseEntity.ok().body(activityService.getActivities());
    }

    @PutMapping("/activity/{activity_id}")
    public ResponseEntity<String> update(@PathVariable(name = "activity_id") Long id, @RequestBody ActivityCreateUpdateDTO activity) {
        activityService.updateActivity(id, activity);
        return ResponseEntity.ok().body("Updated");

    @GetMapping("/activity/{activity_id}")
    public ResponseEntity<ActivityDetailsDto> getActivity(@PathVariable(name = "activity_id") Long id) {
        return ResponseEntity.ok().body(activityService.getActivity(id));
    }
    
    @GetMapping("/activity/report")
    public ResponseEntity<byte[]> downloadActivitiesReport() {
        try {
            byte[] bytes = excelBuilder.buildReport(activityFileService.getReport(null));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ReportFilesInformation.RELEVANT_SERVICES.getDateMask());
            String datetime = LocalDateTime.now().format(formatter);
            String filename = ReportFilesInformation.RELEVANT_SERVICES.getNamePrefix() + datetime + ReportFilesInformation.RELEVANT_SERVICES.getExtension();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, ReportFilesInformation.RELEVANT_SERVICES.getAttachment() + filename + "\"")
                    .contentType(MediaType.parseMediaType(ReportFilesInformation.RELEVANT_SERVICES.getExcelMediaType()))
                    .body(bytes);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
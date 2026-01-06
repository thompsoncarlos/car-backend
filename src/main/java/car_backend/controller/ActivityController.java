package car_backend.controller;

import car_backend.model.dto.ActivityCreateUpdateDto;
import car_backend.model.dto.ActivityDetailsDto;
import car_backend.service.activity.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/activity")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @Operation(summary = "Create a new activity", description = "Add a new activity to the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Add a new activity to the system", content = @Content(schema = @Schema(implementation = ActivityDetailsDto.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<ActivityDetailsDto> create(@RequestBody ActivityCreateUpdateDto activity) {
        return new ResponseEntity<>(activityService.createActivity(activity), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ActivityDetailsDto>> getAll() {
        return ResponseEntity.ok().body(activityService.getActivities());
    }

    @PutMapping("/{activity_id}")
    public ResponseEntity<ActivityDetailsDto> update(@PathVariable(name = "activity_id") String id, @RequestBody ActivityCreateUpdateDto activity) {
        return new ResponseEntity<>(activityService.updateActivity(id, activity), HttpStatus.CREATED);
    }

    @GetMapping("/{activity_id}")
    public ResponseEntity<ActivityDetailsDto> getActivity(@PathVariable(name = "activity_id") String id) {
        return ResponseEntity.ok().body(activityService.getActivity(id));
    }
    
    @GetMapping("/uo/{uo_id}")
    public ResponseEntity<List<ActivityDetailsDto>> getActivitiesByUo(@PathVariable(name = "uo_id") String uoId) {
        return ResponseEntity.ok().body(activityService.getActivitiesByUo(uoId));
    }
    
    @DeleteMapping("/{activity_id}")
    public ResponseEntity<?> deleteDraft(@PathVariable(name = "activity_id") String id) {
        activityService.deleteActivityDraft(id);
        return ResponseEntity.noContent().build();
    }
}
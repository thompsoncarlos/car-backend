package car_backend.controller;

import car_backend.service.activity.ActivityFileService;
import org.springframework.web.bind.annotation.RestController;

import car_backend.model.dto.ActivityCreateUpdateDTO;
import car_backend.model.dto.ActivityDetailsDTO;
import car_backend.service.activity.ActivityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @Autowired
    ActivityFileService activityFileService;

    @PostMapping
    public String create(@RequestBody ActivityCreateUpdateDTO activity) throws Exception {
        try {
            activityService.createActivity(activity);
            return "Success!";
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping
    public ActivityDetailsDTO getAll() {
        return activityService.getActivity();
    }
}
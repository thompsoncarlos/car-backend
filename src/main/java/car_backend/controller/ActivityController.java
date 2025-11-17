package car_backend.controller;

import org.springframework.web.bind.annotation.RestController;

import car_backend.model.dto.ActivityCreateUpdateDTO;
import car_backend.model.dto.ActivityDetailsDTO;
import car_backend.service.ActivityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/car")
public class ActivityController {

    @Autowired
    ActivityService activityService;

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
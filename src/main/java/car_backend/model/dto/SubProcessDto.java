package car_backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubProcessDto {

    private List<String> macroBusinessLines;
    private List<ActivityTypeDto> activityTypes;
    private List<String> macroProcesses;
    private List<String> processes;
    private List<String> subProcesses;
}

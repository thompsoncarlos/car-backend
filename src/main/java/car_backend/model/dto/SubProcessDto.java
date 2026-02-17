package car_backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubProcessDto {

    private String macroBusinessLines;
    private String activityTypes;
    private String macroProcesses;
    private String processes;
    private String subProcesses;

}

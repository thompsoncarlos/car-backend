package car_backend.model.enums; 

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum HeaderCriticalFunctions {
    SERVICE_ID("SERVICE ID"),
    SERVICE_TYPE("SERVICE TYPE"),
    UNIQUE_SERVICE("UNIQUE SERVICE TITLE"),
    CRITICAL_FUNCTION_COUNTRY("CRITICAL FUNCTION COUNTRY"),
    CRITICAL_FUNCTION_ID("CRITICAL FUNCTION ID");

    private String label;

    HeaderCriticalFunctions(String label) {
        this.label = label;
    }

    public static String[] getLabels() {
        List<String> labels = new ArrayList<>();
        for (HeaderRelevantServices header: HeaderRelevantServices.values()) {
            labels.add(header.getLabel());
        }
        return labels.toArray(String[]::new);
    }
}
package car_backend.model.enums; 

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum HeaderBusinessLines {

    SERVICE_ID("SERVICE ID"),
    SERVICE_TYPE("SERVICE TYPE"),
    UNIQUE_SERVICE("UNIQUE SERVICE TITLE"),
    CORE_BUSINESS_LINE_NAME("CORE BUSINESS LINE NAME"),
    CORE_BUSINESS_LINE_ID("CORE BUSINESS LINE ID");

    private String label;

    HeaderBusinessLines(String label) {
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
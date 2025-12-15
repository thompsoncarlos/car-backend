package car_backend.model.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum HeaderRoles {
    SERVICE_ID("SERVICE ID"),
    SERVICE_TYPE("SERVICE TYPE"),
    UNIQUE_SERVICE("UNIQUE SERVICE TITLE"),
    ROLE_ID("ROLE IDENTIFIER"),
    ROLE_NAME("ROLE NAME"),
    DEPARTMENT("DEPARTMENT"),
    CRITICALITY("CRITICALITY");

    private String label;

    HeaderRoles(String label) {
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
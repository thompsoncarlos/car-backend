package car_backend.model.enums;

import lombok.Getter;

import java.util.List;
import java.util.ArrayList;

@Getter
public enum HeaderRelevantServices {

    SERVICE_ID("SERVICE ID"),
    SERVICE_TYPE("SERVICE TYPE"),
    UNIQUE_SERVICE("UNIQUE SERVICE TITLE"),
    SERVICE_RECIPIENT_NAME("SERVICE RECIPIENT NAME"),
    SERVICE_RECIPIENT_CODE("SERVICE RECIPIENT CODE"),
    SERVICE_PROVIDER_ENTITY_NAME("SERVICE PROVIDER ENTITY NAME"),
    SERVICE_PROVIDER_ENTITY_CODE("SERVICE PROVIDER ENTITY CODE"),
    SERVICE_PROVIDER_ENTITY_TYPE("SERVICE PROVIDER ENTITY TYPE"),
    SERVICE_PROVIDER_PARENT_NAME("SERVICE PROVIDER PARENT NAME"),
    SERVICE_PROVIDER_PARENT_CODE("SERVICE PROVIDER PARENT CODE"),
    SERVICE_PROVIDER_PARENT_TYPE("SERVICE PROVIDER PARENT TYPE"),
    SERVICE_DELIVERY("SERVICE DELIVERY"),
    FEATURES("RESOLUTION-RESILIENCE FEATURES"),
    BPR("BUSINESS REORGANIZATION PLAN"),
    ALTERNATIVE_ACTIONS("ALTERNATIVE MITIGATING ACTIONS"),
    CRITICAL_ICT("CRITICAL ICT THIRD PARTY SERVICE PROVIDER UNDER DORA"),
    ICT_SERVICE("ICT SERVICE UNDER DORA");

    private String label;

    HeaderRelevantServices(String label) {
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

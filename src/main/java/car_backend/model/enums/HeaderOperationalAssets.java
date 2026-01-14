package car_backend.model.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum HeaderOperationalAssets {
    SERVICE_ID("SERVICE ID"),
    SERVICE_TYPE("SERVICE TYPE"),
    UNIQUE_SERVICE("UNIQUE SERVICE TITLE"),
    ASSET_IDENTIFIER("ASSET IDENTIFIER"),
    ASSET_TYPE("ASSET TYPE"),
    ASSET_NAME("ASSET NAME"),
    CRITICALITY("CRITICALITY"),
    CONTRACT_TYPE("CONTRACT TYPE"),
    CONTRACT_ID("CONTRACT ID"),
    GOVERNING_LAW("GOVERNING LAW"),
    FEATURES("RESOLUTION-RESILIENCE FEATURES"),
    BPR("BUSINESS REORGANIZATION PLAN"),
    ALTERNATIVE_ACTIONS("ALTERNATIVE MITIGATING ACTIONS");

    private String label;

    HeaderOperationalAssets(String label) {
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
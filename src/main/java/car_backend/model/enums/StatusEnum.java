package car_backend.model.enums;

import lombok.Getter;

@Getter
public enum StatusEnum {

    NEW("NEW"),
    DRAFT("DRAFT"),
    VALIDATED("VALID"),
    ON_HOLD("ON_HOLD"),
    ABOLISHED("ANNULER");

    private final String value;

    StatusEnum(String value) { this.value = value; }

    public static StatusEnum fromValue(String value) {
        for (StatusEnum val : StatusEnum.values()) {
            if (val.getValue().equals(value)) {
                return val;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }


}

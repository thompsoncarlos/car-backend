package car_backend.model.enums;

import lombok.Getter;

@Getter
public enum OptionsEnum {

    YES("YES"),
    NO("NO");

    private final String value;

    OptionsEnum(String value) { this.value = value; }

    public static OptionsEnum fromValue(String value) {
        for (OptionsEnum val: OptionsEnum.values()) {
            if (val.getValue().equals(value)) {
                return val;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }
}

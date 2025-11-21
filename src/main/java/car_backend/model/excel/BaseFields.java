package car_backend.model.excel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseFields {

    private String serviceId;
    private Double serviceType; // will come from the EBA table
    private String serviceUniqueLabel;
}

package car_backend.model.excel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportZ04 {

    @Id
    @Column(name = "SERVICE_IDENTIFIER")
    private String serviceId;

    @Column(name = "SERVICE_TYPE")
    private String serviceType;

    @Column(name = "UNIQUE_SERVICE_TITLE_BK_TAXO")
    private String serviceUniqueLabel;

    @Column(name = "CRITICAL_FUNCTION_ID")
    private String criticalFunctionId;

    @Column(name = "CRITICAL_FUNCTION_COUNTRY")
    private String criticalFunctionCountry;
    
}

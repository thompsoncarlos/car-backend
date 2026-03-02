package car_backend.model.reports;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PHI_T_REPORT_Z04")
public class ReportZ04 {

    @Id
    @Column(name = "SERVICE_IDENTIFIER_0005")
    private String serviceId;

    @Column(name = "SERVICE_TYPE_0010")
    private String serviceType;

    @Column(name = "UNIQUE_SERVICE_TITLE_BK_TAXO_0020")
    private String serviceUniqueLabel;

    @Column(name = "CRITICAL_FUNCTION_COUNTRY_0030")
    private String criticalFunctionId;

    @Column(name = "CRITICAL_FUNCTION_COUNTRY_CODE_0040")
    private String criticalFunctionCountry;

}

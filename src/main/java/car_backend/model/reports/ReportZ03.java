package car_backend.model.reports;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PHI_T_REPORT_Z03")
public class ReportZ03 {

    @Id
    @Column(name = "SERVICE_IDENTIFIER_0005", nullable = false)
    private String serviceIdentifier005;

    @Column(name = "SERVICE_TYPE_0010")
    private String serviceType0010;

    @Column(name = "UNIQUE_SERVICE_TITLE_BK_TAXO_0020")
    private String uniqueServiceTitle;

    @Column(name = "CRITICAL_FUNCTION_COUNTRY_0030")
    private String criticalFunctionCountry;

    @Column(name = "CRITICAL_FUNCTION_COUNTRY_CODE_0040")
    private String criticalFunctionCountryCode;

}

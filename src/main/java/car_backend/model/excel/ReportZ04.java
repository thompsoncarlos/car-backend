package car_backend.model.excel;

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
@Table(name = "PHI_T_REPORT_Z04")
public class ReportZ04 {

    @Id
    @Column(name = "SERVICE_IDENTIFIER_0005")
    private String serviceIdentifier;

    @Column(name = "SERVICE_TYPE_0010")
    private String serviceType;

    @Column(name = "UNIQUE_SERVICE_TITLE_BK_TAXO_0020")
    private String uniqueServiceTitle;

    @Column(name = "CRITICAL_FUNCTION_COUNTRY_0030")
    private String criticalFunctionCountry;

    @Column(name = "CRITICAL_FUNCTION_COUNTRY_CODE_0040")
    private String criticalFunctionCountryCode;

}

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
@Table(name = "PHI_T_REPORT_Z05")
public class ReportZ05 {

    @Id
    @Column(name = "SERVICE_IDENTIFIER_0005")
    private String serviceIdentifier;

    @Column(name = "SERVICE_TYPE_0010")
    private String serviceType;

    @Column(name = "UNIQUE_SERVICE_TITLE_BK_TAXO_0020")
    private String uniqueServiceTitle;

    @Column(name = "CORE_BUSINESS_LINE_NAME_0030");
    private String coreBusinessLineName;

    @Column(name = "CORE_BUSINESS_LINE_ID_0040")
    private String coreBusinessLineId;
}

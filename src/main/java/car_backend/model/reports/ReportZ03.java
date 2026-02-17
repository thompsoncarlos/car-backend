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
@Table(name = "PHI_T_REPORT_Z03")
public class ReportZ03 {

    @Id
    @Column(name = "SERVICE_IDENTIFIER_0005", nullable = false)
    private String serviceIdentifier0005;

    @Column(name = "SERVICE_TYPE_0010")
    private String serviceType0010;

    @Column(name = "UNIQUE_SERVICE_TITLE_BK_TAXO_0020")
    private String uniqueServiceTitleBkTaxo0020;

    @Column(name = "ROLE_IDENTIFIER_0030")
    private String roleIdentifier0030;

    @Column(name = "ROLE_NAME_0040")
    private String roleName0040;

    @Column(name = "DEPARTMENT_0050")
    private String department0050;

    @Column(name = "CRITICALITY_0060")
    private String criticality0060;
}

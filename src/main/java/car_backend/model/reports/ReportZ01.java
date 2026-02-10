package car_backend.model.reports;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PHI_T_REPORT_Z01_FINAL")
public class ReportZ01 {

    @Id
    @Column(name = "SERVICE_IDENTIFIER")
    private String serviceId;

    @Column(name = "SERVICE_TYPE")
    private String serviceType;

    @Column(name = "UNIQUE_SERVICE_TITLE_BK_TAXO")
    private String serviceUniqueLabel;

    @Column(name = "SERVICE_RECIPIENT_NAME")
    private String serviceRecipientName;

    @Column(name = "SERVICE_RECIPIENT_CODE")
    private String serviceRecipientCode;

    @Column(name = "COL__0050")
    private String serviceProviderEntityName;

    @Column(name = "COL__0060")
    private String serviceProviderEntityCode;

    @Column(name = "COL__0070")
    private String serviceProviderEntityType;

    @Column(name = "COL__0080")
    private String serviceProviderParentName;

    @Column(name = "COL__0090")
    private String serviceProviderParentCode;

    @Column(name = "COL__0100")
    private String serviceProviderParentType;

    @Column(name = "COL__0110")
    private String serviceProviderDelivery;

    @Column(name = "COL__0120")
    private String criticality;

    @Column(name = "COL__0130")
    private String contractId;

    @Column(name = "COL__0140")
    private String governingLaw;

    @Column(name = "COL__0150")
    private String resolutionFeatures;

    @Column(name = "COL__0160")
    private String businessReorganizationPlan;

    @Column(name = "COL__0170")
    private String alternativeMitigatingActions;

    @Column(name = "COL__0180")
    private String criticalIct;

    @Column(name = "COL__0190")
    private String ictService;

}

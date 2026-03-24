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
    @Column(name = "SERVICE_IDENTIFIER_0005", nullable = false)
    private String serviceIdentifier0005;

    @Column(name = "SERVICE_TYPE_0010")
    private String serviceType0010;

    @Column(name = "UNIQUE_SERVICE_TITLE_BK_TAXO_0020")
    private String uniqueServiceTitleBkTaxo0020;

    @Column(name = "SERVICE_RECIPIENT_NAME_0030")
    private String serviceRecipientName0030;

    @Column(name = "SERVICE_RECIPIENT_CODE_0040")
    private String serviceRecipientCode0040;

    @Column(name = "SERVICE_PROVIDER_ENTITY_NAME_0050")
    private String serviceProviderEntityName0050;

    @Column(name = "SERVICE_PROVIDER_ENTITY_CODE_0060")
    private String serviceProviderEntityCode0060;

    @Column(name = "SERVICE_PROVIDER_ENTITY_CODE_TYPE_0070")
    private String serviceProviderEntityCodeType0070;

    @Column(name = "SERVICE-PROVIDER_PARENT_NAME_0080")
    private String serviceProviderParentName0080;

    @Column(name = "SERVICE_PROVIDER_PARENT_CODE_0090")
    private String serviceProviderParentCode0090;

    @Column(name = "SERVICE_PROVIDER_PARENT_CODE_TYPE_0100")
    private String serviceProviderParentCodeType0100;

    @Column(name = "SERVICE_PROVIDER_DELIVERY_0110")
    private String serviceProviderDelivery0110;

    @Column(name = "CRITICALITY_0120")
    private String criticality0120;

    @Column(name = "CONTRACT_ID_0130")
    private String contractId0130;

    @Column(name = "GOVERNING_LAW_0140")
    private String governingLaw0140;

    @Column(name = "RESOLUTION_RESILIENCE_FEATURES_0150")
    private String resolutionResilienceFeatures0150;

    @Column(name = "RESOLUTION_RESILIENCE_BRP_0160")
    private String resolutionResilienceBrp0160;

    @Column(name = "RESOLUTION_RESILIENCE_ALT_MIT_0170")
    private String resolutionResilienceAltMit0170;

    @Column(name = "CRITICAL_ICT_THD_PARTY_SERV_PROV_UND_DORA_0180")
    private String criticalIctThdPartyServProvUndDora0180;

    @Column(name = "ICT_SERVICE_UNDER_DORA_0190")
    private String ictServiceUnderDora0190;

}

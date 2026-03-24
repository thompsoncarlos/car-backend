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
@Table(name = "PHI_T_REPORT_Z02")
public class ReportZ02 {

    @Id
    @Column(name = "SERVICE_IDENTIFIER_0005", nullable = false)
    private String serviceIdentifier0005;

    @Column(name = "SERVICE_TYPE_0010")
    private String serviceType0010;

    @Column(name = "UNIQUE_SERVICE_TITLE_BK_TAXO_0020")
    private String uniqueServiceTitleBkTaxo0020;

    @Column(name = "ASSET_IDENTIFIER_0030")
    private String assetIdentifier0030;

    @Column(name = "ASSET_TYPE_0040")
    private String assetType0040;

    @Column(name = "ASSET_NAME_0050")
    private String assetName0050;

    @Column(name = "CRITICALITY_0060")
    private String criticality0060;

    @Column(name = "CONTRACT_TYPE_0070")
    private String contractType0070;

    @Column(name = "CONTRACT_ID_0080")
    private String contractId0080;

    @Column(name = "GOVERNING_LAW_0090")
    private String governingLaw0090;

    @Column(name = "RESOLUTION_RESILIENCE_FEATURES_0100")
    private String resolutionResilienceFeatures0100;

    @Column(name = "RESOLUTION_RESILIENCE_BRP_0110")
    private String resolutionResilienceBrp0110;

    @Column(name = "RESOLUTION_RESILIENCE_ALT_MIT_0120")
    private String resolutionResilienceAltMit0120;
}
package car_backend.model.reports;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ReportZ02 extends BaseFields {

    private String assetIdentifier;
    private String assetType;
    private String assetName;
    private String criticality;
    private String contractType;
    private String contractId;
    private String governingLaw;
    private String resolutionFeatures;
    private String businessReorganizationPlan;
    private String alternativeMitigatingActions;

}
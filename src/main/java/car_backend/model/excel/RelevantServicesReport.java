package car_backend.model.excel;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class RelevantServicesReport {

    private String serviceRecipientName;
    private String serviceRecipientCode;

    private String serviceProviderEntityName;
    private String serviceProviderEntityCode;
    private String serviceProviderEntityType;

    private String serviceProviderParentName;
    private String serviceProviderParentCode;
    private String serviceProviderParentType;

    private String serviceProviderDelivery;

    private String criticality;
    private String contractId;
    private String governingLaw;

    private String resolutionFeatures;
    private String businessReorganizationPlan;
    private String alternativeMitigatingActions;

    private String criticalIct;
    private String ictService;

}

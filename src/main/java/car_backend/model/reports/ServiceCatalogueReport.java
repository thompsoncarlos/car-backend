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
@Table(name = "PHI_T_REPORT_SERVICE_CATALOGUE")
public class ServiceCatalogueReport {

    @Id
    @Column(name = "SERVICE_IDENTIFIER_0005")
    private String serviceIdentifier;

    @Column(name = "SERVICE_TYPE_0010")
    private String serviceType;

    @Column(name = "UNIQUE_SERVICE_TITLE_BK_TAXO_0015")
    private String serviceTitleBkTaxo;

    @Column(name = "SERVICE_DESCRIPTION_0020")
    private String serviceDescription;

    @Column(name = "SERVICE_PROVIDER_ENTITY_NAME_0030")
    private String serviceProviderEntityName;

    @Column(name = "SERVICE_PROVIDER_ENTITY_DEPARTMENT_NAME_0040")
    private String serviceProviderEntityDeptName;

    @Column(name = "SERVICE_PROVIDER_ENTITY_CODE_0050")
    private String serviceProviderEntityCode;

    @Column(name = "SERVICE_RECIPIENT_ENTITY_NAME_0060")
    private String serviceRecipientEntityName;

    @Column(name = "SERVICE_RECIPIENT_ENTITY_CODE_0070")
    private String serviceRecipientEntityCode;

    @Column(name = "DELIVERY_MODEL_0080")
    private String deliveryModel;

    @Column(name = "CRITICAL_FUNCTION_ID_0090")
    private String criticalFunctionId;

    @Column(name = "CORE_BUSINESS_LINE_0100")
    private String coreBusinessLineId;

    @Column(name = "SUBSTITUTABILITY_0110")
    private String substitutability;

    @Column(name = "COST_0120")
    private String cost;

    @Column(name = "CONTACT_ID_0130")
    private String contactId;

    @Column(name = "INCLUSION_OF_RESOLUTION_0140")
    private String inclusionOfResolution;

    @Column(name = "COUNTRY_0150")
    private String country;

    @Column(name = "MACRO_BUSINESS_LINE_0160")
    private String macroBusinessLine;

    @Column(name = "MACRO_PROCESS_0170")
    private String macroProcess;

    @Column(name = "PROCESS_0180")
    private String process;

    @Column(name = "SUB_PROCESS_0190")
    private String subProcess;

    @Column(name = "ACTIVITY_CATEGORY_0200")
    private String activityCategory;

    @Column(name = "IS_BLOKING_0210")
    private String isBlocking;
}

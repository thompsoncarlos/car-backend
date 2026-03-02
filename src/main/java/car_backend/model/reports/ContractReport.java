package car_backend.model.reports;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "PHI_T_REPORT_CONTRACT_REPOSITORY_FINAL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractReport {

    @Id
    @Column(name = "IDENTIFIER", length = 50)
    private String identifier;

    @Column(name = "SERVICE_IDENTIFIER", length = 100)
    private String serviceIdentifier;

    @Column(name = "START_DATE_OF_THE_CONTRACT")
    private LocalDate startDateOfTheContract;

    @Column(name = "END_DATE_OF_THE_CONTRACT")
    private LocalDate endDateOfTheContract;

    @Column(name = "NEXT_RENEWAL_DATE")
    private LocalDate nextRenewalDate;

    @Column(name = "SERVICE_RECIPIENT_NAME", length = 255)
    private String serviceRecipientName;

    @Column(name = "PROVIDER_ENTITY_NAME", length = 255)
    private String providerEntityName;

    @Column(name = "PROVIDER_ENTITY_CODE", length = 100)
    private String providerEntityCode;

    @Column(name = "PROVIDER_ENTITY_TYPE_OF_CODE", length = 50)
    private String providerEntityTypeOfCode;

    @Column(name = "PROVIDER_ENTITY_REGISTERED_ADDRESS", length = 500)
    private String providerEntityRegisteredAddress;

    @Column(name = "PROVIDER_PARENT_NAME", length = 255)
    private String providerParentName;

    @Column(name = "PROVIDER_PARENT_CODE", length = 100)
    private String providerParentCode;

    @Column(name = "PROVIDER_PARENT_TYPE_OF_CODE", length = 50)
    private String providerParentTypeOfCode;

    @Column(name = "SUBCONTRACTOR", length = 10)
    private String subcontractor;

    @Column(name = "PART_OF_THE_GROUP_SERVICE_DELIVERY", length = 50)
    private String partOfTheGroupServiceDelivery;

    @Column(name = "PART_OF_THE_RESOLUTION_GROUP", length = 10)
    private String partOfTheResolutionGroup;

    @Column(name = "GROUP_DEPARTMENT_RESPONSIBLE", length = 255)
    private String groupDepartmentResponsible;

    @Column(name = "BRIEF_DESCRIPTION_OF_THE_SERVICE", length = 4000)
    private String briefDescriptionOfTheService;

    @Column(name = "PRICING_STRUCTURE_PREDICTABLE", length = 10)
    private String pricingStructurePredictable;

    @Column(name = "ESTIMATED_TOTAL_ANNUAL_BUDGET_COST", length = 100)
    private String estimatedTotalAnnualBudgetCost;

    @Column(name = "DEGREE_OF_CRITICALITY", length = 50)
    private String degreeOfCriticality;

    @Column(name = "CRITICAL_FUNCTION_FOR_SERVICE", length = 1000)
    private String criticalFunctionForService;

    @Column(name = "CORE_BUSINESS_LINES_FOR_SERVICE", length = 2000)
    private String coreBusinessLinesForService;

    @Column(name = "RESOLUTION_GROUPS_FOR_SERVICE", length = 500)
    private String resolutionGroupsForService;

    @Column(name = "NAME_OF_ALTERNATIVE_SERVICE_PROVIDER", length = 255)
    private String nameOfAlternativeServiceProvider;

    @Column(name = "JURISDICTION_OF_CONTRACT", length = 255)
    private String jurisdictionOfContract;

    @Column(name = "GOVERNING_LAW", length = 255)
    private String governingLaw;

    @Column(name = "COUNTRIES_WHERE_SERVICES_PROVIDED", length = 500)
    private String countriesWhereServicesProvided;

    @Column(name = "RESOLUTION_RESILIENT_CONTRACT", length = 50)
    private String resolutionResilientContract;

    @Column(name = "PENALTIES_FOR_SUSPENSION", length = 2000)
    private String penaltiesForSuspension;

    @Column(name = "TRIGGERS_FOR_EARLY_TERMINATION", length = 2000)
    private String triggersForEarlyTermination;

    @Column(name = "TERMINATION_NOTICE_PERIOD_PROVIDER")
    private Integer terminationNoticePeriodProvider;

    @Column(name = "DURATION_POST_TERMINATION_ASSISTANCE")
    private Integer durationPostTerminationAssistance;

    @Column(name = "RELATIONSHIPS_BETWEEN_CONTRACTS", length = 100)
    private String relationshipsBetweenContracts;

    @Column(name = "CONDITIONS_OF_PAYMENT", length = 1000)
    private String conditionsOfPayment;

    @Column(name = "EXISTENCE_AUTOMATIC_RENEWAL_CLAUSES", length = 10)
    private String existenceAutomaticRenewalClauses;

    @Column(name = "QUANTITATIVE_PERFORMANCE_TARGETS", length = 4000)
    private String quantitativePerformanceTargets;

    @Column(name = "QUALITATIVE_PERFORMANCE_TARGETS", length = 4000)
    private String qualitativePerformanceTargets;

    @Column(name = "PARTIES_ALLOWED_TO_TERMINATE", length = 1000)
    private String partiesAllowedToTerminate;

    @Column(name = "ESTIMATED_TIME_FOR_SUBSTITUTABILITY", length = 255)
    private String estimatedTimeForSubstitutability;
}

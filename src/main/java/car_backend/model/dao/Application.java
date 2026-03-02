package car_backend.model.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PHI_T_APPLICATION")
public class Application {

    @Id
    @Column(name = "APPLICATION_ID")
    private String applicationId;

    @Column(name = "APPLICATION_LABEL")
    private String applicationLabel;

    @Column(name = "DESCRIPTION_FR")
    private String descriptionFr;

    @Column(name = "DESCRIPTION_EN")
    private String descriptionEn;

    @Column(name = "CIA_CODE")
    private String ciaCode;

    @Column(name = "IS_CRITIQUE_OR_BLOCKING")
    private String isCritiqueOrBlocking;

    @Column(name = "NAME_OF_ASSET")
    private String nameOfAsset;

    @Column(name = "TYPE_OF_ASSET")
    private String typeOfAsset;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "LEGAL_CONTRACT_TYPE")
    private String legalContractType;

    @Column(name = "RESOLUTION_RESILIENT_FEATURES")
    private String resolutionResilientFeatures;

    @Column(name = "RF_FEAT_BUSINESS_REORGANIZATION_PLAN")
    private String rfFeatBusinessReorganizationPlan;

    @Column(name = "RF_FEAT_ALTERNATIVE_MITIGATING_ACTIONS")
    private String rfFeatAlternativeMitigatingActions;

    @Column(name = "APPDEPOSITARY")
    private String appDepositary;

    @Column(name = "SOURCE")
    private String source;

    @Column(name = "VIRTUAL_ROWID")
    private String virtualRowId;

    @ManyToMany
    @JoinTable(name = "PHI_T_REL_APPLICATION_CONTRACT",
            joinColumns = @JoinColumn(name = "APPLICATION_ID"),
            inverseJoinColumns = @JoinColumn(name = "CONTRACT_ID")
    )
    private Set<Contract> assignedContracts = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "PHI_T_REL_APPLICATION_MIDDLEWARE",
            joinColumns = @JoinColumn(name = "APPLICATION_ID"),
            inverseJoinColumns = @JoinColumn(name = "MIDDLEWARE_ID")
    )
    private Set<Middleware> assignedMiddlewares = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "PHI_T_REL_APPLICATION_SERVER",
            joinColumns = @JoinColumn(name = "APPLICATION_ID"),
            inverseJoinColumns = @JoinColumn(name = "SERVER_ID")
    )
    private Set<Server> assignedServers = new HashSet<>();

    @ManyToMany(mappedBy = "assignedApplications")
    private Set<Activity> activitySet = new HashSet<>();



}

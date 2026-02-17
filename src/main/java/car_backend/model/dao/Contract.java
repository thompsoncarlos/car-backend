package car_backend.model.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PHI_T_CONTRACT")
public class Contract {

    @Id
    @Column(name = "CONTRACT_ID")
    private String contractId;

    @Column(name = "GOVERNING_LAW")
    private String governingLaw;

    @Column(name = "RESOLUTION_RESILIENCE_FEATURES")
    private String resolutionResilienceFeatures;

    @Column(name = "ALTERNATIVE_MITIGATING_ACTIONS")
    private String alternativeMitigatingActions;

    @Column(name = "CRIT_ICT_THRD_P_SER_PROV_DORA")
    private String critIctThrdPSerProvDora;

    @Column(name = "ICT_SERVICE_UNDER_DORA")
    private String ictServiceUnderDora;

    @ManyToMany
    @JoinTable(name = "PHI_T_REL_CONTRACT_BUILDING",
            joinColumns = @JoinColumn(name = "CONTRACT_ID"),
            inverseJoinColumns = @JoinColumn(name = "BULDING_ID")
    )
    private Set<Building> assignedBuildings = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "PHI_T_REL_CONTRACT_MIDDLEWARE",
            joinColumns = @JoinColumn(name = "CONTRACT_ID"),
            inverseJoinColumns = @JoinColumn(name = "MIDDLEWARE_ID")
    )
    private Set<Middleware> assignedMiddlewares = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "PHI_T_REL_CONTRACT_PRESTATION",
            joinColumns = @JoinColumn(name = "CONTRACT_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRESTATION_ID")
    )
    private Set<Prestation> assignedPrestations = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "PHI_T_REL_CONTRACT_SERVER",
            joinColumns = @JoinColumn(name = "CONTRACT_ID"),
            inverseJoinColumns = @JoinColumn(name = "SERVER_ID")
    )
    private Set<Server> assignedServers = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "PHI_T_REL_CONTRACT_SUPPLIER",
            joinColumns = @JoinColumn(name = "CONTRACT_ID"),
            inverseJoinColumns = @JoinColumn(name = "SUPPLIER_ID")
    )
    private Set<Supplier> assignedSuppliers = new HashSet<>();

    @ManyToMany(mappedBy = "assignedContracts")
    private Set<Application> applicationSet = new HashSet<>();

}

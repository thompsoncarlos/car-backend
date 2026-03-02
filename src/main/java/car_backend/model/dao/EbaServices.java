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
@Table(name = "PHI_T_EBA_SERVICES")
public class EbaServices {

    @Id
    @Column(name = "EBA_SERVICES_ID")
    private String ebaServicesId;

    @Column(name = "SERVICE_FRANCAIS")
    private String serviceFrancais;

    @Column(name = "SOUS_SERVICE_FRANCAIS")
    private String sousServiceFrancais;

    @Column(name = "SERVICE_ENGLISH")
    private String serviceEnglish;

    @Column(name = "SUB_SERVICE_ENGLISH")
    private String subServiceEnglish;

    @Column(name = "VIRTUAL_ROWID")
    private String virtualRowId;

    @ManyToMany
    @JoinTable(name = "PHI_T_REL_EBA_SERVICES_ACTIVITY_TYPE",
            joinColumns = @JoinColumn(name = "EBA_SERVICES_ID"),
            inverseJoinColumns = @JoinColumn(name = "ACTIVITY_TYPE_ID")
    )
    private Set<ActivityType> assignedActivityTypes = new HashSet<>();
}

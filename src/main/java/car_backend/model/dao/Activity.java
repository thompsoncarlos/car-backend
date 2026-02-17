package car_backend.model.dao;

import car_backend.model.dao.refs.OrganizationalUnitRef;
import car_backend.model.dao.relations.ActivityPersonRel;
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
@Table(name = "PHI_T_ACTIVITY", schema = "HPIOA")
public class Activity {

    @Id
    @Column(name = "ACTIVITY_ID")
    private String activityId;

    @Column(name = "ACTIVITY_LABEL")
    private String activityLabel;

    @Column(name = "ACTIVITY_DESCRIPTION")
    private String activityDescription;

    @Column(name = "IS_AGREEMENT_NECESSARY")
    private String isAgreementNecessary;

    @Column(name = "ACT_TYPE_AGREEMENT_CONFIRMATION_GAIN")
    private String actType;

    @Column(name = "IS_BLOCKING")
    private String isBlocking;

    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;

    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

    @Column(name = "COMMENTAIRES")
    private String commentaires;

    @Column(name = "LIFE_CYCLE")
    private String lifeCycle;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "ACTIVITY_CATEGORY_TYPE")
    private String activityCategoryType;

    @Column(name = "ESSENTIAL")
    private String essential;

    @Column(name = "CRITICAL")
    private String critical;

    @Column(name = "CBL")
    private String cbl;

    @Column(name = "ACTIVITY_BUSINESS_LINE_INFORMATION")
    private String activityBusinessLineInfo;

    @Column(name = "VERSION_ACTIVITY")
    private String activityVersion;

    @Column(name = "VALIDATION_DATE")
    private String validationDate;

    @Column(name = "CREATOR")
    private String creator;

    @Column(name = "UPDATOR")
    private String modifier;

    @Column(name = "REVIEW_DATE")
    private String reviewDate;

    @Column(name = "VIRTUAL_ESSENTIAL")
    private String virtualEssential;

    @Column(name = "VIRTUAL_CRITICAL")
    private String virtualCritical;

    @Column(name = "VIRTUAL_ROWID")
    private String  virtualRowId;

    @Column(name = "SOURCE")
    private String source;

     @Column(name = "ACTIVITY_TYPE_ID")
    private String activityTypeId;

    @Column(name = "CRITICAL_ESSENTIAL")
    private String criticalEssential;

    @ManyToMany
    @JoinTable(name = "PHI_T_REL_ACTIVITY_ACTIVITY_TYPE",
            joinColumns = @JoinColumn(name = "ACTIVITY_ID"),
            inverseJoinColumns = @JoinColumn(name = "ACTIVITY_TYPE_ID")
    )
    private Set<ActivityType> assignedActivityTypes = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "PHI_T_REL_ACTIVITY_APPLICATION",
            joinColumns = @JoinColumn(name = "ACTIVITY_ID"),
            inverseJoinColumns = @JoinColumn(name = "APPLICATION_ID")
    )
    private Set<Application> assignedApplications = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "PHI_T_REL_ACTIVITY_ENTITY",
            joinColumns = @JoinColumn(name = "ACTIVITY_ID"),
            inverseJoinColumns = @JoinColumn(name = "LEGAL_ENTITY_ID")
    )
    private Set<LegalEntity> assignedLegalEntities = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "PHI_T_REL_ACTIVITY_TRADEMARK",
            joinColumns = @JoinColumn(name = "ACTIVITY_ID"),
            inverseJoinColumns = @JoinColumn(name = "TRADEMARK_ID")
    )
    private Set<Trademark> assignedTrademarks = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "PHI_T_REL_ACTIVITY_UO",
            joinColumns = @JoinColumn(name = "ACTIVITY_ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_UO")
    )
    private Set<OrganizationalUnitRef> assignedUos = new HashSet<>();

    @OneToMany(mappedBy = "assignedActivity", fetch = FetchType.LAZY)
    private Set<ActivityPersonRel> personRelations = new HashSet<>();

}

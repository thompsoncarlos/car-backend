package car_backend.model.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PHI_T_LEGAL_ENTITY")
public class LegalEntity {

    @Id
    @Column(name = "LEGAL_ENTITY_ID", nullable = false)
    private String legalEntityId;

    @Column(name = "LEGAL_ENTITY_LABEL")
    private String legalEntityLabel;

    @Column(name = "LEI_CODE")
    private String leiCode;

    @Column(name = "SIREN_CODE")
    private String sirenCode;

    @Column(name = "PARENT_ENTITY_NAME")
    private String parentEntityName;

    @Column(name = "PARENT_LEI_CODE")
    private String parentLeiCode;

    @Column(name = "VIRTUAL_ROWID")
    private String virtualRowId;

    @Column(name = "CRN_CODE")
    private String crnCode;

    @Column(name = "LEGAL_ENTITY_PARENT_ID")
    private String legalEntityParentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LEGAL_ENTITY_PARENT_ID",
            referencedColumnName = "LEGAL_ENTITY_ID",
            insertable = false,
            updatable = false)
    private LegalEntity parentEntity;

    @OneToMany(mappedBy = "parentEntity", fetch = FetchType.LAZY)
    private List<LegalEntity> childEntities;

    @ManyToMany(mappedBy = "assignedLegalEntities")
    private Set<Activity> activitySet = new HashSet<>();
}

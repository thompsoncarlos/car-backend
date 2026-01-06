package car_backend.model.dao.files;

import car_backend.model.enums.OptionsEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PHI_T_FILES_ACTIVITY_PRESTATION")
public class ActivityPrestation {

    @Id
    @Column(name = "ID_TECH_LINE")
    private Long techLineId;

    @Column(name = "ACTIVITY_ID")
    private String activityId;

    @Column(name = "ACTIVITY_LABEL")
    private String activityLabel;

    @Column(name = "PRESTATION_ID")
    private String prestationId;

    @Column(name = "IS_BLOCKING")
    @Enumerated(EnumType.STRING)
    private OptionsEnum isBlocking;

    @Column(name = "PRESTATION_LABEL")
    private String prestationLabel;

    @Column(name = "PRESTATION_CATEGORY")
    private String prestationCategory;

    @Column(name = "ONLY_SERVICE")
    @Enumerated(EnumType.STRING)
    private OptionsEnum onlyService;

    @Column(name = "CONTRACT_IDENTIFIER")
    private String contractId;

    @Column(name = "PROVIDER_LABEL")
    private String providerLabel;

    @Column(name = "COMMENTAIRES")
    private String commentaries;

    @Column(name = "ROW_UPDATE_DATE")
    private LocalDateTime updateDateRow;

    @Column(name = "PRESTATION_DESCRIPTION")
    private String prestationDescription;

    @Column(name = "SQLLDR_LOAD_ID")
    private String sqlLoadId;

    @Column(name = "VIRTUAL_ROWID")
    private String virtualRowId;
}

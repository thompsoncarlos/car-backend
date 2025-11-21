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

    @Column(name = "PRESTATION_ID")
    private String prestationId;

    @Column(name = "ACTIVITY_ID")
    private String activityId;

    @Column(name = "ACTIVITY_LABEL")
    private String activityLabel;

    @Column(name = "IS_BLOCKING")
    @Enumerated(EnumType.STRING)
    private OptionsEnum isBlocking;

    @Column(name = "PRESTATION_DESCRIPTION")
    private String prestationDescription;

    @Column(name = "PRESTATION_CATEGORY")
    private String prestationCategory;

    @Column(name = "ONLY_SERVICE")
    @Enumerated(EnumType.STRING)
    private OptionsEnum onlyService;

    @Column(name = "CONTRACT_IDENTIFIER")
    private String contractId;

    @Column(name = "PROVIDER_LABEL")
    private String providerLabel;

    @Column(name = "COMMNETAIRES")
    private String commentaries;
}

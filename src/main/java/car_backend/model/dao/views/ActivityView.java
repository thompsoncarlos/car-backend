package car_backend.model.dao.views;

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
@Table(name = "PHI_V_ACTIVITY")
public class ActivityView {

    @Id
    @Column(name = "ACTIVITY_ID")
    private String activityId;

    @Column(name = "ACTIVITY_LABEL")
    private String activityName;

    @Column(name = "ACTIVITY_TYPE")
    private String activityType;

    @Column(name = "AGREEMENT_IS_NECESSARY")
    @Enumerated(EnumType.STRING)
    private OptionsEnum agreementIsNecessary;

    @Column(name = "DO_YOU_CONFIRM")
    @Enumerated(EnumType.STRING)
    private OptionsEnum doYouConfirm;

    @Column(name = "IS_BLOCKING")
    @Enumerated(EnumType.STRING)
    private OptionsEnum isBlocking;
}

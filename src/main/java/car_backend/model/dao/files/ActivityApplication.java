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
@Table(name = "PHI_T_FILES_ACTIVITY_APPLICATION")
public class ActivityApplication {

    @Id
    @Column(name = "APPLICATION_LABEL")
    private String applicationLabel;

    @Column(name = "ENTITY")
    private String entity;

    @Column(name = "SOUS_PROCESSUS")
    private String subProcess;

    @Column(name = "ACTIVITY_ID")
    private String activityId;

    @Column(name = "ACTIVITY_LABEL")
    private String activityLabel;

    @Column(name = "APPDEPOSITORY")
    private String appDepository;

    @Column(name = "IS_BLOCKING")
    @Enumerated(EnumType.STRING)
    private OptionsEnum isBlocking;

    @Column(name = "COMMENTAIRES")
    private String commentaries;
}

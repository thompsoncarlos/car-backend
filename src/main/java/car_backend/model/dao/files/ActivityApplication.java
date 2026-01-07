package car_backend.model.dao.files;

import car_backend.model.enums.OptionsEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PHI_T_FILES_ACTIVITY_APPLICATION")
public class ActivityApplication {
 @Id
    @Column(name = "ID_TECH_LINE")
    private Long techLineId;

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

    @Column(name = "APPLICATION_LABEL")
    private String applicationLabel;

    @Column(name = "IS_BLOCKING")
    @Enumerated(EnumType.STRING)
    private OptionsEnum isBlocking;

    @Column(name = "COMMENTAIRES")
    private String commentaries;

    @Column(name = "ROW_UPDATE_DATE")
    private LocalDateTime updateDate;

    @Column(name = "SQLLDR_LOAD_ID")
    private String sqlLdrLoadId;

    @Column(name = "NOM_D_APPLI")
    private String applicationName;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "FOURNISSEUR")
    private String fournisseur;

    @Column(name = "DETENTION")
    private String detention;

    @Column(name = "COLONNE1")
    private String colonne;

    @Column(name = "VIRTUAL_ROW_ID")
    private String virtualRowId;
}

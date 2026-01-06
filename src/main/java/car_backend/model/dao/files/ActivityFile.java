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
@Table(name = "PHI_T_FILES_ACTIVITY")
public class ActivityFile {

     @Id
    @Column(name = "ID_TECH_LINE")
    private Long techLineId;

    @Column(name = "ACTIVITY_ID")
    private String activityId;

    @Column(name = "ACTIVITY_LABEL")
    private String activityLabel;

    @Column(name = "ACTIVITY_DESCRIPTION")
    private String activityDescription;

    @Column(name = "ACTIVITY_CATEGORY_TYPE_LABEL")
    private String activityCategoryType;

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

    @Column(name = "SUB_PROCESS_LABEL")
    private String subProcessLabel;

    @Column(name = "PROCESS_LABEL")
    private String processLabel;

    @Column(name = "SUB_SERVICE_LABEL")
    private String subServiceLabel;

    @Column(name = "UPDATE_DATE")
    private String updateDate;

    @Column(name = "COL_CHECK")
    private String check;

    @Column(name = "COMMENTAIRES")
    private String commentaries;

    @Column(name = "ROW_UPDATE_DATE")
    private LocalDateTime updateDateRow;

    @Column(name = "SQLLDR_LOAD_ID")
    private String sqlLoadId;

    @Column(name = "VIRTUAL_ROWID")
    private String virtualRowId;
}

package car_backend.model.dao.files;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "PHI_T_FILES_ACTIVITY_ACTIVITY")
public class ActivityLinked {

    @Id
    @Column(name = "ID_TECH_LINE")
    private Long techLineId;

    @Column(name = "PARENT_ACTIVITY_ID")
    private String parentActivityId;

    @Column(name = "PARENT_ACTIVITY_LABEL")
    private String parentActivityLabel;

    @Column(name = "CHILD_ACTIVITY_ID")
    private String childActivityId;

    @Column(name = "CHILD_ACTIVITY_LABEL_MANDATORY")
    private String childActivityLabel;

    @Column(name = "ACTIVITY_EBA_TYPE")
    private String activityEbaType;

    @Column(name = "IS_CHILD_BLOCKING")
    private String isChildBlocking;

    @Column(name = "COMMENTARIES")
    private String commentaries;

    @Column(name = "ROW_UPDATE_DATE")
    private LocalDateTime updateDate;

    @Column(name = "SQLLDR_LOAD_ID")
    private String sqlLdrLoadId;

    @Column(name = "VIRTUAL_ROWID")
    private String virtualRowId;

}

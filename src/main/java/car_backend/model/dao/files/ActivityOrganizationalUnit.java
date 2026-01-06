package car_backend.model.dao.files;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PHI_T_FILES_ACTIVITY_UO")
public class ActivityOrganizationalUnit {

   @Id
    @Column(name = "ID_TECH_LINE")
    private Long techLineId;

    @Column(name = "ACTIVITY_ID")
    private String activityId;

    @Column(name = "ACTIVITY_LABEL")
    private String activityLabel;

    @Column(name = "LEGAL_ENTITY_LABEL")
    private String legalEntityLabel;

    @Column(name = "ORGANIZATIONAL_UNIT_LABEL")
    private String organizationalUnitLabel;

    @Column(name = "COMMENTAIRES")
    private String commentaries;

    @Column(name = "ROW_UPDATE_DATE")
    private LocalDateTime updateDate;

    @Column(name = "ID_UO")
    private String uoId;

    @Column(name = "ID_LEGAL_ENTITY")
    private String legalEntityId;

    @Column(name = "VIRTUAL_ROWID")
    private String virtualRowId;
}

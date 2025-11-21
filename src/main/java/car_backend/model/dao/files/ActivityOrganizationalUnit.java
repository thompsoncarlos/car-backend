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
    @Column(name = "ORGANIZATIONAL_UNIT_LABEL")
    private String organizationalUnitLabel;

    @Column(name = "ACTIVITY_ID")
    private String activityId;

    @Column(name = "ACTIVITY_LABEL")
    private String activityLabel;

    @Column(name = "LEGAL_ENTITY_LABEL")
    private String legalEntityLabel;

    @Column(name = "COMMENTARIES")
    private String commentaries;
}

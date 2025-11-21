package car_backend.model.dao;

import car_backend.model.enums.OptionsEnum;
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
@Table(name = "PHI_REF_ACTIVITY_TYPE")
public class ActivityType {

    @Id
    @Column(name = "ACTIVITY_TYPE_ID")
    private String activityTypeId;

    @Column(name = "ACTIVITY_TYPE_LABEL")
    private String activityTypeLabel;

    @Column(name = "ACTIVITY_TYPE_CATEGORY")
    private String activityTypeCategory;

    @Column(name = "ACTIVITY_TYPE_DESCRIPTION")
    private String activityTypeDescription;

    @Column(name = "AGREEMENT_IS_NECESSARY")
    private OptionsEnum agreementIsNecessary;

    @Column(name = "STATUS")
    private String status;
}

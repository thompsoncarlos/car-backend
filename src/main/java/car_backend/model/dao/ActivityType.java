package car_backend.model.dao;

import car_backend.model.enums.OptionsEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PHI_T_ACTIVITY_TYPE_CATEGORY")
public class ActivityType {

    @Id
    @Column(name = "ACTIVITY_TYPE_ID")
    private String activityTypeId;

    @Column(name = "SUB_PROCESS_ID")
    private String subProcessId;

    @Column(name = "EBA_ECO_FUNCTION_ID")
    private String ebaEcoFunctionId;

    @Column(name = "CRITICAL")
    private String critical;

    @Column(name = "ACTIVITY_TYPE_CATEGORY")
    private String activityTypeCategory;

    @Column(name = "ACTIVITY_TYPE_LABEL")
    private String activityTypeLabel;

    @Column(name = "ACTIVITY_TYPE_DESCRIPTION")
    private String activityTypeDescription;

    @Column(name = "PROPAGATED_CRITICAL_ESSENTIEL")
    private String propagatedCriticalEssential;

    @ManyToMany(mappedBy = "assignedActivityTypes")
    private Set<Activity> activitySet = new HashSet<>();

    @ManyToMany(mappedBy = "assignedActivityTypes")
    private Set<EbaServices> ebaServicesSet = new HashSet<>();
}
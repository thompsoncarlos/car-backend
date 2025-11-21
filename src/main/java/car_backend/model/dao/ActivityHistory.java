package car_backend.model.dao;

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
@Table(name = "PHI_T_WEB_ACTIVITY_INPUT_USER_HISTORY")
public class ActivityHistory {

    @Id
    @Column(name = "ACTIVITY_ID")
    private String activityId;

    @Column(name = "ACTIVITY_LABEL")
    private String activityName;

    @Column(name = "ACTIVITY_DESCRIPTION")
    private String activityDescription;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "VERSION_ACTIVITY")
    private String activityVersion;

    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;
}

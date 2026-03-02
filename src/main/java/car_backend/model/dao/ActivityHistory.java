package car_backend.model.dao;

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
@Table(name = "PHI_T_WEB_ACTIVITY_INPUT_USER_HISTORY")
public class ActivityHistory {

    @Id
    @Column(name = "ID_ACTIVITY")
    private Long activityId;

    @Column(name = "ACTIVITY_LABEL")
    private String activityName;

    @Column(name = "ACTIVITY_DESCRIPTION")
    private String activityDescription;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "VERSION_ACTIVITY")
    private Long activityVersion;

    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;

}

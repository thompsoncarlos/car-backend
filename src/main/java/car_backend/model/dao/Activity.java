package car_backend.model.dao;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PHI_T_ACTIVITY", schema = "HPIOA")
public class Activity {

    @Id
    @Column(name = "ACTIVITY_ID")
    private String activityId;

    @Column(name = "ACTIVITY_LABEL")
    private String activityLabel;

    @Column(name = "ACTIVITY_DESCRIPTION")
    private String activityDescription;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "VERSION_ACTIVITIY")
    private String activityVersion;

    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;

}

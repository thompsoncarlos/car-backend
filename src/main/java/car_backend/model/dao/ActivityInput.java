package car_backend.model.dao;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PHI_T_WEB_ACTIVITY_INPUT_USER")
public class ActivityInput {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ACTIVITY")
    private String id;

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
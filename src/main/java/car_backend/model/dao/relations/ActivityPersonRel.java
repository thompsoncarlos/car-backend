package car_backend.model.dao.relations;

import car_backend.model.dao.Activity;
import car_backend.model.dao.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PHI_T_REL_ACTIVITY_PERSON", schema = "HPIOA")
public class ActivityPersonRel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RELATION", nullable = false)
    private Long idRelation;

    @ManyToOne
    @JoinColumn(name = "ACTIVITY_ID")
    private Activity assignedActivity;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private Person assignedPerson;

    @Column(name = "KEY_PERSON")
    private String keyPerson;
}
package car_backend.model.dao;

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
@Table(name = "PHI_T_TRADEMARK")
public class Trademark {

    @Id
    @Column(name = "TRADEMARK_ID")
    private String trademarkId;

    @Column(name = "TRADEMARK_LABEL")
    private String trademarkLabel;

    @ManyToMany(mappedBy = "assignedTrademarks")
    private Set<Activity> activitySet = new HashSet<>();
}

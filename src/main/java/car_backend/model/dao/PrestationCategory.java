package car_backend.model.dao;

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
@Table(name = "PHI_T_PRESTATION_CATEGORY")
public class PrestationCategory {

    @Id
    @Column(name = "ID_PRESTATION_CATEGORY")
    private Long prestationTypeId;

    @Column(name = "LABEL_PRESTATION_CATEGORY")
    private String prestationName;
}

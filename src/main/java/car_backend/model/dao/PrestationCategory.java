package car_backend.model.dao;

import car_backend.model.enums.OptionsEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PHI_REF_PRESTATION_CATEGORY")
public class PrestationCategory {

    @Id
    private Long id;

    @Column(name = "PRESTATION_CATEGORY_LABEL")
    private String prestationName;

    @Column(name = "ONLY_SERVICE")
    @Enumerated(EnumType.STRING)
    private OptionsEnum onlyService;

    @Column(name = "PRESTATION_TYPE_ID")
    private Long prestationTypeId;
}

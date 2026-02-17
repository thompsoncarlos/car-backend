package car_backend.model.dao.refs;

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
@Table(name = "PHI_REF_PRESTATION_CATEGORY")
public class PrestationCategoryRef {

    @Id
    @Column(name = "PRESTATION_TYPE_ID")
    private Long prestationTypeId;

    @Column(name = "PRESTATION_CATEGORY_LABEL")
    private String prestationName;
}

package car_backend.model.dao.views;

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
@Table(name = "PHI_V_UO")
public class OrganizationalUnitView {

    @Id
    @Column(name = "ID_UO")
    private String id;

    @Column(name = "UO_LABEL_EN")
    private String englishLabel;

    @Column(name = "UO_LABEL_FR")
    private String frenchLabel;

    @Column(name = "ID_ETABLISSEMENT")
    private String establishmentId;
}

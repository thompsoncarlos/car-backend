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
@Table(name = "PHI_V_PERSON")
public class PersonView {

    @Id
    @Column(name = "ID_PERSONNE")
    private String personId;

    @Column(name = "NOM")
    private String lastname;

    @Column(name = "PRENOM")
    private String name;

    @Column(name = "ID_UO")
    private String uoId;

    @Column(name = "ID_HIERARCHY_SUP")
    private String idHierarchy;

    @Column(name = "COUNTRY")
    private String country;
}

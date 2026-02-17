package car_backend.model.dao;

import car_backend.model.dao.Person;
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
@Table(name = "PHI_T_REF_UO")
public class OrganizationalUnitRef {

    @Id
    @Column(name = "ID_UO")
    private String id;

    @Column(name = "UO_LABEL_EN")
    private String englishLabel;

    @Column(name = "UO_LABEL_FR")
    private String frenchLabel;

    @Column(name = "ID_ETABLISSEMENT")
    private String establishmentId;

    @Column(name = "ID_TECH_LINE")
    private Long techLineId;

    @ManyToMany(mappedBy = "assignedUos")
    private Set<Person> personSet = new HashSet<>();

}
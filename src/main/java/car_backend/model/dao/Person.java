package car_backend.model.dao;

import car_backend.model.dao.refs.OrganizationalUnitRef;
import car_backend.model.dao.relations.ActivityPersonRel;
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
@Table(name = "PHI_T_PERSON")
public class Person {

    @Id
    @Column(name = "PERSON_ID")
    private String personId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "SUPERIOR_ID")
    private String superiorId;

    @Column(name = "COUNTRY_FR")
    private String countryFr;

    @Column(name = "COUNTRY_EN")
    private String countryEn;

    @Column(name = "CITY")
    private String city;

    @Column(name = "ROLE_ID")
    private String roleId;

    @Column(name = "ROLE_NAME")
    private String roleName;

    @Column(name = "LABEL_ROLE_EN")
    private String labelRoleEn;

    @Column(name = "LABEL_ROLE_FR")
    private String labelRoleFr;

    @Column(name = "UO_PERSON_ID")
    private String uoPersonId;

    @Column(name = "PERSON_EMAIL")
    private String personEmail;

    @Column(name = "PERSON_PHONE_NUMBER")
    private String personPhoneNumber;

    @Column(name = "TYPE_OF_CONTRACT")
    private String typeOfContract;

    @Column(name = "PERSON_STATUS")
    private String personStatus;

    @Column(name = "BUILDING_ID")
    private String buildingId;

    @ManyToMany
    @JoinTable(name = "PHI_T_REL_PERSON_UO",
            joinColumns = @JoinColumn(name = "PERSON_ID"),
            inverseJoinColumns = @JoinColumn(name = "UO_ID"))
    private Set<OrganizationalUnitRef> assignedUos = new HashSet<>();

    @OneToMany(mappedBy = "assignedPerson", fetch = FetchType.LAZY)
    private Set<ActivityPersonRel> activityRelations = new HashSet<>();
}

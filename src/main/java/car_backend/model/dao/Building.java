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
@Table(name = "PHI_T_BUILDING")
public class Building {

    @Id
    @Column(name = "BUILDING_ID")
    private String buildingId;

    @Column(name = "BUILDING_LABEL")
    private String buildingLabel;

    @Column(name = "BUILDING_CODE")
    private String buildingCode;

    @Column(name = "LIBELLE_ETAT_JURIDIQUE")
    private String legalStatus;

    @ManyToMany(mappedBy = "assignedBuildings")
    private Set<Contract> contractSet = new HashSet<>();

    @ManyToMany(mappedBy = "assignedBuildings")
    private Set<Server> serverSet = new HashSet<>();

}

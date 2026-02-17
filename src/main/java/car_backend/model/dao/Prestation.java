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
@Table(name = "PHI_T_PRESTATION")
public class Prestation {

    @Id
    @Column(name = "PRESTATION_ID")
    private String prestationId;

    @Column(name = "CODE_LEI_CRN")
    private String codeLeiCrn;

    @Column(name = "PRESTATION_LABEL")
    private String prestationLabel;

    @Column(name = "PRESTATION_CATEGORY")
    private String prestationCategory;

    @Column(name = "ONLY_SERVICE")
    private String onlyService;

    @Column(name = "VIRTUAL_ROWID")
    private String virtualRowId;

    @ManyToMany(mappedBy = "assignedPrestations")
    private Set<Contract> contractSet = new HashSet<>();
}

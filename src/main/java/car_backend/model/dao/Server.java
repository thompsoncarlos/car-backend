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
@Table(name = "PHI_T_SERVER")
public class Server {

    @Id
    @Column(name = "SERVER_ID")
    private String serverId;

    @Column(name = "SERVER_LABEL")
    private String serverLabel;

    @ManyToMany
    @JoinTable(name = "PHI_T_REL_REL_SERVER_BUILDING",
            joinColumns = @JoinColumn(name = "SERVER_ID"),
            inverseJoinColumns = @JoinColumn(name = "BUILDING_ID")
    )
    private Set<Building> assignedBuildings = new HashSet<>();

    @ManyToMany(mappedBy = "assignedServers")
    private Set<Middleware> middlewareSet = new HashSet<>();

    @ManyToMany(mappedBy = "assignedServers")
    private Set<Contract> contractSet = new HashSet<>();

    @ManyToMany(mappedBy = "assignedServers")
    private Set<Application> applicationSet = new HashSet<>();

}

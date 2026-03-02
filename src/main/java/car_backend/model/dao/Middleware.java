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
@Table(name = "PHI_T_MIDDLEWARE")
public class Middleware {

    @Id
    @Column(name = "MIDDLEWARE_ID")
    private String middlewareId;

    @Column(name = "MIDDLEWARE_LABEL")
    private String middlewareLabel;

    @ManyToMany
    @JoinTable(name = "PHI_T_REL_MIDDLEWARE_SERVER",
            joinColumns = @JoinColumn(name = "MIDDLEWARE_ID"),
            inverseJoinColumns = @JoinColumn(name = "SERVER_ID")
    )
    private Set<Server> assignedServers = new HashSet<>();

    @ManyToMany(mappedBy = "assignedMiddlewares")
    private Set<Application> applicationSet = new HashSet<>();

    @ManyToMany(mappedBy = "assignedMiddlewares")
    private Set<Contract> contractSet = new HashSet<>();
}

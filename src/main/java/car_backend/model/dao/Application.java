package car_backend.model.dao;

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
@Table(name = "PHI_T_REF_APP")
public class Application {

    @Id
    @Column(name = "APPLICATION_LABEL")
    private String applicationLabel;

    @Column(name = "DEPOSITORY")
    private String depository;
}

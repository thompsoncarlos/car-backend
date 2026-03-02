package car_backend.model.dao;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PHI_T_SUB_PROCESS")
public class SubProcess {

    @Id
    @Column(name = "SUB_PROCESS_ID")
    private String subProcessId;

    @Column(name = "PROCESS_ID")
    private String processId;

    @Column(name = "LABEL")
    private String label;

}

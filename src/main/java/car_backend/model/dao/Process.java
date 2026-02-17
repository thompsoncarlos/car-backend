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
@Table(name = "PHI_T_PROCESS")
public class Process {

    @Id
    @Column(name = "PROCESS_ID")
    private String processId;

    @Column(name = "MACRO_PROCESS_ID")
    private String macroProcessId;

    @Column(name = "LABEL")
    private String label;
}

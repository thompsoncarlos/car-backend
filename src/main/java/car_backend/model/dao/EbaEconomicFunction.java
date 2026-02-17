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
@Table(name = "PHI_T_EBA_ECO_FUNCTION")
public class EbaEconomicFunction {

    @Id
    @Column(name = "EBA_ECO_FUNCTION_ID", nullable = false)
    private String ebaEcoFunctionId;

    @Column(name = "EBA_FUNCTION_LABEL")
    private String ebaFunctionLabel;

    @Column(name = "EBA_CODE")
    private String ebaCode;

    @Column(name = "CRITICAL")
    private String critical;

    @Column(name = "CRITICAL_ESSENTIEL")
    private String criticalEssential;

}

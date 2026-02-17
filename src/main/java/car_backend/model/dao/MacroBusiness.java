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
@Table(name = "PHI_T_MACRO_BUSINESS_LINE")
public class MacroBusiness {

    @Id
    @Column(name = "BUSINESS_ID")
    private String businessId;

    @Column(name = "LABEL")
    private String label;
}

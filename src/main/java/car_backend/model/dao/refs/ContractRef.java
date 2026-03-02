package car_backend.model.dao.refs;

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
@Table(name = "PHI_REF_CONTRACT")
public class ContractRef {

    @Id
    @Column(name = "ID_TECH_LINE")
    private Long techLineId;

    @Column(name = "CONTRACT_IDENTIFIER")
    private String contractId;

    @Column(name = "CONTRACT_CATEGORY_LABEL")
    private String contractCategoryLabel;

    @Column(name = "CRITICITY")
    private String criticality;

    @Column(name = "PROVIDER_LABEL")
    private String providerLabel;

    @Column(name = "PECI")
    private String peci;

    @Column(name = "PROVIDER_ID")
    private String providerId;

}

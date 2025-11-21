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
@Table(name = "PHI_REF_CONTRACT")
public class Contract {

    @Id
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

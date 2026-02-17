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
@Table(name = "PHI_T_SUPPLIER")
public class Supplier {

    @Id
    @Column(name = "SUPPLIER_ID")
    private String supplierId;

    @Column(name = "SUPPLIER_LABEL")
    private String supplierLabel;

    @Column(name = "LEI_SUPPLIER_CODE")
    private String leiSupplierCode;

    @Column(name = "CRN_SUPPLIER")
    private String crnSupplier;

    @Column(name = "SIREN_SIRET_SUPPLIER_CODE")
    private String sirenSiretSupplierCode;

    @Column(name = "SUPPLIER_PARENT_ID")
    private String supplierParentId;

    @ManyToMany(mappedBy = "assignedSuppliers")
    private Set<Contract> contractSet = new HashSet<>();

}

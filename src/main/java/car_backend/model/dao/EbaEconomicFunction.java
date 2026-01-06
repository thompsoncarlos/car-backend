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
@Table(name = "PHI_REF_EBA_ECONOMIC_FUNCTIONS")
public class EbaEconomicFunction {

    @Id
    @Column(name = "ID_TECH_LINE")
    private Long techLineId;

    @Column(name = "REF")
    private String ref;

    @Column(name = "FONCTION_FRANCAIS")
    private String frenchFunction;

    @Column(name = "SOUS_FONCTIONS_FRANCAIS")
    private String frenchSubfunction;

    @Column(name = "FUNCTION_ENGLISH")
    private String englishFunction;

    @Column(name = "SUB_FUNCTIONSS_ENGLISH")
    private String englishSubfunction;

    @Column(name = "ROW_UPDATE_DATE")
    private LocalDateTime updateDateRow;

    @Column(name = "SQLLDR_LOAD_ID")
    private String sqlLoadId;

    @Column(name = "VIRTUAL_ROWID")
    private String virtualRowId;

}

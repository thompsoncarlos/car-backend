package car_backend.model.dao.refs;

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
@Table(name = "PHI_REF_EBA_SERVICES")
public class EbaServicesRef {

    @Id
    @Column(name = "ID_TECH_LINE")
    private Long techLineId;

    @Column(name = "REF")
    private String ref;

    @Column(name = "SERVICE_FRANCAIS")
    private String frenchService;

    @Column(name = "SOUS_SERVICE_FRANCAIS")
    private String frenchSubservice;

    @Column(name = "SERVICE_ENGLISH")
    private String englishService;

    @Column(name = "SUB_SERVICE_ENGLISH")
    private String englishSubservice;

    @Column(name = "ROW_UPDATE_DATE")
    private LocalDateTime updateDateRow;

    @Column(name = "SQLLDR_LOAD_ID")
    private String sqlLoadId;

    @Column(name = "VIRTUAL_ROWID")
    private String virtualRowId;
}

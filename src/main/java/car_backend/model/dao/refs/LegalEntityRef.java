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
@Table(name = "PHI_REF_LEGAL_ENTITY")
public class LegalEntityRef {

    @Id
    @Column(name = "ID_TECH_LINE")
    private Long techLineId;

    @Column(name = "LEGAL_ENTITY_ID")
    private String legalEntityId;

    @Column(name = "LEGAL_ENTITYLABEL")
    private String legalEntityLabel;

    @Column(name = "ROW_UPDATE_DATE")
    private LocalDateTime updateDateRow;

    @Column(name = "SQLLDR_LOAD_ID")
    private String sqlLoadId;

    @Column(name = "VIRTUAL_ROWID")
    private String virtualRowId;
}

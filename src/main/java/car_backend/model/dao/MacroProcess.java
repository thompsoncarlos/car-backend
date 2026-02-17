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
@Table(name = "PHI_T_MACRO_PROCESS_BL")
public class MacroProcess {

    @Id
    @Column(name = "MACRO_PROCESS_ID")
    private String macroProcessId;

    @Column(name = "BUSINESS_ID")
    private String businessId;

    @Column(name = "CORE_BUSINESS_LINE_NAME")
    private String coreBusinessLineName;

    @Column(name = "CORE_BUSINESS_LINE_ID")
    private String coreBusinessLineId;

    @Column(name = "LABEL")
    private String label;

    @Column(name = "IMPORTANT")
    private String important;
}
